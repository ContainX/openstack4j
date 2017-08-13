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
import java.util.Date;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.database.constants.BackupStatus;
import org.openstack4j.openstack.database.constants.DatastoreType;
import org.openstack4j.openstack.database.constants.InstanceType;
import org.openstack4j.openstack.database.constants.ReplicationMode;
import org.openstack4j.openstack.database.constants.VolumeType;
import org.openstack4j.openstack.database.domain.DatabaseBackup;
import org.openstack4j.openstack.database.domain.DatabaseBackupCreate;
import org.openstack4j.openstack.database.domain.DatabaseBackupCreateResponse;
import org.openstack4j.openstack.database.domain.DatabaseBackupPolicy;
import org.openstack4j.openstack.database.domain.DatabaseInstance;
import org.openstack4j.openstack.database.domain.DatabaseInstanceRestore;
import org.openstack4j.openstack.database.domain.DatabaseRestorePoint;
import org.openstack4j.openstack.database.domain.HA;
import org.openstack4j.openstack.database.domain.Volume;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/backup", enabled = true)
public class DatabaseBackupTest extends AbstractTest {

	@Test
	public void testUpdateBackupPolicy() throws IOException, InterruptedException {
		respondWith("/database/list_errorlog_response.json");

		DatabaseBackupPolicy policy = DatabaseBackupPolicy.builder().keepDay(3).startTime("03:10:00").build();
		ActionResponse response = osv3().database().backups().updateBackupPolicy("instance-id", policy);
		Assert.assertTrue(response.isSuccess());

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/backups/policy");
		Assert.assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("03:10:00", node.get("policy").get("starttime").asText());
		Assert.assertEquals(3, node.get("policy").get("keepday").asInt());
	}

	@Test
	public void testGetBackupPolicy() throws IOException, InterruptedException {
		respondWith("/database/get_bakcup_policy_response.json");

		DatabaseBackupPolicy policy = osv3().database().backups().getBackupPolicy("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/backups/policy");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(policy.getKeepDay().intValue(), 7);
		Assert.assertEquals(policy.getStartTime(), "00:00:00");
	}

