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
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplate;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplateCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;
import com.huawei.openstack4j.openstack.message.notification.options.MessageTemplateListOptions;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Notification/MessageTemplate", enabled = true)
public class MessageTemplateTest extends AbstractTest {

	@Test
	public void testCreateMessageTemplate() throws IOException, InterruptedException {
		respondWith("/message/notification/create_message_template_response.json");

		MessageTemplateCreate create = MessageTemplateCreate.builder().name("name").protocol(Protocol.EMAIL)
				.locale("zh-cn").content("content").build();
		MessageTemplate messageTemplate = osv3().notification().messageTemplates().create(create);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/message_template");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("name", node.get("message_template_name").asText());
		Assert.assertEquals("content", node.get("content").asText());
		Assert.assertEquals("email", node.get("protocol").asText());
		Assert.assertEquals("zh-cn", node.get("locale").asText());

		Assert.assertEquals(messageTemplate.getId(), "57ba8dcecda844878c5dd5815b65d10f");
		Assert.assertEquals(messageTemplate.getRequestId(), "ca03efa691624d8eb2dfeba01a1bcf6e");
	}

	@Test
	public void testGetMessageTemplate() throws IOException, InterruptedException {
		respondWith("/message/notification/get_message_template_response.json");
		MessageTemplate template = osv3().notification().messageTemplates().get("message-template-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/message_template/message-template-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(template.getId(), "57ba8dcecda844878c5dd5815b65d10f");
		Assert.assertEquals(template.getName(), "confirm_message");
		Assert.assertEquals(template.getProtocol(), Protocol.HTTPS);
		Assert.assertEquals(template.getLocale(), "en-us");
		Assert.assertEquals(template.getTags(), Lists.newArrayList("topic_id_id4"));
		Assert.assertEquals(template.getContent(),
				"(1/24)You are invited to subscribe to topic({topic_id_id4}). Click the following URL to "
						+ "confirm subscription:(If you do not want to subscribe to this topic, ignore this message.)");
		Assert.assertEquals(template.getUpdateTime().getTime(), 1470126145000L);
		Assert.assertEquals(template.getCreateTime().getTime(), 1470126140000L);

	}

	@Test
	public void testUpdateMessageTemplate() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest updated = osv3().notification().messageTemplates().updateContent("id", "new-content");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/message_template/id");
		Assert.assertEquals(request.getMethod(), "PUT");

		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("new-content", node.get("content").asText());

		Assert.assertEquals(updated.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testDeleteMessageTemplate() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest updated = osv3().notification().messageTemplates().delete("id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/message_template/id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(updated.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testListMessageTemplate() throws IOException, InterruptedException {
		respondWith("/message/notification/list_message_template_response.json");

		MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1).offset(0).name("name")
				.protocol(Protocol.EMAIL);
		List<? extends MessageTemplate> templates = osv3().notification().messageTemplates().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v2/project-id/notifications/message_template?limit=1&protocol=email&offset=0&message_template_name=name");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(templates.size(), 3);
		MessageTemplate template = templates.get(0);

		Assert.assertEquals(template.getId(), "79227dfdf88d4e52a1820ca1eb411635");
		Assert.assertEquals(template.getName(), "confirm_message");
		Assert.assertEquals(template.getLocale(), "en-us");
		Assert.assertEquals(template.getTags(), Lists.newArrayList("topic_urn"));
		Assert.assertEquals(template.getUpdateTime().getTime(), 1470126138000L);
		Assert.assertEquals(template.getCreateTime().getTime(), 1470126138000L);

	}

	@Override
	protected Service service() {
		return Service.NOTIFICATION;
	}

}
