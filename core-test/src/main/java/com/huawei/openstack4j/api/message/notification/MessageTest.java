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
package com.huawei.openstack4j.api.message.notification;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageIdResponse;
import com.huawei.openstack4j.openstack.message.notification.domain.StructuredMessage;
import com.huawei.openstack4j.openstack.message.notification.domain.TemplatedMessage;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Notification/Message", enabled = true)
public class MessageTest extends AbstractTest {

	@Test
	public void testPublicMessage() throws IOException, InterruptedException {
		respondWith("/message/notification/publish_message_response.json");

		MessageIdResponse response = osv3().notification().messages().publish("topic-id", "subject", "message");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/publish");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("subject", node.get("subject").asText());
		Assert.assertEquals("message", node.get("message").asText());
		
		Assert.assertEquals(response.getId(), "bf94b63a5dfb475994d3ac34664e24f2");
		Assert.assertEquals(response.getRequestId(), "9974c07f6d554a6d827956acbeb4be5f");
	}
	
	@Test
	public void testPublicTemplatedMessage() throws IOException, InterruptedException {
		respondWith("/message/notification/publish_message_response.json");

		Map<String, Object> tagReplacer = Maps.newHashMap();
		tagReplacer.put("user", "tag-user");
		TemplatedMessage templatedMessage = TemplatedMessage.builder().messageTemplateName("name").subject("hello")
				.tags(tagReplacer).build();
		// dont know why not success
		MessageIdResponse message = osv3().notification().messages().publish("topic-id", templatedMessage);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/publish");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("hello", node.get("subject").asText());
		Assert.assertEquals("name", node.get("message_template_name").asText());
		JsonNode tags = node.get("tags");
		Assert.assertEquals("tag-user", tags.get("user").asText());
		
		Assert.assertEquals(message.getId(), "bf94b63a5dfb475994d3ac34664e24f2");
		Assert.assertEquals(message.getRequestId(), "9974c07f6d554a6d827956acbeb4be5f");
	}
	
	@Test
	public void testPublicStructuredMessage() throws IOException, InterruptedException {
		respondWith("/message/notification/publish_message_response.json");

		StructuredMessage structuredMessage = StructuredMessage.builder().subject("hello")
				.defaultMessage("hello, there").emailMessage("hello, email").build();
		MessageIdResponse message = osv3().notification().messages().publish("topic-id", structuredMessage);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/publish");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("hello", node.get("subject").asText());
		
		JsonNode structure = ObjectMapperSingleton.getContext(Object.class).readTree(node.get("message_structure").asText());
		Assert.assertEquals("hello, there", structure.get("default").asText());
		Assert.assertEquals("hello, email", structure.get("email").asText());
		
		Assert.assertEquals(message.getId(), "bf94b63a5dfb475994d3ac34664e24f2");
		Assert.assertEquals(message.getRequestId(), "9974c07f6d554a6d827956acbeb4be5f");
	}


	@Override
	protected Service service() {
		return Service.NOTIFICATION;
	}

}
