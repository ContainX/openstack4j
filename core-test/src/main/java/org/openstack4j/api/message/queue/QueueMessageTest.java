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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.message.queue.constant.ConsumeStatus;
import org.openstack4j.openstack.message.queue.domain.ConsumeConfirmResponse;
import org.openstack4j.openstack.message.queue.domain.QueueMessage;
import org.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.google.common.collect.Maps;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "MessageQueue/QueueMessage", enabled = true)
public class QueueMessageTest extends AbstractTest {

	@Test
	public void testProduceSingleMessage() throws IOException, InterruptedException {
		respondWith(200);

		HashMap<String, Object> attributes1 = Maps.newHashMap();
		attributes1.put("attr1", 1);
		attributes1.put("attr2", false);
		QueueMessage message = QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build();

		ActionResponse response = osv3().messageQueue().messages().produce("queue-id", message);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id/messages");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();

		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		JsonNode messagesNode = node.get("messages");
		Assert.assertEquals(messagesNode.size(), 1);
		Assert.assertEquals(messagesNode.get(0).get("body").asText(), "sdk-unittests");
		Assert.assertEquals(messagesNode.get(0).get("attributes").get("attr1").asInt(), 1);
		Assert.assertEquals(messagesNode.get(0).get("attributes").get("attr2").asBoolean(), false);

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testProduceMessages() throws IOException, InterruptedException {
		respondWith(200);

		List<QueueMessage> messages = Lists.newArrayList();

		HashMap<String, Object> attributes1 = Maps.newHashMap();
		attributes1.put("attr1", 1);
		attributes1.put("attr2", false);
		messages.add(QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build());

		HashMap<String, Object> attributes2 = Maps.newHashMap();
		attributes2.put("attr1", "value1");
		attributes2.put("attr2", false);
		messages.add(QueueMessage.builder().body(attributes2).attributes(attributes2).build());

		ActionResponse response = osv3().messageQueue().messages().produce("queue-id", messages);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id/messages");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();

		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		JsonNode messagesNode = node.get("messages");
		Assert.assertEquals(messagesNode.size(), 2);
		Assert.assertEquals(messagesNode.get(0).get("body").asText(), "sdk-unittests");
		Assert.assertEquals(messagesNode.get(0).get("attributes").get("attr1").asInt(), 1);
		Assert.assertEquals(messagesNode.get(0).get("attributes").get("attr2").asBoolean(), false);
		Assert.assertEquals(messagesNode.get(1).get("body").get("attr1").asText(), "value1");
		Assert.assertEquals(messagesNode.get(1).get("body").get("attr2").asBoolean(), false);
		Assert.assertEquals(messagesNode.get(1).get("attributes").get("attr1").asText(), "value1");
		Assert.assertEquals(messagesNode.get(1).get("attributes").get("attr2").asBoolean(), false);

		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testConsumeMessage() throws IOException, InterruptedException {
		respondWith("/message/queue/consume_messages_response.json");

		List<QueueMessageWithHandler> messages = osv3().messageQueue().messages().consume("queue-id", "consumer-group-id",
				5, 10);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.0/project-id/queues/queue-id/groups/consumer-group-id/messages?max_msgs=5&time_wait=10");
		Assert.assertEquals(request.getMethod(), "GET");
		

		Assert.assertEquals(messages.size(), 2);
		
		HashMap<String, Object> attributes = Maps.newHashMap();
		attributes.put("attribute1", "value1");
		attributes.put("attribute2", "value2");
		
		HashMap<String, Object> body = Maps.newHashMap();
		body.put("foo", "123=");
		
		QueueMessageWithHandler message = messages.get(0);
		Assert.assertEquals(message.getHandler(), "eyJjZyI6Im15X2pzb25fZ3JvdXAiLCJjaSI6InJlc3QtY29uc3VtZXItYzNlNThiNjEtYzA0NC00NGJkLTkxM2ItZDgzNjljNmJhYTQxIiwiY291bnQiOjAsIm9mZnNldCI6MCwicCI6MCwidCI6InRlc3QyIn0=");
		Assert.assertEquals(message.getMessage().getBody(), body);
		Assert.assertEquals(message.getMessage().getAttributes(), attributes);
	}
	
	@Test
	public void testConfirmConsuming() throws IOException, InterruptedException {
		respondWith("/message/queue/confirm_consuming_response.json");

		Map<String, ConsumeStatus> consumeResult = Maps.newHashMap();
		consumeResult.put("handler1", ConsumeStatus.SUCCESS);
		consumeResult.put("handler2", ConsumeStatus.SUCCESS);

		ConsumeConfirmResponse confirmConsuming = osv3().messageQueue().messages().confirmConsuming("queue-id", "consumer-group-id", consumeResult);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.0/project-id/queues/queue-id/groups/consumer-group-id/ack");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		JsonNode messageNode = node.get("message");
		
		Assert.assertEquals(messageNode.size(), 2);
		Assert.assertEquals(messageNode.get(0).get("handler").asText(), "handler1");
		Assert.assertEquals(messageNode.get(1).get("handler").asText(), "handler2");
		Assert.assertEquals(messageNode.get(0).get("status").asText(), "success");
		Assert.assertEquals(messageNode.get(1).get("status").asText(), "success");

		Assert.assertEquals(confirmConsuming.getSuccess().intValue(), 1);
		Assert.assertEquals(confirmConsuming.getFail().intValue(), 2);
	}

	@Override
	protected Service service() {
		return Service.MESSAGE_QUEUE;
	}

}
