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

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumerGroup;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;

@Test
public class ConsumerGroupTest extends AbstractTest {

	String name = randomName();
	Queue queue = null;
	List<ConsumerGroup> groups = null;
	List<String> groupNames = Lists.newArrayList("consumer-group-1", "consumer-group-2");

	/**
	 * create a topic for test
	 */
	@BeforeClass
	public void prepare() {
		queue = osclient.messageQueue().queue().create(name, "sdk-unittest");

		// create consumer groups
		groups = osclient.messageQueue().consumerGroups().create(queue.getId(), groupNames);
	}

	/**
	 * after all, delete the topic
	 */
	@AfterClass
	public void cleanup() {
		// delete groups
		for (ConsumerGroup consumerGroup : groups) {
			ActionResponse delete = osclient.messageQueue().consumerGroups().delete(queue.getId(),
					consumerGroup.getId());
			Assert.assertTrue(delete.isSuccess());
		}

		ActionResponse delete = osclient.messageQueue().queue().delete(queue.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	public void testListConsumerGroup() {
		List<ConsumerGroup> list = osclient.messageQueue().consumerGroups().list(queue.getId());
		Assert.assertEquals(list.size(), 2);
		
		List<String> names = list.stream().map(g -> g.getName()).collect(Collectors.toList());
		Assert.assertEquals(names, groupNames);
	}
}
