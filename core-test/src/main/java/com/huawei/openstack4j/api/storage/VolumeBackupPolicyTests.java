/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC tld                                       
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

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupPolicyStatus;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupScheduledPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask.BackupTaskStatus;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource.VolumeBackupPolicyResourceActionResult;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicy;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupScheduledPolicy;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "VolumeBackup/policies")
public class VolumeBackupPolicyTests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.VOLUME_BACKUP;
	}

	@Test
	public void testCreatePolicy() throws Exception {
		respondWith(200, "{\"backup_policy_id\": \"policy-id\"}");

		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(10)
				.maxBackupAmount(10).retainFirstBackupOfCurrentMonth(true).startTime("01:00")
				.status(VolumeBackupPolicyStatus.ON).build();
		VBSVolumeBackupPolicy create = VBSVolumeBackupPolicy.builder().name("name").scheduledPolicy(scheduledPolicy)
				.build();
		VolumeBackupPolicy policy = osv3().blockStorage().policies().create(create);
		assertEquals("policy-id", policy.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);

		assertEquals("name", post.get("backup_policy_name").asText());
		JsonNode scheduled = post.get("scheduled_policy");
		assertEquals("Y", scheduled.get("remain_first_backup_of_curMonth").asText());
		assertEquals(10, scheduled.get("rentention_num").asInt());
		assertEquals(10, scheduled.get("frequency").asInt());
		assertEquals("01:00", scheduled.get("start_time").asText());
		assertEquals("ON", scheduled.get("status").asText());
	}

	@Test
	public void testUpdatePolicy() throws Exception {
		respondWith(200, "{\"backup_policy_id\": \"policy-id\"}");

		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().maxBackupAmount(10)
				.retainFirstBackupOfCurrentMonth(false).startTime("02:00").status(VolumeBackupPolicyStatus.OFF).build();
		VBSVolumeBackupPolicy updated = VBSVolumeBackupPolicy.builder().id("policy-id").scheduledPolicy(scheduledPolicy)
				.build();
		VolumeBackupPolicy policy = osv3().blockStorage().policies().update(updated);
		assertEquals("policy-id", policy.getId());

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v2/project-id/backuppolicy/policy-id");
		assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		JsonNode scheduled = post.get("scheduled_policy");
		assertEquals("N", scheduled.get("remain_first_backup_of_curMonth").asText());
		assertEquals(10, scheduled.get("rentention_num").asInt());
		assertEquals("02:00", scheduled.get("start_time").asText());
		assertEquals("OFF", scheduled.get("status").asText());
	}

	@Test
	public void testListPolicy() throws Exception {
		respondWith("/storage/v1/policy_list.json");
		List<? extends VolumeBackupPolicy> policies = osv3().blockStorage().policies().list();
		assertEquals(2, policies.size());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy"));
		assertEquals(request.getMethod(), "GET");

		VolumeBackupPolicy policy = policies.get(0);
		assertEquals("XX", policy.getId());
		assertEquals("plan01", policy.getName());
		VolumeBackupScheduledPolicy scheduled = policy.getScheduledPolicy();
		assertFalse(scheduled.getRetainFirstBackupOfCurrentMonth());
		assertEquals(10, scheduled.getMaxBackupAmount().intValue());
		assertEquals(1, scheduled.getFrequency().intValue());
		assertEquals("12:00", scheduled.getStartTime());
		assertEquals(VolumeBackupPolicyStatus.ON, scheduled.getStatus());
	}

	@Test
	public void testDeletePolicy() throws Exception {
		respondWith(200);
		ActionResponse delete = osv3().blockStorage().policies().delete("policy-id");
		assertTrue(delete.isSuccess());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy/policy-id"));
		assertEquals(request.getMethod(), "DELETE");
	}

	@Test
	public void testDisablePolicy() throws Exception {
		respondWith(200, "{\"backup_policy_id\": \"policy-id\"}");
		VolumeBackupPolicy policy = osv3().blockStorage().policies().disable("policy-id");
		assertEquals("policy-id", policy.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy/policy-id"));
		assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		JsonNode scheduled = post.get("scheduled_policy");
		assertEquals("OFF", scheduled.get("status").asText());
	}

	@Test
	public void testEnablePolicy() throws Exception {
		respondWith(200, "{\"backup_policy_id\": \"policy-id\"}");
		VolumeBackupPolicy policy = osv3().blockStorage().policies().enable("policy-id");
		assertEquals("policy-id", policy.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy/policy-id"));
		assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		JsonNode scheduled = post.get("scheduled_policy");
		assertEquals("ON", scheduled.get("status").asText());
	}

	@Test
	public void testExecutePolicy() throws Exception {
		respondWith(200);
		ActionResponse response = osv3().blockStorage().policies().execute("policy-id");
		assertTrue(response.isSuccess());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy/policy-id/action"));
		assertEquals(request.getMethod(), "POST");
	}

	@Test
	public void testLinkResource() throws Exception {
		respondWith("/storage/v1/policy_link_resources_response.json");
		ArrayList<String> resources = Lists.newArrayList("r1", "r2");
		VolumeBackupPolicyResourceActionResult result = osv3().blockStorage().policies().linkResources("policy-id",
				resources);
		assertEquals(1, result.getSuccessResources().size());
		VolumeBackupPolicyResource resource = result.getSuccessResources().get(0);
		assertEquals("bce8d47a-af17-4169-901f-4c7ae9f29c2c", resource.getId());
		assertEquals("volume", resource.getType());
		assertEquals("eu-de-01sa-brazil-1cn-north-1", resource.getAvailabilityZone());
		assertEquals("pod01.eu-de-01sa-brazil-1cn-north-1", resource.getOsVolHostAttr());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicyresources"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		assertEquals("policy-id", post.get("backup_policy_id").asText());
		assertTrue(post.get("resources").isArray());
		JsonNode r = post.get("resources").get(0);
		assertEquals("r1", r.get("resource_id").asText());
		assertEquals("volume", r.get("resource_type").asText());
	}

	@Test
	public void testUnlinkResource() throws Exception {
		respondWith("/storage/v1/policy_unlink_resources_response.json");
		ArrayList<String> resources = Lists.newArrayList("r1", "r2");
		VolumeBackupPolicyResourceActionResult result = osv3().blockStorage().policies().unlinkResources("policy-id",
				resources);
		assertEquals(1, result.getFailResources().size());

		VolumeBackupPolicyResource resource = result.getFailResources().get(0);
		assertEquals("bbba7509-f457-4732-97f1-a8e24b6ed9bc", resource.getId());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicyresources/policy-id/deleted_resources"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode post = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		assertTrue(post.get("resources").isArray());
		JsonNode r = post.get("resources").get(0);
		assertEquals("r1", r.get("resource_id").asText());
	}

	@Test
	public void testListTask() throws Exception {
		respondWith("/storage/v1/policy_task_list_response.json");
		List<? extends VolumeBackupPolicyBackupTask> tasks = osv3().blockStorage().policies().tasks("policy-id", null);
		assertEquals(13, tasks.size());

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v2/project-id/backuppolicy/policy-id/backuptasks"));
		assertEquals(request.getMethod(), "GET");
		
		VolumeBackupPolicyBackupTask task = tasks.get(0);
		assertEquals("7679fd78-838e-4f9f-8366-06a5c7ebf31d", task.getId());
		assertEquals(BackupTaskStatus.EXECUTE_FAIL, task.getStatus());
		assertEquals("autobk_829a", task.getBackupName());
		assertEquals("ac1c4557-d051-405f-a085-e99f6b6137c3", task.getResourceId());
		assertEquals("volume", task.getResourceType());
	}
}
