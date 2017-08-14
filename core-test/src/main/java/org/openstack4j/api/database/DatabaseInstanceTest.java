/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.api.database;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.database.constants.DatastoreType;
import org.openstack4j.openstack.database.constants.InstanceType;
import org.openstack4j.openstack.database.constants.ReplicationMode;
import org.openstack4j.openstack.database.constants.VolumeType;
import org.openstack4j.openstack.database.domain.BackupStrategy;
import org.openstack4j.openstack.database.domain.DatabaseInstance;
import org.openstack4j.openstack.database.domain.DatabaseInstanceCreate;
import org.openstack4j.openstack.database.domain.Datastore;
import org.openstack4j.openstack.database.domain.HA;
import org.openstack4j.openstack.database.domain.NIC;
import org.openstack4j.openstack.database.domain.Volume;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/Instance", enabled = true)
public class DatabaseInstanceTest extends AbstractTest {

	@Test
	public void testDeleteInstance() throws IOException, InterruptedException {
		respondWith(200, "{ \"jobId\": \"ff8080815a88d52c015a8a0db2250003\" }");

		String jobId = osv3().database().instances().delete("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertNotNull(jobId);
		Assert.assertEquals(jobId, "ff8080815a88d52c015a8a0db2250003");
	}

	@Test
	public void testRestartInstance() throws IOException, InterruptedException {
		respondWith("/database/restart_instance_response.json");

		List<String> jobIds = osv3().database().instances().restart("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("restart") instanceof ObjectNode);
		Assert.assertFalse(node.get("restart").fields().hasNext());

		Assert.assertEquals(jobIds.size(), 1);
		Assert.assertEquals(jobIds.get(0), "2b414788-a600-4883-a023-90e2eb0ea227");
	}

	@Test
	public void testResizeInstanceVolume() throws IOException, InterruptedException {
		respondWith("/database/resize_instance_volume_response.json");

		List<String> jobIds = osv3().database().instances().resize("instance-id", 200);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("resize") instanceof ObjectNode);
		Assert.assertTrue(node.get("resize").get("volume") instanceof ObjectNode);
		Assert.assertEquals(node.get("resize").get("volume").get("size").asInt(), 200);

		Assert.assertEquals(jobIds.size(), 1);
		Assert.assertEquals(jobIds.get(0), "2b414788-a600-4883-a023-90e2eb0ea227");
	}

	@Test
	public void testResizeInstanceFlavor() throws IOException, InterruptedException {
		respondWith("/database/resize_instance_flavor_response.json");

		List<String> jobIds = osv3().database().instances().resize("instance-id", "flavor-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("resize") instanceof ObjectNode);
		Assert.assertEquals(node.get("resize").get("flavorRef").asText(), "flavor-id");

		Assert.assertEquals(jobIds.size(), 1);
		Assert.assertEquals(jobIds.get(0), "ff8080815703e6de015703e98504001a");
	}

	@Test
	public void testListInstance() throws IOException, InterruptedException {
		respondWith("/database/list_instance_response.json");

		List<DatabaseInstance> instances = osv3().database().instances().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(instances.size(), 1);

		DatabaseInstance instance = instances.get(0);
		Assert.assertEquals(instance.getId(), "252f11f1-2912-4c06-be55-1999bde659c5");
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getName(), "trove-instance-rep3");
		Assert.assertEquals(instance.getCreated().getTime(), 1466277710000L);
		Assert.assertEquals(instance.getUpdated().getTime(), 1466277710000L);
		Assert.assertEquals(instance.getHostname(), "");
		Assert.assertEquals(instance.getType(), InstanceType.Master);
		Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), "490a4a08-ef4b-44c5-94be-3051ef9e4fce");
		Assert.assertEquals(instance.getNic().getSubnetId(), "0e2eda62-1d42-4d64-a9d1-4e9aa9cd994f");
		Assert.assertEquals(instance.getSecurityGroup().getId(), "2a1f7fc8-3307-42a7-aa6f-42c8b9b8f8c5");
		Assert.assertEquals(instance.getFlavor().getId(), "bf07a6d4-844a-4023-a776-fc5c5fb71fb4");
		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getDatastore().getVersion(), "5.6.30");
		Assert.assertEquals(instance.getBackupStrategy().getStartTime(), "01:00:00");
		Assert.assertEquals(instance.getBackupStrategy().getKeepDays().intValue(), 3);
		Assert.assertEquals(instance.getSlaveId(), "9405d8b8-a87d-4531-bd3a-e504c8434281");
		Assert.assertEquals(instance.getHa().getReplicationMode(), ReplicationMode.ASYNC);
	}

	@Test
	public void testGetInstance() throws IOException, InterruptedException {
		respondWith("/database/get_instance_response.json");

		DatabaseInstance instance = osv3().database().instances().get("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(instance.getId(), "252f11f1-2912-4c06-be55-1999bde659c5");
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getName(), "trove-instance-rep3");
		Assert.assertEquals(instance.getCreated().getTime(), 1466277710000L);
		Assert.assertEquals(instance.getUpdated().getTime(), 1466277710000L);
		Assert.assertEquals(instance.getHostname(), "");
		Assert.assertEquals(instance.getType(), InstanceType.Master);
		Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), "490a4a08-ef4b-44c5-94be-3051ef9e4fce");
		Assert.assertEquals(instance.getNic().getSubnetId(), "0e2eda62-1d42-4d64-a9d1-4e9aa9cd994f");
		Assert.assertEquals(instance.getSecurityGroup().getId(), "2a1f7fc8-3307-42a7-aa6f-42c8b9b8f8c5");
		Assert.assertEquals(instance.getFlavor().getId(), "bf07a6d4-844a-4023-a776-fc5c5fb71fb4");
		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getDatastore().getVersion(), "5.6.30");
		Assert.assertEquals(instance.getBackupStrategy().getStartTime(), "01:00:00");
		Assert.assertEquals(instance.getBackupStrategy().getKeepDays().intValue(), 3);
		Assert.assertEquals(instance.getSlaveId(), "9405d8b8-a87d-4531-bd3a-e504c8434281");
		Assert.assertEquals(instance.getHa().getReplicationMode(), ReplicationMode.ASYNC);
	}

	@Test
	public void testCreateInstance() throws IOException, InterruptedException {
		respondWith("/database/create_instance_response.json");

		// build datastore version
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("6.3.35").build();

		// get flavor
		Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();

		NIC nic = NIC.builder().subnetId("network-id").build();
		BackupStrategy backupStrategy = BackupStrategy.builder().keepDays(1).startTime("15:00:00").build();
		HA ha = HA.builder().enable(true).replicationMode(ReplicationMode.ASYNC).build();

		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name("sdk-test").datastore(datastore)
				.flavorRef("flavor-id").volume(volume).region("eu-de").availabilityZone("eu-de-01").vpcId("vpc-id")
				.nic(nic).securityGroup(new IdResourceEntity("sg-id")).rootPassword("1qaz@WSX")
				.backupStrategy(backupStrategy).ha(ha).build();

		DatabaseInstance instance = osv3().database().instances().create(instanceCreate);


		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances");
		Assert.assertEquals(request.getMethod(), "POST");
		
		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/database/create_instance_request.json");
		Assert.assertEquals(requestBody, expectBody);

		Assert.assertEquals(instance.getId(), "252f11f1-2912-4c06-be55-1999bde659c5");
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getName(), "trove-instance-rep3");
		Assert.assertEquals(instance.getCreated(), null);
		Assert.assertEquals(instance.getUpdated(), null);
		Assert.assertEquals(instance.getHostname(), "");
		Assert.assertEquals(instance.getType(), InstanceType.Master);
		Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), "490a4a08-ef4b-44c5-94be-3051ef9e4fce");
		Assert.assertEquals(instance.getNic().getSubnetId(), "0e2eda62-1d42-4d64-a9d1-4e9aa9cd994f");
		Assert.assertEquals(instance.getSecurityGroup().getId(), "2a1f7fc8-3307-42a7-aa6f-42c8b9b8f8c5");
		Assert.assertEquals(instance.getFlavor().getId(), "bf07a6d4-844a-4023-a776-fc5c5fb71fb4");
		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getDatastore(), null);
		Assert.assertEquals(instance.getBackupStrategy().getStartTime(), "01:00:00");
		Assert.assertEquals(instance.getBackupStrategy().getKeepDays().intValue(), 3);
		Assert.assertEquals(instance.getSlaveId(), "9405d8b8-a87d-4531-bd3a-e504c8434281");
		Assert.assertEquals(instance.getHa().getReplicationMode(), ReplicationMode.ASYNC);
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
