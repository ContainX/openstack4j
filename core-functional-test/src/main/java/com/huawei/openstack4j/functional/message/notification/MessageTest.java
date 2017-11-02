/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.functional.message.notification;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;
import org.testng.util.Strings;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageIdResponse;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplate;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplateCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.StructuredMessage;
import com.huawei.openstack4j.openstack.message.notification.domain.Subscription;
import com.huawei.openstack4j.openstack.message.notification.domain.SubscriptionCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.TemplatedMessage;
import com.huawei.openstack4j.openstack.message.notification.domain.Topic;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;

@Test(suiteName = "SimpleMessageNotificatoin/Message/Test")
public class MessageTest extends AbstractTest {

	String name = randomName();
	Topic topic = null;
	Subscription subscription = null;
	MessageTemplate template = null;

	/**
	 * prepare resources
	 */
	@BeforeClass
	public void prepare() {
		// create a new topic for testing
		topic = osclient.notification().topics().create(name, name);

		// create subscription
		SubscriptionCreate subscribe = SubscriptionCreate.builder().topicUrn(topic.getUrn()).endpoint("xmufive@qq.com")
				.protocol(Protocol.EMAIL).remark("sdk-unittest").build();
		subscription = osclient.notification().subscriptions().subscribe(subscribe);

		// create a message template
		MessageTemplateCreate create = MessageTemplateCreate.builder().name(name).protocol(Protocol.Default)
				.locale("zh-cn").content("Hello, {user}").build();
		template = osclient.notification().messageTemplates().create(create);
	}

	/**
	 * clean up resources
	 */
	@AfterClass
	public void cleanup() {
		// delete message template
		TracableRequest delete = osclient.notification().messageTemplates().delete(template.getId());
		Assert.assertTrue(!Strings.isNullOrEmpty(delete.getRequestId()));

		// unsubscribe
		TracableRequest unsubscribe = osclient.notification().subscriptions().unsubscribe(subscription.getUrn());
		Assert.assertTrue(!Strings.isNullOrEmpty(unsubscribe.getRequestId()));

		// delete topic
		osclient.notification().topics().delete(topic.getUrn());
	}

	@Test
	public void testPublishMessage() {
		MessageIdResponse message = osclient.notification().messages().publish(topic.getUrn(), "Hello", "Hello, there");
		Assert.assertNotNull(message.getId());
		Assert.assertNotNull(message.getRequestId());
	}

	@Test
	public void testPublishTemplatedMessage() {
		Map<String, Object> tagReplacer = Maps.newHashMap();
		tagReplacer.put("user", "tag-user");
		TemplatedMessage templatedMessage = TemplatedMessage.builder().messageTemplateName(name).subject("hello")
				.tags(tagReplacer).build();
		MessageIdResponse message = osclient.notification().messages().publish(topic.getUrn(), templatedMessage);
		Assert.assertNotNull(message.getId());
		Assert.assertNotNull(message.getRequestId());
	}

	@Test(priority = 1)
	public void testPublishStructuredMessage() {
		StructuredMessage structuredMessage = StructuredMessage.builder().subject("hello")
				.defaultMessage("hello, there").emailMessage("hello, email").build();
		MessageIdResponse message = osclient.notification().messages().publish(topic.getUrn(), structuredMessage);
		Assert.assertNotNull(message.getId());
		Assert.assertNotNull(message.getRequestId());
	}

}
