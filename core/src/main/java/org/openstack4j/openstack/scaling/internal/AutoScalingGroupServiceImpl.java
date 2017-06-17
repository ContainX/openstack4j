package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroup;
import org.openstack4j.model.scaling.ScalingGroupCreate;
import org.openstack4j.model.scaling.ScalingGroupUpdate;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroup;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroup.ASAutoScalingGroups;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import org.openstack4j.openstack.scaling.domain.action.ScalingGroupAction;
import org.openstack4j.openstack.scaling.options.ScalingGroupListOptions;

/**
 *
 * @author QianBiao.NG
 * @date 2017-06-14 09:02:52
 */
public class AutoScalingGroupServiceImpl extends BaseAutoScalingServices implements AutoScalingGroupService {

	@Override
	public ScalingGroupCreate create(ScalingGroupCreate group) {
		checkNotNull(group, "scaling group");
		checkNotNull(group.getNetworks(), "networks");
		checkNotNull(group.getSecurityGroups(), "securityGroups");
		checkNotNull(group.getVpcId(), "vpcId");
		for (IdResourceEntity network : group.getNetworks()) {
			checkNotNull(network.getId(), "network id");
		}
		for (IdResourceEntity securityGroup : group.getSecurityGroups()) {
			checkNotNull(securityGroup.getId(), "security group id");
		}
		return post(ASAutoScalingGroupCreate.class, uri("/scaling_group")).entity(group).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends ScalingGroup> list(ScalingGroupListOptions options) {
		return get(ASAutoScalingGroups.class, uri("/scaling_group")).params(options.getOptions()).execute().getList();
	}

	@Override
	public List<? extends ScalingGroup> list() {
		return get(ASAutoScalingGroups.class, uri("/scaling_group")).execute().getList();
	}

	@Override
	public ScalingGroup get(String groupId) {
		checkNotNull(groupId, "scaling group id");
		return get(ASAutoScalingGroup.class, uri("/scaling_group/%s", groupId)).execute();
	}

	@Override
	public ScalingGroupUpdate update(String groupId, ScalingGroupUpdate group) {
		checkNotNull(group, "scaling group");
		checkNotNull(groupId, "groupId");
		if (group.getNetworks() != null) {
			for (IdResourceEntity network : group.getNetworks()) {
				checkNotNull(network.getId(), "network id");
			}
		}
		if (group.getSecurityGroups() != null) {
			for (IdResourceEntity securityGroup : group.getSecurityGroups()) {
				checkNotNull(securityGroup.getId(), "security group id");
			}
		}
		return put(ASAutoScalingGroupUpdate.class, uri("/scaling_group/%s", groupId)).entity(group).execute();
	}

	@Override
	public ActionResponse delete(String groupId) {
		checkNotNull(groupId, "groupId");
		return deleteWithResponse(uri("/scaling_group/%s", groupId)).execute();
	}

	@Override
	public ActionResponse operate(String groupId, ScalingGroupAction action) {
		checkNotNull(groupId, "groupId");
		checkNotNull(action, "action");
		return postWithResponse(uri("/scaling_group/%s/action", groupId)).entity(action).execute();
	}

}
