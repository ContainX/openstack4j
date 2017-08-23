/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.storage;


import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupCreate;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "VolumeBackup/AsyncVolumeBackup")
public class AsyncVolumeBackupTests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.VOLUME_BACKUP;
	}

	@Test
	public void asyncCreateVolumeBackup() throws Exception {

		respondWith(200, "{\"job_id\": \"job-id\"}");

		// 新的创建备份接口 创建一个备份
		AsyncVolumeBackupCreate vbc = Builders.asyncVolumeBackupCreate().name("new-backup").volumeId("volume-id")
				.build();
		final AsyncVolumeBackupJob job = osv3().blockStorage().asyncBackups().create(vbc);
		assertEquals("job-id", job.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/cloudbackups"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode response = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		assertTrue(response.has("backup"));
		JsonNode backup = response.get("backup");
		assertEquals("volume-id", backup.get("volume_id").asText());
		assertEquals("new-backup", backup.get("name").asText());
	}

	@Test
	public void asyncRestoreVolumeBackup() throws Exception {

		respondWith(200, "{\"job_id\": \"job-id\"}");

		AsyncVolumeBackupJob job = osv3().blockStorage().asyncBackups().restore("volume-backup-id", "volume-id");
		assertEquals("job-id", job.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/cloudbackups/volume-backup-id/restore"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode response = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		assertTrue(response.has("restore"));
		JsonNode backup = response.get("restore");
		assertEquals("volume-id", backup.get("volume_id").asText());
	}
	
	@Test
	public void testGetAsyncJobs() throws Exception {
		respondWith("/storage/v1/volumebackup_get_job_response.json");

		AsyncVolumeBackupJob job = osv3().blockStorage().jobs().get("job-id");

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v1/project-id/jobs/job-id"));
		assertEquals(request.getMethod(), "GET");
		
		
		assertEquals("4010b39b5281d3590152874bfa3b1604", job.getId());
		assertEquals("bksCreateBackup", job.getType());
		assertEquals(AsyncVolumeBackupJob.Status.SUCCESS, job.getStatus());
		assertEquals(1453997649466L, job.getBeginTime().getTime());
	}

}
