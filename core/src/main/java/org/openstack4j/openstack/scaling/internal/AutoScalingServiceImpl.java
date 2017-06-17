package org.openstack4j.openstack.scaling.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.scaling.AutoScalingActivityLogService;
import org.openstack4j.api.scaling.AutoScalingConfigService;
import org.openstack4j.api.scaling.AutoScalingGroupInstanceService;
import org.openstack4j.api.scaling.AutoScalingGroupService;
import org.openstack4j.api.scaling.AutoScalingPolicyService;
import org.openstack4j.api.scaling.AutoScalingService;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-14 09:57:45
 */
public class AutoScalingServiceImpl extends BaseAutoScalingServices implements AutoScalingService {

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AutoScalingGroupService groups() {
		return Apis.get(AutoScalingGroupService.class);
	}

	@Override
	public AutoScalingConfigService configs() {
		return Apis.get(AutoScalingConfigService.class);
	}

	@Override
	public AutoScalingGroupInstanceService groupInstances() {
		return Apis.get(AutoScalingGroupInstanceService.class);
	}

	@Override
	public AutoScalingPolicyService policys() {
		return Apis.get(AutoScalingPolicyService.class);
	}

	@Override
	public AutoScalingActivityLogService activityLogs() {
		return Apis.get(AutoScalingActivityLogService.class);
	}
	
}
