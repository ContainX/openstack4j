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
package com.huawei.openstack4j.sample.scaling;

import static org.testng.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroupInstance;
import com.huawei.openstack4j.model.scaling.ScalingGroupInstance.HealthStatus;
import com.huawei.openstack4j.model.scaling.ScalingGroupInstance.LifeCycleState;
import com.huawei.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;
import com.huawei.openstack4j.sample.AbstractSample;

public class ASGroupInstanceSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASGroupInstanceSample.class);

	@Test(priority = 2)
	public void testListAutoScalingGroupInstance() {
		String groupId = "588b4592-0998-4722-b51d-e6dbc574ec32";
		List<? extends ScalingGroupInstance> list = osclient.autoScaling().groupInstances().list(groupId);
		logger.info("{}", list);
		if (list != null && !list.isEmpty()) {
			for (ScalingGroupInstance instance : list) {
				assertTrue(groupId.equals(instance.getGroupId()));
			}
		}

		ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create()
				.lifeCycleState(LifeCycleState.INSERVICE)
				.heathStatus(HealthStatus.NORMAL).limit(2);
		List<? extends ScalingGroupInstance> filterList = osclient.autoScaling().groupInstances().list(groupId,
				options);
		logger.info("{}", filterList);
		if (filterList != null && !filterList.isEmpty()) {
			for (ScalingGroupInstance instance : filterList) {
				assertTrue(LifeCycleState.INSERVICE.equals(instance.getLifeCycleState()));
				assertTrue(HealthStatus.NORMAL.equals(instance.getHealthStatus()));
			}
		}
	}

	@Test(priority = 1)
	public void testDeleteAutoScalingGroupInstance() {
		String instanceId = "7f8e7f05-0323-4d5f-9e48-445da24e1cee";
		ActionResponse resp = osclient.autoScaling().groupInstances().delete(instanceId, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test(priority = 0)
	public void testBatchOperateAutoScalingGroupInstance() throws InterruptedException {
		String groupId = "588b4592-0998-4722-b51d-e6dbc574ec32";
		List<String> instanceIds = Lists.newArrayList("7f8e7f05-0323-4d5f-9e48-445da24e1cee");

		ActionResponse resp = osclient.autoScaling().groupInstances().batchAdd(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
		//wait server to initial new group instance life cycle status, 
		//otherwise the delete test case will fail for group instance not found
		TimeUnit.MINUTES.sleep(1);
		
		resp = osclient.autoScaling().groupInstances().batchRemove(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}
	
}
