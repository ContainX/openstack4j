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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import org.openstack4j.model.scaling.ScheduledPolicy;
import org.openstack4j.model.scaling.ScheduledPolicy.RecurrenceType;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate.PolicyType;
import org.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

public class ASPolicySample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASPolicySample.class);

	@Test
	public void testCreateAutoScalingPolicy() {
		String groupId = "8a2462e3-6ae8-4d86-bd89-4497836fe022";
		ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder().launchTime("2017-07-24T01:21Z")
				.recurrenceType(RecurrenceType.Daily.name()).endTime(getEndTime()).recurrenceValue(null).build();
		ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName("SDK-policyName")
				.groupId(groupId).policyType(PolicyType.SCHEDULED.name()).scheduledPolicy(scheduledPolicy).build();
		ScalingPolicyCreateUpdate create = osclient.autoScaling().policies().create(policy);
		assertTrue(create != null && !Strings.isNullOrEmpty(create.getPolicyId()));
	}

	@Test
	public void testUpdateAutoScalingPolicy() {
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
		String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
		ScheduledPolicy scheduledPolicy = policy.getScheduledPolicy().toBuilder().endTime(getEndTime()).build();
		osclient.autoScaling().policies().update(ASAutoScalingPolicyCreateUpdate.fromScalingPolicy(policy).toBuilder()
				.scheduledPolicy(scheduledPolicy).policyName(after).build());

		policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
		assertTrue(after.equals(policy.getPolicyName()));
	}

	@Test
	public void testListAutoScalingPolicy() {
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<? extends ScalingPolicy> all = osclient.autoScaling().policies().list(groupId);
		logger.info("{}", all);

		String policyName = "SDK-policyName";
		ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName(policyName);
		List<? extends ScalingPolicy> list = osclient.autoScaling().policies().list(groupId, options);
		logger.info("{}", list);
		if (list != null && !list.isEmpty()) {
			for (ScalingPolicy policy : list) {
				assertTrue(policyName.equals(policy.getPolicyName()));
			}
		}
	}

	@Test
	public void testGetAutoScalingPolicy() {
		String policyId = "50bbaf82-f4c1-4870-a55c-61a52cdcfa27";
		ScalingPolicy policy = osclient.autoScaling().policies().get(policyId);
		assertTrue(policyId.equals(policy.getPolicyId()));
	}

	@Test
	public void testOperateAutoScalingPolicy() {
		String policyId = "73a0d241-ba0a-4273-a471-d80ed55db184";
		ActionResponse resp = osclient.autoScaling().policies().resume(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		ScalingPolicy policy = osclient.autoScaling().policies().get(policyId);
		assertTrue("INSERVICE".equals(policy.getPolicyStatus()));
		
		resp = osclient.autoScaling().policies().execute(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		policy = osclient.autoScaling().policies().get(policyId);
		assertTrue("INSERVICE".equals(policy.getPolicyStatus()));
		
		resp = osclient.autoScaling().policies().pause(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());

		policy = osclient.autoScaling().policies().get(policyId);
		assertTrue("PAUSED".equals(policy.getPolicyStatus()));
	}

	@Test
	public void testDeleteAutoScalingPolicy() {
		String policyId = "73a0d241-ba0a-4273-a471-d80ed55db184";
		ActionResponse resp = osclient.autoScaling().policies().delete(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	private Date getEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 12);
		return cal.getTime();
	}

}
