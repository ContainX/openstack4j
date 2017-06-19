package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingGroupInstanceService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstance.ASAutoScalingGroupInstances;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch.Action;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;

import com.google.common.base.Strings;

public class AutoScalingGroupInstanceServiceImpl extends BaseAutoScalingServices
		implements AutoScalingGroupInstanceService {

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId)).execute()
				.getList();
	}

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId, ScalingGroupInstanceListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId))
				.params(options.getOptions()).execute().getList();
	}

	@Override
	public ActionResponse delete(String instanceId, boolean deleteInstance) {
		checkArgument(!Strings.isNullOrEmpty(instanceId), "instanceId is required");
		String yesOrNo = deleteInstance ? "yes" : "no";
		return deleteWithResponse(uri("/scaling_group_instance/%s?instance_delete=%s", instanceId, yesOrNo)).execute();
	}

	@Override
	public ActionResponse batchOperate(String groupId, List<String> instanceIds, boolean deleteInstance,
			Action action) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId is required");
		String yesOrNo = deleteInstance ? "yes" : "no";
		ASAutoScalingGroupInstanceBatch entity = ASAutoScalingGroupInstanceBatch.builder().instanceIds(instanceIds)
				.delete(yesOrNo).action(action.name()).build();
		return post(ActionResponse.class, uri("/scaling_group_instance/%s/action", groupId)).entity(entity).execute();
	}
}
