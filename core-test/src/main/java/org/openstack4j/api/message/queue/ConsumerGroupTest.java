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
package org.openstack4j.api.message.queue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.message.queue.domain.ConsumerGroup;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "MessageQueue/ConsumerGroup", enabled = true)
public class ConsumerGroupTest extends AbstractTest {

	@Test
	public void testCreateConsumerGroup() throws IOException, InterruptedException {
		respondWith("/message/queue/create_consumer_group_response.json");

		List<ConsumerGroup> groups = osv3().messageQueue().consumerGroups().create("queue-id",
				Lists.newArrayList("g1", "g2"));

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id/groups");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		JsonNode groupsNode = node.get("groups");
		
		Assert.assertEquals(groupsNode.size(), 2);
		Assert.assertEquals(groupsNode.get(0).get("name").asText(), "g1");
		Assert.assertEquals(groupsNode.get(1).get("name").asText(), "g2");

		Assert.assertEquals(groups.size(), 1);
		ConsumerGroup consumerGroup = groups.get(0);
		Assert.assertEquals(consumerGroup.getId(), "g-5ec247fd-d4a2-4d4f-9876-e4ff3280c461");
		Assert.assertEquals(consumerGroup.getName(), "abcDffD");
	}
	
	@Test
	public void testListConsumerGroup() throws IOException, InterruptedException {
		respondWith("/message/queue/list_consumer_group_response.json");

		List<ConsumerGroup> groups = osv3().messageQueue().consumerGroups().list("queue-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id/groups");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(groups.size(), 2);
		ConsumerGroup consumerGroup = groups.get(0);
		Assert.assertEquals(consumerGroup.getId(), "g-5ec247fd-d4a2-4d4f-9876-e4ff3280c461");
		Assert.assertEquals(consumerGroup.getName(), "abcDffD");
		Assert.assertEquals(consumerGroup.getAvailableMessages().intValue(), 0);
		Assert.assertEquals(consumerGroup.getConsumedMessages().intValue(), 0);
		Assert.assertEquals(consumerGroup.getProducedMessages().intValue(), 0);
	}

	
	@Test
	public void testDeleteQueue() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().messageQueue().consumerGroups().delete("queue-id", "group-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id/groups/group-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertTrue(response.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.MESSAGE_QUEUE;
	}

}
