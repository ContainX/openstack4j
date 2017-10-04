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
package com.huawei.openstack4j.functional.message.queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirmResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumerGroup;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessage;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueService;
import com.huawei.openstack4j.openstack.message.queue.internal.QueueService;

@Test
public class QueueMessageTest extends AbstractTest {

	String name = randomName();
	Queue queue = null;
	List<ConsumerGroup> groups = null;
	List<String> groupNames = Lists.newArrayList("consumer-group-1", "consumer-group-2");

	@BeforeClass
	public void prepare() {
		// create a queue
		MessageQueueService messageQueue = osclient.messageQueue();
		QueueService queueService = messageQueue.queue();
		queue = queueService.create(name, "sdk-unittest");

		// create consumer groups
		groups = messageQueue.consumerGroups().create(queue.getId(), groupNames);
	}

	@AfterClass
	public void cleanup() {
		// delete groups
		for (ConsumerGroup consumerGroup : groups) {
			ActionResponse delete = osclient.messageQueue().consumerGroups().delete(queue.getId(),
					consumerGroup.getId());
			Assert.assertTrue(delete.isSuccess());
		}

		// delete queue
		ActionResponse delete = osclient.messageQueue().queue().delete(queue.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test(priority = 1)
	public void testProduceMessage() {

		HashMap<String, Object> attributes1 = Maps.newHashMap();
		attributes1.put("attr1", 1);
		attributes1.put("attr2", false);
		QueueMessage message = QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build();

		ActionResponse produce = osclient.messageQueue().messages().produce(queue.getId(), message);
		Assert.assertTrue(produce.isSuccess());
	}

	@Test(priority = 2)
	public void testProduceMessages() {
		List<QueueMessage> messages = Lists.newArrayList();

		HashMap<String, Object> attributes1 = Maps.newHashMap();
		attributes1.put("attr1", 1);
		attributes1.put("attr2", false);
		messages.add(QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build());

		HashMap<String, Object> attributes2 = Maps.newHashMap();
		attributes2.put("attr1", "value1");
		attributes2.put("attr2", false);
		messages.add(QueueMessage.builder().body(attributes2).attributes(attributes2).build());

		ActionResponse produce = osclient.messageQueue().messages().produce(queue.getId(), messages);
		Assert.assertTrue(produce.isSuccess());
	}

	@Test(dependsOnMethods = { "testProduceMessage", "testProduceMessages" })
	public void testConsumeMessages() {
		ConsumerGroup consumerGroup1 = groups.get(0);

		List<QueueMessageWithHandler> all = Lists.newArrayList();
		for (int i = 0; i < 3; i++) {
			List<QueueMessageWithHandler> temp = osclient.messageQueue().messages().consume(queue.getId(),
					consumerGroup1.getId(), 5, 10);
			all.addAll(temp);
		}
		Assert.assertEquals(all.size(), 3);

		for (QueueMessageWithHandler queueMessageWithHandler : all) {
			Assert.assertNotNull(queueMessageWithHandler.getHandler());
			Assert.assertNotNull(queueMessageWithHandler.getMessage());
			Assert.assertNotNull(queueMessageWithHandler.getMessage().getBody());
			Assert.assertNotNull(queueMessageWithHandler.getMessage().getAttributes());
		}

	}

	@Test(dependsOnMethods = { "testProduceMessage", "testProduceMessages" })
	public void testConfirmConsumeMessages() {
		ConsumerGroup consumerGroup2 = groups.get(1);

		// get all messages
		List<QueueMessageWithHandler> all = osclient.messageQueue().messages().consume(queue.getId(),
				consumerGroup2.getId(), 5, 10);

		Map<String, ConsumeStatus> consumeResult = Maps.newHashMap();
		for (QueueMessageWithHandler queueMessageWithHandler : all) {
			consumeResult.put(queueMessageWithHandler.getHandler(), ConsumeStatus.SUCCESS);
		}

		ConsumeConfirmResponse confirmConsuming = osclient.messageQueue().messages().confirmConsuming(queue.getId(), consumerGroup2.getId(), consumeResult);
		Assert.assertEquals(confirmConsuming.getSuccess().intValue(), all.size());
	}

}
