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

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.openstack.message.notification.constant.PushFailedPolicy;
import com.huawei.openstack4j.openstack.message.notification.constant.TopicAttributeName;
import com.huawei.openstack4j.openstack.message.notification.domain.Topic;
import com.huawei.openstack4j.openstack.message.notification.domain.TopicAttributes;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Notification/Topic", enabled = true)
public class TopicTest extends AbstractTest {


	@Test
	public void testGetTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/get_topic_response.json");
		Topic topic = osv3().notification().topics().get("topic-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(topic.getUrn(), "urn:smn:regionId:8bad8a40e0f7462f8c1676e3f93a8183:test_create_topic_v2");
		Assert.assertEquals(topic.getDisplayName(), "test create topic v2");
		Assert.assertEquals(topic.getName(), "test_create_topic_v2");
		Assert.assertEquals(topic.getUpdateTime().getTime(), 1470017798000L);
		Assert.assertEquals(topic.getCreateTime().getTime(), 1470017798000L);
		Assert.assertEquals(topic.getPushPolicy(), PushFailedPolicy.Queue);
		Assert.assertEquals(topic.getRequestId(), "6837531fd3f54550927b930180a706bf");
	}

	@Test
	public void testUpdateTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest updated = osv3().notification().topics().updateDisplayName("topic-id", "new-name");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id");
		Assert.assertEquals(request.getMethod(), "PUT");

		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("new-name", node.get("display_name").asText());

		Assert.assertEquals(updated.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testDeleteTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest updated = osv3().notification().topics().delete("topic-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(updated.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testListTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/list_topic_response.json");
		List<? extends Topic> topics = osv3().notification().topics().list(100, 10);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics?limit=100&offset=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(topics.size(), 1);
		Topic topic = topics.get(0);

		Assert.assertEquals(topic.getUrn(), "urn:smn:regionId:8bad8a40e0f7462f8c1676e3f93a8183:test_topic_v2");
		Assert.assertEquals(topic.getDisplayName(), "testtest");
		Assert.assertEquals(topic.getName(), "test_topic_v1");
		Assert.assertEquals(topic.getPushPolicy(), PushFailedPolicy.Queue);
	}

	@Test
	public void testCreateTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/create_topic_response.json");
		Topic topic = osv3().notification().topics().create("topic-name", "display-name");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("topic-name", node.get("name").asText());
		Assert.assertEquals("display-name", node.get("display_name").asText());

		Assert.assertEquals(topic.getUrn(), "urn:smn:regionId:f96188c7ccaf4ffba0c9aa149ab2bd57:test_topic_v2");
		Assert.assertEquals(topic.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testListTopicAttributes() throws IOException, InterruptedException {
		respondWith("/message/notification/list_topic_attrs_response.json");
		TopicAttributes attrs = osv3().notification().topics().getTopicAttributes("topic-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/attributes");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(attrs.getSmsSignId(), "sms_sign_id");
		Assert.assertEquals(attrs.getIntroduction(), "introduction");
		Assert.assertEquals(attrs.getAccessPolicy(), "access_policy");
	}

	@Test
	public void testUpdateTopicAttribute() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest result = osv3().notification().topics().updateTopicAttribute("topic-id",
				TopicAttributeName.AccessPolicy, "xxx");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/attributes/access_policy");
		Assert.assertEquals(request.getMethod(), "PUT");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("xxx", node.get("value").asText());

		Assert.assertEquals(result.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Test
	public void testDeleteTopicAttribute() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest result = osv3().notification().topics().deleteTopicAttribute("topic-id",
				TopicAttributeName.AccessPolicy);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/attributes/access_policy");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(result.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}
	
	@Test
	public void testDeleteTopicAttributes() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest result = osv3().notification().topics().deleteTopicAttributes("topic-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/attributes");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(result.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Override
	protected Service service() {
		return Service.NOTIFICATION;
	}

}
