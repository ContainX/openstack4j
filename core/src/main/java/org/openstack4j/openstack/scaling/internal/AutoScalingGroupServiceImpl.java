package org.openstack4j.openstack.scaling.internal;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingGroupService;
import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroup.ASAutoScalingGroups;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-14 09:02:52
 */
public class AutoScalingGroupServiceImpl  extends BaseAutoScalingServices implements AutoScalingGroupService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends ScalingGroup> list() {
		return get(ASAutoScalingGroups.class, uri("/scaling_group")).execute().getList();
	}
	
}
