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
package org.openstack4j.sample;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch.Action;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

//TODO need test
public class ASGroupInstanceSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASGroupInstanceSample.class);

	@Test
	public void testListAutoScalingGroupInstance() {
		String groupId = "";
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

	@Test
	public void testDeleteAutoScalingGroupInstance() {
		String instanceId = "";
		ActionResponse resp = osclient.autoScaling().groupInstances().delete(instanceId, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Test
	public void testBatchOperateAutoScalingGroupInstance() {
		String groupId = "";
		List<String> instanceIds =  Lists.newArrayList();
		ActionResponse resp = osclient.autoScaling().groupInstances().batchOperate(groupId, instanceIds , false, Action.ADD);
		assertTrue(resp.isSuccess(), resp.getFault());
		
		resp = osclient.autoScaling().groupInstances().batchOperate(groupId, instanceIds, false, Action.REMOVE);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

}
