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

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;
import com.huawei.openstack4j.openstack.message.notification.domain.Subscription;
import com.huawei.openstack4j.openstack.message.notification.domain.SubscriptionCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.Topic;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;

@Test(suiteName = "SimpleMessageNotificatoin/Subscription/Test")
public class SubscriptionTest extends AbstractTest {

	String name = randomName();
	Topic topic = null;
	Subscription subscription = null;

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
	}

	/**
	 * clean up resources
	 */
	@AfterClass
	public void cleanup() {
		// unsubscribe
		TracableRequest unsubscribe = osclient.notification().subscriptions().unsubscribe(subscription.getUrn());
		Assert.assertTrue(!Strings.isNullOrEmpty(unsubscribe.getRequestId()));
		// delete topic
		osclient.notification().topics().delete(topic.getUrn());
	}

	@Test(priority = 1)
	public void testListSubscriptions() {
		List<? extends Subscription> subscriptions = osclient.notification().subscriptions().listByTopic(topic.getUrn(),
				100, 0);

		Subscription found = null;
		for (Subscription subscription : subscriptions) {
			if (subscription.getUrn().equals(subscription.getUrn())) {
				found = subscription;
				break;
			}
		}
		Assert.assertNotNull(found);
		Assert.assertEquals(found.getEndpoint(), "xmufive@qq.com");
		Assert.assertEquals(found.getProtocol(), Protocol.EMAIL);
		Assert.assertEquals(found.getRemark(), "sdk-unittest");
	}

	@Test(priority = 1)
	public void testListSubscriptions2() {
		List<? extends Subscription> subscriptions = osclient.notification().subscriptions().list(100, 0);

		Subscription found = null;
		for (Subscription subscription : subscriptions) {
			if (subscription.getUrn().equals(subscription.getUrn())) {
				found = subscription;
				break;
			}
		}
		Assert.assertNotNull(found);
		Assert.assertEquals(found.getEndpoint(), "xmufive@qq.com");
		Assert.assertEquals(found.getProtocol(), Protocol.EMAIL);
		Assert.assertEquals(found.getRemark(), "sdk-unittest");
	}

}
