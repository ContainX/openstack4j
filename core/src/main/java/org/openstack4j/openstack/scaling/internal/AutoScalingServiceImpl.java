package org.openstack4j.openstack.scaling.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.scaling.AutoScalingGroupService;
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

}