	@Test
	public void testCreateBackup() throws IOException, InterruptedException {
		respondWith("/database/create_backup_response.json");

		DatabaseBackupCreate creation = DatabaseBackupCreate.builder().name("backup-name").description("sdk unittests")
				.instance("instance-id").build();
		DatabaseBackupCreateResponse response = osv3().database().backups().create(creation);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/backups");
		Assert.assertEquals(request.getMethod(), "POST");

		DatabaseBackup backup = response.getBackup();
		Assert.assertNotNull(response.getExtendParam());
		Assert.assertEquals(response.getExtendParam().getJobs().size(), 1);
		Assert.assertEquals(response.getExtendParam().getJobs().get(0).getId(), "ff80808157127d9301571bf8160c001d");

		Assert.assertNotNull(backup);
		Assert.assertEquals(backup.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(backup.getDatastore().getVersion(), "5.6.30");
		Assert.assertEquals(backup.getDatastore().getVersionId(), "e8a8b8cc-63f8-4fb5-8d4a-24c502317a61");

		Assert.assertEquals(backup.getId(), "2f4ddb93-b901-4b08-93d8-1d2e472f30fe");
		Assert.assertEquals(backup.getDescription(), "123");
		Assert.assertEquals(backup.getName(), "test011111");
		Assert.assertEquals(backup.getBackupType(), "1");
		Assert.assertEquals(backup.getInstanceId(), "0bc7300c-dc63-45d4-aa3b-d85bf577baac");
		Assert.assertEquals(backup.getStatus(), BackupStatus.BUILDING);
		Assert.assertEquals(backup.getCreated().getTime(), 1473643025000L);
	}

	@Test
	public void testListBackup() throws IOException, InterruptedException {
		respondWith("/database/list_backup_response.json");

		List<DatabaseBackup> backups = osv3().database().backups().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/backups");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(backups.size(), 2);

		DatabaseBackup backup = backups.get(0);

		Assert.assertNotNull(backup);
		Assert.assertEquals(backup.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(backup.getDatastore().getVersion(), "5.6.30");
		Assert.assertEquals(backup.getDatastore().getVersionId(), "e8a8b8cc-63f8-4fb5-8d4a-24c502317a61");

		Assert.assertEquals(backup.getId(), "2a4d0f86-67cd-408a-8b66-017454fb7793");
		Assert.assertEquals(backup.getDescription(), "");
		Assert.assertEquals(backup.getLocationRef(), "");
		Assert.assertEquals(backup.getName(),
				"50deafb3e45d451a9406ca146b71fe9a_rds-1_4f87d3c4-9e33-482f-b962-e23b30d1a18c_2016_08_23_01_59_23.tar.gz");
		Assert.assertEquals(backup.getBackupType(), "1");
		Assert.assertEquals(backup.getInstanceId(), "4f87d3c4-9e33-482f-b962-e23b30d1a18c");
		Assert.assertEquals(backup.getStatus(), BackupStatus.COMPLETED);
		Assert.assertEquals(backup.getSize(), 0.0D);
		Assert.assertEquals(backup.getCreated().getTime(), 1471924763000L);
		Assert.assertEquals(backup.getUpdated().getTime(), 1471924900000L);
	}

	@Test
	public void testDeleteBackup() throws IOException, InterruptedException {
		respondWith(200, "{}");

		ActionResponse response = osv3().database().backups().delete("backup-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/backups/backup-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testRestoreToExistInstance() throws IOException, InterruptedException {
		respondWith("/database/restore_to_exist_instance_response.json");

		Date now = new Date();
		DatabaseRestorePoint point = DatabaseRestorePoint.builder().backupRef("backup-ref").restoreTime(now).build();
		List<String> jobIds = osv3().database().backups().restoreToExistInstance("restore-to", point);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/restore-to/action");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("backup-ref", node.get("restore").get("backupRef").asText());
		Assert.assertEquals(now.getTime(), node.get("restore").get("restoreTime").asLong());

		Assert.assertEquals(jobIds, Lists.newArrayList("ff80808156fa51c50156fa7c20210bc9"));
	}

	@Test
	public void testRestoreToNewInstance() throws IOException, InterruptedException {
		respondWith("/database/restore_to_new_instance_response.json");

		Date now = new Date(1502617216267L);
		Volume volume = Volume.builder().size(100).build();
		HA ha = HA.builder().enable(true).replicationMode(ReplicationMode.ASYNC).build();
		DatabaseRestorePoint restorePoint = DatabaseRestorePoint.builder().backupRef("backup-ref").restoreTime(now)
				.sourceInstanceId("source-instance").build();
		DatabaseInstanceRestore restore = DatabaseInstanceRestore.builder().name("bakup").flavorRef("flavor-ref")
				.volume(volume).ha(ha).restorePoint(restorePoint).build();
		DatabaseInstance instance = osv3().database().backups().restoreToNewInstance(restore);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		String expected = this.getResource("/database/restore_to_new_instance_request.json");
		Assert.assertEquals(requestBody, expected);

		Assert.assertEquals(instance.getId(), "9fbe7995-9851-47ea-b7af-6037104a1dd5");
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getName(), "rds-f1d61");
		Assert.assertEquals(instance.getCreated(), null);
		Assert.assertEquals(instance.getUpdated(), null);
		Assert.assertEquals(instance.getHostname(), "");
		Assert.assertEquals(instance.getType(), InstanceType.Master);
		Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), "2d6d6053-6dd1-46d7-99b4-02c62686a628");
		Assert.assertEquals(instance.getNic().getSubnetId(), "a2c3a6e3-5204-4f53-aa4c-bc3d22c98176");
		Assert.assertEquals(instance.getSecurityGroup().getId(), "8c3f8730-f63b-48d4-a183-d0c8a091db8c");
		Assert.assertEquals(instance.getFlavor().getId(), "0d922098-553c-4124-80df-e627a1d41a0d");
		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getDatastore(), null);
		Assert.assertEquals(instance.getBackupStrategy().getStartTime(), "22:00:00");
		Assert.assertEquals(instance.getBackupStrategy().getKeepDays().intValue(), 2);
		Assert.assertEquals(instance.getExtendParam().getJobs().get(0).getId(), "ff80808156fd9aee0156fe1fef4a294f");
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
