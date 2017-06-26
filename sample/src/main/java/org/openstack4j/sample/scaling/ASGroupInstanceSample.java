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
package org.openstack4j.sample.scaling;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

public class ASGroupInstanceSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASGroupInstanceSample.class);

	@Test(priority = 2)
	public void testListAutoScalingGroupInstance() {
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<? extends ScalingGroupInstance> list = osclient.autoScaling().groupInstances().list(groupId);
		logger.info("{}", list);
		if (list != null && !list.isEmpty()) {
			for (ScalingGroupInstance instance : list) {
				assertTrue(groupId.equals(instance.getGroupId()));
			}
		}

		ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create().lifeCycleState("INSERVICE")
				.heathStatus("NORMAL").limit(2);
		List<? extends ScalingGroupInstance> filterList = osclient.autoScaling().groupInstances().list(groupId,
				options);
		logger.info("{}", filterList);
		if (filterList != null && !filterList.isEmpty()) {
			for (ScalingGroupInstance instance : filterList) {
				assertTrue("INSERVICE".equalsIgnoreCase(instance.getLifeCycleState()));
				assertTrue("NORMAL".equalsIgnoreCase(instance.getHealthStatus()));
			}
		}
	}

	@Test(priority = 1)
	public void testDeleteAutoScalingGroupInstance() {
		String instanceId = "475db405-11b4-47f6-bb9d-f3bcbc7ac27f";
		ActionResponse resp = osclient.autoScaling().groupInstances().delete(instanceId, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test(priority = 0)
	public void testBatchOperateAutoScalingGroupInstance() throws InterruptedException {
		String groupId = "8a2462e3-6ae8-4d86-bd89-4497836fe022";
		List<String> instanceIds = Lists.newArrayList("ebcc24c9-64b6-4f61-ab30-46e496ef31c5");

		ActionResponse resp = osclient.autoScaling().groupInstances().batchAdd(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
		//wait server to initial new group instance life cycle status, 
		//otherwise the delete test case will fail for group instance not found
		TimeUnit.MINUTES.sleep(1);
		
		resp = osclient.autoScaling().groupInstances().batchRemove(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}
	
}
