/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.sample.maas;

import static org.testng.Assert.assertTrue;

import org.openstack4j.openstack.maas.domain.Task;
import org.openstack4j.openstack.maas.domain.TaskCreate;
import org.openstack4j.openstack.maas.domain.TaskCreate.Node;
import org.openstack4j.openstack.maas.domain.TaskCreateResp;
import org.openstack4j.openstack.maas.domain.Version;
import org.openstack4j.openstack.maas.options.TaskListOptions;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TaskSample extends AbstractSample {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskSample.class);
	
	@Test
	public void testVersionGet() {
		Version version = osclient.maas().version().get();
		LOGGER.info("{}", version);
		assertTrue(version != null);
	}

//	@Test
//	public void testTaskCreate() {
//		Node srcNode = Node.builder().region("region").ak("ak").sk("sk").objectKey("objectKey").bucket("bucket").build();
//		Node dstNode = Node.builder().region("region").ak("ak").sk("sk").objectKey("objectKey").bucket("bucket").build();
//		TaskCreate task = TaskCreate.builder().srcNode(srcNode ).dstNode(dstNode ).enableKMS(true).threadNum(1).enableTas(false).build();
//		TaskCreateResp create = osclient.maas().task().create(task );
//		LOGGER.info("{}", create);
//	}
	
	@Test
	public void testTaskList() {
		TaskListOptions options = TaskListOptions.create().start(0).limit(10);
		Task[] list = osclient.maas().task().list(options);
		LOGGER.info("{}", list);
	}
	
	@Test
	public void testTaskCount() {
		long count = osclient.maas().task().count();
		LOGGER.info("{}", count);
	}
	
}
