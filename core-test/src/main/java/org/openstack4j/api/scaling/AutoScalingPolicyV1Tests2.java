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
import org.openstack4j.model.scaling.ScheduledPolicy;
import org.openstack4j.model.scaling.ScheduledPolicy.RecurrenceType;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate.PolicyType;
import org.openstack4j.openstack.scaling.domain.action.ASScalingPolicyAction.Pause;
import org.openstack4j.openstack.scaling.domain.action.ASScalingPolicyAction.Resume;
import org.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

//TODO need test
@Test(suiteName = "AutoScaling/AutoScalingPolicyV1", enabled = true)
public class AutoScalingPolicyV1Tests2 extends AbstractTest {

	private static final String JSON_SCALING_POLICY_CREATE = "";
	private static final String JSON_SCALING_POLICY_UPDATE = "";
	private static final String JSON_SCALING_POLICY_LIST = "";
	private static final String JSON_SCALING_POLICY_LIST2 = "";
	private static final String JSON_SCALING_POLICY_GET = "";

	public void testCreateAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_CREATE);
		String groupId = "";
		ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder().launchTime("01:00")
				.recurrenceType(RecurrenceType.Daily.name()).recurrenceValue(null).build();
		ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName("policyName")
				.groupId(groupId).policyType(PolicyType.SCHEDULED.name()).scheduledPolicy(scheduledPolicy).build();
		ScalingPolicyCreateUpdate create = osv3().autoScaling().policies().create(policy);
		assertTrue(create != null && !Strings.isNullOrEmpty(create.getPolicyId()));
	}

	public void testUpdateAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_UPDATE);
		String policyId = "";
		ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osv3().autoScaling().policies().get(policyId);
		String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
		ScalingPolicyCreateUpdate update = osv3().autoScaling().policies()
				.update(policy.toBuilder().policyName(after).build());
		assertTrue(after.equals(update.getPolicyName()));
	}

	public void testListAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_LIST);
		String groupId = "";
		List<? extends ScalingPolicy> all = osv3().autoScaling().policies().list(groupId);
		assertTrue(all != null && all.size() == 5);

		
		respondWith(JSON_SCALING_POLICY_LIST2);
		String policyName = "";
		ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName(policyName);
		List<? extends ScalingPolicy> list = osv3().autoScaling().policies().list(groupId, options);
		assertTrue(list != null && list.size() == 2);
		if (list != null && !list.isEmpty()) {
			for (ScalingPolicy policy : list) {
				assertTrue(policyName.equals(policy.getPolicyName()));
			}
		}
	}

	public void testGetAutoScalingPolicy() throws IOException {
		respondWith(JSON_SCALING_POLICY_GET);
		String policyId = "";
		ScalingPolicy policy = osv3().autoScaling().policies().get(policyId);
		assertTrue(policyId.equals(policy.getPolicyId()));
	}

	public void testOperateAutoScalingPolicy() {
		respondWith(204);
		String policyId = "";
		ActionResponse resp = osv3().autoScaling().policies().operate(policyId, new Resume());
		assertTrue(resp.isSuccess(), resp.getFault());

		respondWith(204);
		resp = osv3().autoScaling().policies().operate(policyId, new Pause());
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void testDeleteAutoScalingPolicy() {
		respondWith(204);
		String policyId = "";
		ActionResponse resp = osv3().autoScaling().policies().delete(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
