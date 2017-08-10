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
package org.openstack4j.api.trove;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.trove.constant.DatastoreType;
import org.openstack4j.openstack.trove.constant.InstanceType;
import org.openstack4j.openstack.trove.constant.VolumeType;
import org.openstack4j.openstack.trove.domain.DatabaseInstance;
import org.openstack4j.openstack.trove.domain.DatabaseInstanceCreate;
import org.openstack4j.openstack.trove.domain.DatabaseUser;
import org.openstack4j.openstack.trove.domain.Datastore;
import org.openstack4j.openstack.trove.domain.NIC;
import org.openstack4j.openstack.trove.domain.Volume;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Trove/Instance", enabled = true)
public class DatabaseInstanceTest extends AbstractTest {

	@Test
	public void testDeleteInstance() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().trove().instances().delete("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testRestartInstance() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().trove().instances().restart("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("restart") instanceof ObjectNode);
		Assert.assertFalse(node.get("restart").fields().hasNext());

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testResizeInstanceVolume() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().trove().instances().resize("instance-id", 200);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("resize") instanceof ObjectNode);
		Assert.assertTrue(node.get("resize").get("volume") instanceof ObjectNode);
		Assert.assertEquals(node.get("resize").get("volume").get("size").asInt(), 200);

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testResizeInstanceFlavor() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().trove().instances().resize("instance-id", "flavor-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertTrue(node.get("resize") instanceof ObjectNode);
		Assert.assertEquals(node.get("resize").get("flavorRef").asText(), "flavor-id");

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testListInstance() throws IOException, InterruptedException {
		respondWith("/trove/list_instance_response.json");

		List<DatabaseInstance> instances = osv3().trove().instances().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(instances.size(), 9);

		DatabaseInstance instance = instances.get(6);
		Assert.assertEquals(instance.getId(), "d11fb880-89dd-4fb0-b510-6dbbc29ca67a");
		Assert.assertEquals(instance.getStatus(), "ACTIVE");
		Assert.assertEquals(instance.getName(), "rds-d946");
		Assert.assertEquals(instance.getLinks().size(), 2);
		Assert.assertEquals(instance.getLinks().get(0).getHref(), "");
		Assert.assertEquals(instance.getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getLinks().get(1).getHref(), "");
		Assert.assertEquals(instance.getLinks().get(1).getRel(), "bookmark");

		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);

		Assert.assertEquals(instance.getHostname(), null);
		Assert.assertEquals(instance.getIp(), "192.168.2.7");
		Assert.assertEquals(instance.getRegion(), "eu-de");

		Assert.assertEquals(instance.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getDatastore().getVersion(), "MySQL-5.6.34");

		Assert.assertEquals(instance.getFlavor().getId(), "0d922098-553c-4124-80df-e627a1d41a0d");
		Assert.assertEquals(instance.getFlavor().getLinks().size(), 2);
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getHref(), "");
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getHref(), "");
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getRel(), "bookmark");

		Assert.assertEquals(instance.getReplicaOf().size(), 1);
		Assert.assertEquals(instance.getReplicaOf().get(0).getId(), "abc36f95-8a4f-4c71-b3cc-20953cc98ab6");
		Assert.assertEquals(instance.getReplicaOf().get(0).getLinks().size(), 2);

	}

	@Test
	public void testGetInstance() throws IOException, InterruptedException {
		respondWith("/trove/get_instance_response.json");

		DatabaseInstance instance = osv3().trove().instances().get("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(instance.getId(), "e8faac23-8129-4c68-a231-480e46fc5f4f");
		Assert.assertEquals(instance.getStatus(), "ACTIVE");
		Assert.assertEquals(instance.getName(), "rds-MySQL-1-1");

		Assert.assertEquals(instance.getLinks().size(), 2);
		Assert.assertEquals(instance.getLinks().get(0).getHref(), "");
		Assert.assertEquals(instance.getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getLinks().get(1).getHref(), "");
		Assert.assertEquals(instance.getLinks().get(1).getRel(), "bookmark");

		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 210);

		Assert.assertEquals(instance.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getDatastore().getVersion(), "MySQL-5.7.17");

		Assert.assertEquals(instance.getFlavor().getId(), "31b2863c-0e15-44fd-a80d-1e83a7aca338");
		Assert.assertEquals(instance.getFlavor().getLinks().size(), 2);
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getHref(), "");
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getHref(), "");
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getRel(), "bookmark");

		Assert.assertEquals(instance.getReplicaOf(), null);

		Assert.assertEquals(instance.getConfigurationStatus(), "In-Sync");
		Assert.assertEquals(instance.getConfigurationId(), "b89db814-6ba1-454f-a9ad-380064ef0c6f");
		Assert.assertEquals(instance.getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getSubnetId(), "0fb5d084-4e5d-463b-8920-fca10e6b4028");
		Assert.assertEquals(instance.getRole(), InstanceType.Master);
		Assert.assertEquals(instance.getInternalSubnetId(), "330a10fd-3962-44c5-b3a1-1d282617a183");
		Assert.assertEquals(instance.getGroup(), "1");
		Assert.assertEquals(instance.getSecureGroupId(), "ca99fcef-502f-495f-b28d-85c9c6f4666e");

		Assert.assertEquals(instance.getRegion(), null);
		Assert.assertEquals(instance.getFault(), null);
		Assert.assertEquals(instance.getConfiguration(), null);
		Assert.assertEquals(instance.getLocality(), null);
		Assert.assertEquals(instance.getReplicas(), null);
		Assert.assertEquals(instance.getStorageEngine(), null);
		Assert.assertEquals(instance.getDbUser(), "root");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), "292997f2-3bf7-4d60-86a5-4e9d593bc850");
		Assert.assertEquals(instance.getPayModel().intValue(), 0);
		Assert.assertEquals(instance.getClusterId(), "fb22f24c-0466-48f2-8275-70af04ef4935");

		Assert.assertEquals(instance.getCreated().getTime(), 1494555526000L);
		Assert.assertEquals(instance.getUpdated().getTime(), 1494555526000L);
	}

	@Test
	public void testCreateInstance() throws IOException, InterruptedException {
		respondWith("/trove/create_instance_response.json");

		// build datastore version
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("6.3.35").build();
		// get flavor
		Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();
		NIC nic = NIC.builder().networkId("network-id").securityGroupId("sg-id").build();
		DatabaseUser user = DatabaseUser.builder().username("root").password("Demo@234").build();

		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name("name").datastore(datastore)
				.flavorRef("flavor-id").users(Lists.newArrayList(user)).volume(volume)
				.configurationId("config-id").availabilityZone("eu-de-01").vpcId("vpc-id").nics(Lists.newArrayList(nic))
				.build();

		DatabaseInstance instance = osv3().trove().instances().create(instanceCreate);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/trove/create_instance_request.json");
		Assert.assertEquals(requestBody, expectBody);
		
		Assert.assertEquals(instance.getId(), "c90c1234-f687-462a-a6bd-cec35919c096");
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getName(), "creat-trove-instance-28-MySQL-1-1");
		Assert.assertEquals(instance.getLinks().size(), 2);
		Assert.assertEquals(instance.getLinks().get(0).getHref(), null);
		Assert.assertEquals(instance.getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getLinks().get(1).getHref(), null);
		Assert.assertEquals(instance.getLinks().get(1).getRel(), "bookmark");

		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);

		Assert.assertEquals(instance.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getDatastore().getVersion(), "MySQL-5.6.33");

		Assert.assertEquals(instance.getFlavor().getId(), "99001234-dfc2-4418-b224-fea05d358ce3");
		Assert.assertEquals(instance.getFlavor().getLinks().size(), 2);
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getHref(), null);
		Assert.assertEquals(instance.getFlavor().getLinks().get(0).getRel(), "self");
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getHref(), null);
		Assert.assertEquals(instance.getFlavor().getLinks().get(1).getRel(), "bookmark");

	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
