package org.openstack4j.sample;

import static org.testng.Assert.assertTrue;

import java.util.List;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

//TODO need test
public class ASPolicySample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASPolicySample.class);

	@Test
	public void testCreateAutoScalingPolicy() {
		String groupId = "";
		ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder().launchTime("01:00")
				.recurrenceType(RecurrenceType.Daily.name()).recurrenceValue(null).build();
		ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder().policyName("policyName")
				.groupId(groupId).policyType(PolicyType.SCHEDULED.name()).scheduledPolicy(scheduledPolicy).build();
		ScalingPolicyCreateUpdate create = osclient.autoScaling().policies().create(policy);
		assertTrue(create != null && !Strings.isNullOrEmpty(create.getPolicyId()));
	}

	@Test
	public void testUpdateAutoScalingPolicy() {
		String policyId = "";
		ASAutoScalingPolicy policy = (ASAutoScalingPolicy) osclient.autoScaling().policies().get(policyId);
		String after = new StringBuilder(policy.getPolicyName()).reverse().toString();
		ScalingPolicyCreateUpdate update = osclient.autoScaling().policies()
				.update(policy.toBuilder().policyName(after).build());
		assertTrue(after.equals(update.getPolicyName()));
	}

	@Test
	public void testListAutoScalingPolicy() {
		String groupId = "";
		List<? extends ScalingPolicy> all = osclient.autoScaling().policies().list(groupId);
		logger.info("{}", all);
		
		String policyName = "";
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
		String policyId = "";
		ScalingPolicy policy = osclient.autoScaling().policies().get(policyId);
		assertTrue(policyId.equals(policy.getPolicyId()));
	}
	
	@Test
	public void testOperateAutoScalingPolicy() {
		String policyId = "";
		ActionResponse resp = osclient.autoScaling().policies().operate(policyId, new Resume());
		assertTrue(resp.isSuccess(), resp.getFault());
		
		resp = osclient.autoScaling().policies().operate(policyId, new Pause());
		assertTrue(resp.isSuccess(), resp.getFault());
	}
	
	@Test
	public void testDeleteAutoScalingPolicy() {
		String policyId = "";
		ActionResponse resp = osclient.autoScaling().policies().delete(policyId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

}
