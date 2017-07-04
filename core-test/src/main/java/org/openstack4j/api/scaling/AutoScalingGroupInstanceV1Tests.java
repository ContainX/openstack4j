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
package org.openstack4j.api.scaling;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.model.scaling.ScalingGroupInstance.HealthStatus;
import org.openstack4j.model.scaling.ScalingGroupInstance.LifeCycleState;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

@Test(suiteName = "AutoScaling/AutoScalingGroupInstanceV1", enabled = true)
public class AutoScalingGroupInstanceV1Tests extends AbstractTest {

	private static final String JSON_SCALING_GROUP_INSTANCE_LIST = "/scaling/as_scaling_group_instance_list.json";
	private static final String JSON_SCALING_GROUP_INSTANCE_LIST2 = "/scaling/as_scaling_group_instance_list2.json";

	public void testListAutoScalingGroupInstance() throws IOException {
		respondWith(JSON_SCALING_GROUP_INSTANCE_LIST);

		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<? extends ScalingGroupInstance> all = osv3().autoScaling().groupInstances().list(groupId);
		assertTrue(all != null && all.size() == 2);

		respondWith(JSON_SCALING_GROUP_INSTANCE_LIST2);
		ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create().heathStatus(HealthStatus.NORMAL)
				.lifeCycleState(LifeCycleState.INSERVICE);
		List<? extends ScalingGroupInstance> list = osv3().autoScaling().groupInstances().list(groupId, options);
		assertTrue(list != null && list.size() == 2);
		for (ScalingGroupInstance instance : list) {
			assertTrue(LifeCycleState.INSERVICE.equals(instance.getLifeCycleState()));
			assertTrue(HealthStatus.NORMAL.equals(instance.getHealthStatus()));
		}
	}

	public void testDeleteAutoScalingGroupInstance() {
		respondWith(204);
		String instanceId = "475db405-11b4-47f6-bb9d-f3bcbc7ac27f";
		ActionResponse resp = osv3().autoScaling().groupInstances().delete(instanceId, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testBatchOperateAutoScalingGroupInstance() {
		respondWith(204);
		respondWith(204);
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<String> instanceIds = Lists.newArrayList("475db405-11b4-47f6-bb9d-f3bcbc7ac27f");
		ActionResponse resp = osv3().autoScaling().groupInstances().batchAdd(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
		
		resp = osv3().autoScaling().groupInstances().batchRemove(groupId, instanceIds, false);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
