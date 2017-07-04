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
import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate.ScalingPolicyType;
import org.openstack4j.model.scaling.ScheduledPolicy;
import org.openstack4j.model.scaling.ScheduledPolicy.RecurrenceType;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;
import org.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

@Test(suiteName = "AutoScaling/AutoScalingPolicyV1", enabled = true)
public class AutoScalingPolicyV1Tests2 extends AbstractTest {

	private static final String JSON_SCALING_POLICY_CREATE = "/scaling/as_scaling_group_policy_create.json";
	private static final String JSON_SCALING_POLICY_LIST = "/scaling/as_scaling_group_policy_list.json";
	private static final String JSON_SCALING_POLICY_LIST2 = "/scaling/as_scaling_group_policy_list2.json";
	private static final String JSON_SCALING_POLICY_GET = "/scaling/as_scaling_group_policy.json";

	public void testCreateAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_CREATE);
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder().launchTime("01:00")
				.recurrenceType(RecurrenceType.DAILY).recurrenceValue(null).build();
		ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName("policyTestName")
				.groupId(groupId).policyType(ScalingPolicyType.RECURRENCE).scheduledPolicy(scheduledPolicy).build();
		ScalingPolicyCreateUpdate create = osv3().autoScaling().policies().create(policy);
		assertTrue(create != null && !Strings.isNullOrEmpty(create.getPolicyId()));
	}

	public void testUpdateAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_GET);
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osv3().autoScaling().policies().get(policyId);

		respondWith(JSON_SCALING_POLICY_CREATE);
		String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
		ScalingPolicyCreateUpdate update = osv3().autoScaling().policies().update(
				ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder().policyName(after).build());
		assertTrue(policyId.equals(update.getPolicyId()));
	}

	public void testListAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_LIST);
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<? extends ScalingPolicy> all = osv3().autoScaling().policies().list(groupId);
		assertTrue(all != null && all.size() == 2);

		
		respondWith(JSON_SCALING_POLICY_LIST2);
		String policyName = "policyName";
		ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName(policyName);
		List<? extends ScalingPolicy> list = osv3().autoScaling().policies().list(groupId, options);
		assertTrue(list != null && list.size() == 1);
		if (list != null && !list.isEmpty()) {
			for (ScalingPolicy policy : list) {
				assertTrue(policyName.equals(policy.getPolicyName()));
			}
		}
	}

	public void testGetAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_GET);
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ScalingPolicy policy = osv3().autoScaling().policies().get(policyId);
		assertTrue(policyId.equals(policy.getPolicyId()));
	}

	public void testOperateAutoScalingPolicy() {
		respondWith(204);
		respondWith(204);
		respondWith(204);
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ActionResponse resp = osv3().autoScaling().policies().resume(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
		
		resp = osv3().autoScaling().policies().execute(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		resp = osv3().autoScaling().policies().pause(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testDeleteAutoScalingPolicy() {
		respondWith(204);
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ActionResponse resp = osv3().autoScaling().policies().delete(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
