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

import java.util.Collection;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.message.notification.constant.TopicAttributeName;
import com.huawei.openstack4j.openstack.message.notification.domain.Topic;
import com.huawei.openstack4j.openstack.message.notification.domain.TopicAttributes;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;

@Test(suiteName = "SimpleMessageNotificatoin/Topic/Test")
public class TopicTest extends AbstractTest {

	String name = randomName();
	Topic topic = null;

	/**
	 * create a topic for test
	 */
	@BeforeClass
	public void prepare() {
		topic = osclient.notification().topics().create(name, name);
	}

	/**
	 * after all, delete the topic
	 */
	@AfterClass
	public void cleanup() {
		TracableRequest delete = osclient.notification().topics().delete(topic.getUrn());
		Assert.assertFalse(Strings.isNullOrEmpty(delete.getRequestId()));
	}

	@Test(priority = 1)
	public void testGetTopic() {
		Topic get = osclient.notification().topics().get(topic.getUrn());
		Assert.assertEquals(get.getName(), name);
		Assert.assertEquals(get.getDisplayName(), name);
		Assert.assertFalse(Strings.isNullOrEmpty(get.getRequestId()));

		topic = get;
	}

	@Test(priority = 2)
	public void testUpdateDisplayName() {
		TracableRequest updated = osclient.notification().topics().updateDisplayName(topic.getUrn(), "SDK-unittest");
		Assert.assertFalse(Strings.isNullOrEmpty(updated.getRequestId()));

		// fetch topic for assert
		Topic get = osclient.notification().topics().get(topic.getUrn());
		Assert.assertEquals(get.getName(), name);
		Assert.assertEquals(get.getDisplayName(), "SDK-unittest");
	}

	@Test(priority = 4)
	public void testListTopics() {
		List<? extends Topic> topics = osclient.notification().topics().list(100, 0);
		// maybe bug here, but it should not happen, topics is sort by create time,
		// so we could just assume that the topic we create is in the top 100 list
		Collection<String> topicUrnList = Collections2.transform(topics, new Function<Topic, String>() {
			public String apply(Topic input) {
				return input.getUrn();
			}
		});
		Assert.assertTrue(topicUrnList.contains(topic.getUrn()));
	}

	@Test(priority = 6)
	public void testUpdateTopicAttribute() {
		TracableRequest request = osclient.notification().topics().updateTopicAttribute(topic.getUrn(),
				TopicAttributeName.Introduction, "sdk-unittest");
		Assert.assertFalse(Strings.isNullOrEmpty(request.getRequestId()));
	}

	@Test(priority = 7, dependsOnMethods = { "testUpdateTopicAttribute" })
	public void testGetTopicAttribute() {
		String attr = osclient.notification().topics().getTopicAttribute(topic.getUrn(),
				TopicAttributeName.Introduction);
		Assert.assertEquals(attr, "sdk-unittest");
	}

	@Test(priority = 8, dependsOnMethods = { "testGetTopicAttribute" })
	public void testDeleteTopicAttribute() {
		TracableRequest request = osclient.notification().topics().deleteTopicAttribute(topic.getUrn(),
				TopicAttributeName.Introduction);
		Assert.assertFalse(Strings.isNullOrEmpty(request.getRequestId()));

		String attr = osclient.notification().topics().getTopicAttribute(topic.getUrn(),
				TopicAttributeName.Introduction);
		Assert.assertTrue(Strings.isNullOrEmpty(attr));
	}

	@Test(priority = 9, dependsOnMethods = { "testDeleteTopicAttribute" })
	public void testListAttributes() {
		osclient.notification().topics().updateTopicAttribute(topic.getUrn(), TopicAttributeName.Introduction,
				"sdk-unittest");
		// what to setup?
		// osclient.notification().topics().updateTopicAttribute(topic.getUrn(), TopicAttributeName.AccessPolicy,
		// "{}");
		// only enterprise user can update sms_sign_id
		// osclient.notification().topics().updateTopicAttribute(topic.getUrn(), TopicAttributeName.SMSSignId,
		// "sdk-unittest");

		TopicAttributes attrs = osclient.notification().topics().getTopicAttributes(topic.getUrn());
		Assert.assertEquals(attrs.getIntroduction(), "sdk-unittest");
		// Assert.assertEquals(attrs.getAccessPolicy(), "{}");
		// Assert.assertEquals(attrs.getSmsSignId(), "sdk-unittest");
	}

	@Test(priority = 10, dependsOnMethods = { "testListAttributes" })
	public void testDeleteAllAttributes() {
		TracableRequest request = osclient.notification().topics().deleteTopicAttributes(topic.getUrn());
		Assert.assertFalse(Strings.isNullOrEmpty(request.getRequestId()));

		TopicAttributes attrs = osclient.notification().topics().getTopicAttributes(topic.getUrn());
		Assert.assertTrue(Strings.isNullOrEmpty(attrs.getIntroduction()));
		Assert.assertTrue(Strings.isNullOrEmpty(attrs.getAccessPolicy()));
		Assert.assertTrue(Strings.isNullOrEmpty(attrs.getSmsSignId()));
	}
}
