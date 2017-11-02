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
package com.huawei.openstack4j.functional.scaling;

import static org.testng.Assert.*;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingPolicy;
import com.huawei.openstack4j.model.scaling.ScalingPolicy.PolicyStatus;
import com.huawei.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import com.huawei.openstack4j.model.scaling.ScalingPolicyCreateUpdate.ScalingPolicyType;
import com.huawei.openstack4j.model.scaling.ScheduledPolicy;
import com.huawei.openstack4j.model.scaling.ScheduledPolicy.RecurrenceType;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;

public class ASPolicyTest extends BaseAutoScalingTest {

	String name = randomName();
	String asConfigId;
	String asGroupId;
	String asPolicyId;

	Date now;
	Date oneDaysLater;

	private ScalingPolicy policy;

	@BeforeClass
	public void testCreatePolicy() {
		// prepare
		asConfigId = createConfig(name);
		asGroupId = createGroup(name, asConfigId);
		osclient.autoScaling().groups().resume(asGroupId);

		now = new Date();
		now = new Date(now.getTime() / 60000 * 60000);
		oneDaysLater = new Date(now.getTime() + 1000L * 60 * 60 * 24);

		// create policy
		ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder().launchTime("16:00").startTime(now)
				.endTime(oneDaysLater).recurrenceType(RecurrenceType.DAILY).recurrenceValue(null).build();

		ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName(name).groupId(asGroupId)
				.policyType(ScalingPolicyType.RECURRENCE).scheduledPolicy(scheduledPolicy).build();

		asPolicyId = osclient.autoScaling().policies().create(policy);
	}

	@AfterClass
	public void testDeletePolicy() {
		ActionResponse resp = osclient.autoScaling().policies().delete(asPolicyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		osclient.autoScaling().groups().delete(asGroupId);
		osclient.autoScaling().configs().delete(asConfigId);
	}

	@Test
	public void testGetAutoScalingPolicy() {
		ScalingPolicy policy = osclient.autoScaling().policies().get(asPolicyId);
		validatePolicy(policy);
		this.policy = policy;
	}

	/**
	 * @param policy
	 */
	public void validatePolicy(ScalingPolicy policy) {
		Assert.assertEquals(policy.getPolicyId(), asPolicyId);
		Assert.assertEquals(policy.getPolicyName(), name);
		Assert.assertEquals(policy.getGroupId(), asGroupId);
		Assert.assertEquals(policy.getPolicyType(), ScalingPolicyType.RECURRENCE);
		ScheduledPolicy scheduled = policy.getScheduledPolicy();
		Assert.assertEquals(scheduled.getLaunchTime(), "16:00");
		Assert.assertEquals(scheduled.getStartTime(), now);
		Assert.assertEquals(scheduled.getEndTime(), oneDaysLater);
		Assert.assertEquals(scheduled.getRecurrenceType(), RecurrenceType.DAILY);
	}

	@Test
	public void testListAutoScalingPolicy() {
		List<? extends ScalingPolicy> all = osclient.autoScaling().policies().list(asGroupId);
		boolean found = false;
		for (ScalingPolicy scalingPolicy : all) {
			if (scalingPolicy.getPolicyId().equals(asPolicyId)) {
				found = true;
				validatePolicy(scalingPolicy);
				break;
			}
		}
		Assert.assertTrue(found);

		ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName(name);
		List<? extends ScalingPolicy> list = osclient.autoScaling().policies().list(asGroupId, options);
		Assert.assertEquals(list.size(), 1);
		ScalingPolicy scalingPolicy = list.get(0);
		validatePolicy(scalingPolicy);
	}

	@Test(dependsOnMethods = { "testListAutoScalingPolicy", "testGetAutoScalingPolicy" })
	public void testUpdateAutoScalingPolicy() {
		osclient.autoScaling().policies().update(ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder()
				.policyName(name + "-updated").coolDownTime(200).build());

		ScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(asPolicyId);
		Assert.assertEquals(policy.getPolicyName(), name + "-updated");
		Assert.assertEquals(policy.getCoolDownTime().intValue(), 200);
	}

	@Test
	public void testOperateAutoScalingPolicy() {
		
		// resume
		ActionResponse resp = osclient.autoScaling().policies().resume(asPolicyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		ScalingPolicy policy = osclient.autoScaling().policies().get(asPolicyId);
		assertTrue(PolicyStatus.INSERVICE.equals(policy.getPolicyStatus()));

		// pause
		resp = osclient.autoScaling().policies().pause(asPolicyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		policy = osclient.autoScaling().policies().get(asPolicyId);
		assertTrue(PolicyStatus.PAUSED.equals(policy.getPolicyStatus()));
		
		// resume again
		resp = osclient.autoScaling().policies().resume(asPolicyId);
		
		// execute
		resp = osclient.autoScaling().policies().execute(asPolicyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		policy = osclient.autoScaling().policies().get(asPolicyId);
		assertTrue(PolicyStatus.INSERVICE.equals(policy.getPolicyStatus()));
	}

}
