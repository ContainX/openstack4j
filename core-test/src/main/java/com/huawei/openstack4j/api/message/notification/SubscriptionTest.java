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
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;
import com.huawei.openstack4j.openstack.message.notification.constant.SubscriptionStatus;
import com.huawei.openstack4j.openstack.message.notification.domain.Subscription;
import com.huawei.openstack4j.openstack.message.notification.domain.SubscriptionCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Notification/Subscription", enabled = true)
public class SubscriptionTest extends AbstractTest {

	@Test
	public void testListSubscription() throws IOException, InterruptedException {
		respondWith("/message/notification/list_subscription_response.json");
		List<? extends Subscription> subscriptions = osv3().notification().subscriptions().list(100, 10);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/subscriptions?limit=100&offset=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(subscriptions.size(), 1);
		Subscription subscription = subscriptions.get(0);

		Assert.assertEquals(subscription.getUrn(),
				"urn:smn:regionId:762bdb3251034f268af0e395c53ea09b:test_topic_v1:a2d52a9f5c3b47f48c3fafb177a58796");
		Assert.assertEquals(subscription.getTopicUrn(),
				"urn:smn:regionId:762bdb3251034f268af0e395c53ea09b:test_topic_v1");
		Assert.assertEquals(subscription.getProtocol(), Protocol.EMAIL);
		Assert.assertEquals(subscription.getOwner(), "762bdb3251034f268af0e395c53ea09b");
		Assert.assertEquals(subscription.getEndpoint(), "xx@xx.com");
		Assert.assertEquals(subscription.getRemark(), "");
		Assert.assertEquals(subscription.getStatus(), SubscriptionStatus.NotConfirmed);
	}

	@Test
	public void testListSubscriptionOfTopic() throws IOException, InterruptedException {
		respondWith("/message/notification/list_subscription_of_topic_response.json");
		List<? extends Subscription> subscriptions = osv3().notification().subscriptions().listByTopic("topic-id", 100,
				10);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v2/project-id/notifications/topics/topic-id/subscriptions?limit=100&offset=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(subscriptions.size(), 2);
		Subscription subscription = subscriptions.get(0);

		Assert.assertEquals(subscription.getUrn(),
				"urn:smn:regionId:762bdb3251034f268af0e395c53ea09b:test_topic_v1:2e778e84408e44058e6cbc6d3c377837");
		Assert.assertEquals(subscription.getTopicUrn(),
				"urn:smn:regionId:762bdb3251034f268af0e395c53ea09b:test_topic_v1");
		Assert.assertEquals(subscription.getProtocol(), Protocol.SMS);
		Assert.assertEquals(subscription.getOwner(), "762bdb3251034f268af0e395c53ea09b");
		Assert.assertEquals(subscription.getEndpoint(), "xxxxxxxxxx");
		Assert.assertEquals(subscription.getRemark(), "");
		Assert.assertEquals(subscription.getStatus(), SubscriptionStatus.NotConfirmed);
	}

	@Test
	public void testSubscribe() throws IOException, InterruptedException {
		respondWith("/message/notification/create_subscription_response.json");
		SubscriptionCreate subscribe = SubscriptionCreate.builder().topicUrn("topic-id").endpoint("xx@xx.com")
				.protocol(Protocol.EMAIL).remark("sdk-unittest").build();
		Subscription subscription = osv3().notification().subscriptions().subscribe(subscribe);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/topics/topic-id/subscriptions");
		Assert.assertEquals(request.getMethod(), "POST");

		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("email", node.get("protocol").asText());
		Assert.assertEquals("xx@xx.com", node.get("endpoint").asText());
		Assert.assertEquals("sdk-unittest", node.get("remark").asText());

		Assert.assertEquals(subscription.getUrn(),
				"urn:smn:regionId:762bdb3251034f268af0e395c53ea09b:test_topic_v1:2e778e84408e44058e6cbc6d3c377837");
		Assert.assertEquals(subscription.getRequestId(), "fdbabe38ead6482b8574f82a3d1168e9");
	}
	
	@Test
	public void testUnsubscribe() throws IOException, InterruptedException {
		respondWith("/message/notification/request_id_response.json");
		TracableRequest unsub = osv3().notification().subscriptions().unsubscribe("subscription-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/subscriptions/subscription-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(unsub.getRequestId(), "6a63a18b8bab40ffb71ebd9cb80d0085");
	}

	@Override
	protected Service service() {
		return Service.NOTIFICATION;
	}

}
