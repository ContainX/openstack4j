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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;

@Test(suiteName = "MessageQueue/Queue/Test")
public class QueueTest extends AbstractTest {

	String name = randomName();
	Queue queue = null;

	/**
	 * create a topic for test
	 */
	@BeforeClass
	public void prepare() {
		queue = osclient.messageQueue().queue().create(name, "sdk-unittest");
	}

	/**
	 * after all, delete the topic
	 */
	@AfterClass
	public void cleanup() {
		ActionResponse delete = osclient.messageQueue().queue().delete(queue.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test
	public void testGetTopic() {
		Queue get = osclient.messageQueue().queue().get(queue.getId());
		Assert.assertEquals(get.getName(), name);
		Assert.assertEquals(get.getDescription(), "sdk-unittest");

		queue = get;
	}

	@Test
	public void testListTopic() {
		List<? extends Queue> queues = osclient.messageQueue().queue().list();

		boolean found = false;
		for (Queue queue : queues) {
			if (queue.getId().endsWith(queue.getId())) {
				found = true;
			}
		}
		Assert.assertTrue(found);
	}
}
