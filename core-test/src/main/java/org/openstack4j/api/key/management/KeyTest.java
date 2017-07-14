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
package org.openstack4j.api.key.management;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.openstack.common.Quota;
import org.openstack4j.openstack.common.Quota.ResourceType;
import org.openstack4j.openstack.key.management.constants.KeyState;
import org.openstack4j.openstack.key.management.domain.Key;
import org.openstack4j.openstack.key.management.domain.Key.Keys;
import org.openstack4j.openstack.key.management.domain.KeyCreate;
import org.openstack4j.openstack.key.management.options.KeyListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "KMS/Key", enabled = true)
public class KeyTest extends AbstractTest {

	String sequence = "919c82d4-8046-4722-9094-35c3c6524cff";

	@Test
	public void testGetKey() throws IOException, InterruptedException {
		respondWith("/kms/get_key_response.json");
		Key key = osv3().keyManagement().keys().get("key-id", sequence);

		validateRequest(server.takeRequest(), "/v1.0/project-id/kms/describe-key");

		Assert.assertEquals(key.getId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(key.getDomainId(), "b168fe00ff56492495a7d22974df2d0b");
		Assert.assertEquals(key.getAlias(), "kms_test");
		Assert.assertEquals(key.getRealm(), "cn-north-1eu-deaaa");
		Assert.assertEquals(key.getDescription(), "");
		Assert.assertEquals(key.getCreationDate().getTime(), 1472442386000L);
		Assert.assertEquals(key.getScheduledDeletionDate(), null);
		Assert.assertEquals(key.getState(), KeyState.Enabled);
		Assert.assertEquals(key.getIsDefaultKey().booleanValue(), false);
		Assert.assertEquals(key.getType(), "1");
	}

	@Test
	public void testListKey() throws IOException, InterruptedException {
		respondWith("/kms/list_key_response.json");

		KeyListOptions options = KeyListOptions.create().limit(10).marker("last-key-id").sequence(sequence);
		Keys keys = osv3().keyManagement().keys().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/list-keys");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);

		Assert.assertEquals(10, node.get("limit").asInt());
		Assert.assertEquals("last-key-id", node.get("marker").asText());
		Assert.assertEquals(sequence, node.get("sequence").asText());

		Assert.assertEquals(keys.getNextMarker(), "");
		Assert.assertEquals(keys.getTruncated().booleanValue(), false);
		Assert.assertEquals(keys.get().size(), 3);
		Assert.assertEquals(keys.get(), Lists.newArrayList("0d0466b0-e727-4d9c-b35d-f84bb474a37f",
				"2e258389-bb1e-4568-a1d5-e1f50adf70ea", "3cf69e11-f15f-4e86-a5d3-a14d22d7521c"));
	}

	@Test
	public void testEnableKey() throws IOException, InterruptedException {
		respondWith("/kms/enable_key_response.json");
		Key enabled = osv3().keyManagement().keys().enable("key-id", sequence);
		validateRequest(server.takeRequest(), "/v1.0/project-id/kms/enable-key");
		Assert.assertEquals(enabled.getId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test
	public void testDisableKey() throws IOException, InterruptedException {
		respondWith("/kms/disable_key_response.json");
		Key enabled = osv3().keyManagement().keys().disable("key-id", sequence);
		validateRequest(server.takeRequest(), "/v1.0/project-id/kms/disable-key");
		Assert.assertEquals(enabled.getId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(enabled.getState(), KeyState.Disabled);
	}

	/**
	 * @param request
	 * @param path 
	 * @return 
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public JsonNode validateRequest(RecordedRequest request, String path) throws IOException, JsonProcessingException {
		Assert.assertEquals(request.getPath(), path);
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("key-id", node.get("key_id").asText());
		Assert.assertEquals(sequence, node.get("sequence").asText());
		return node;
	}

	@Test
	public void testScheduleDeletionKey() throws IOException, InterruptedException {
		respondWith("/kms/delete_key_response.json");

		Key delete = osv3().keyManagement().keys().scheduleDeletion("key-id", 10, sequence);
		JsonNode node = validateRequest(server.takeRequest(), "/v1.0/project-id/kms/schedule-key-deletion");
		Assert.assertEquals(10, node.get("pending_days").asInt());

		Assert.assertEquals(delete.getId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(delete.getState(), KeyState.ScheduledDeletion);
	}

	@Test
	public void testCancelDeletionKey() throws IOException, InterruptedException {
		respondWith("/kms/cancel_delete_key_response.json");

		Key delete = osv3().keyManagement().keys().cancelDeletion("key-id", sequence);
		validateRequest(server.takeRequest(), "/v1.0/project-id/kms/cancel-key-deletion");

		Assert.assertEquals(delete.getId(), "0d0466b0-e727-4d9c-b35d-f84bb474a37f");
		Assert.assertEquals(delete.getState(), KeyState.Disabled);
	}

	@Test
	public void testCreateKey() throws IOException, InterruptedException {
		respondWith("/kms/create_key_response.json");

		KeyCreate create = KeyCreate.builder().alias("alias").description("desc").realm("eu-de").sequence(sequence)
				.build();
		Key key = osv3().keyManagement().keys().create(create);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/create-key");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("alias", node.get("key_alias").asText());
		Assert.assertEquals("desc", node.get("key_description").asText());
		Assert.assertEquals("eu-de", node.get("realm").asText());
		Assert.assertEquals(sequence, node.get("sequence").asText());

		Assert.assertEquals(key.getId(), "bb6a3d22-dc93-47ac-b5bd-88df7ad35f1e");
		Assert.assertEquals(key.getDomainId(), "b168fe00ff56492495a7d22974df2d0b");
	}
	
	@Test
	public void testGetKeyCreatedAmount() throws IOException, InterruptedException {
		respondWith("/kms/get_user_instance_response.json");
		Integer created = osv3().keyManagement().keys().getKeyCreatedAmount();
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/user-instances");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(created.intValue(), 15);
	}
	
	@Test
	public void testListQuotas() throws IOException, InterruptedException {
		respondWith("/kms/list_quota_response.json");
		
		List<Quota> quotas = osv3().keyManagement().keys().quotas();
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/kms/user-quotas");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(quotas.size(), 1);
		Quota quota = quotas.get(0);
		Assert.assertEquals(quota.getUsed().intValue(), 15);
		Assert.assertEquals(quota.getQuota().intValue(), 20);
		Assert.assertEquals(quota.getType(), ResourceType.CMK);
	}

	@Override
	protected Service service() {
		return Service.KEY_MANAGEMENT;
	}

}
