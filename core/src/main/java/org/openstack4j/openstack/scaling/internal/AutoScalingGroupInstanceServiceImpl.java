package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingGroupInstanceService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstance.ASAutoScalingGroupInstances;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingGroupInstanceBatch;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;

public class AutoScalingGroupInstanceServiceImpl extends BaseAutoScalingServices
		implements AutoScalingGroupInstanceService {

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId) {
		checkNotNull(groupId, "groupId");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId)).execute()
				.getList();
	}

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId, ScalingGroupInstanceListOptions options) {
		checkNotNull(groupId, "groupId");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list", groupId))
				.params(options.getOptions()).execute().getList();
	}

	@Override
	public ActionResponse delete(String instanceId, boolean deleteInstance) {
		checkNotNull(instanceId, "instanceId");
		String yesOrNo = deleteInstance ? "yes" : "no";
		return deleteWithResponse(uri("/scaling_group_instance/%s?instance_delete=%s", instanceId, yesOrNo)).execute();
	}

	@Override
	public ActionResponse batchOperate(String groupId, List<String> instanceIds, boolean deleteInstance,
			String action) {
		checkNotNull(groupId, "groupId");
		String yesOrNo = deleteInstance ? "yes" : "no";
		ASAutoScalingGroupInstanceBatch entity = ASAutoScalingGroupInstanceBatch.builder().instanceIds(instanceIds)
				.delete(yesOrNo).action(action).build();
		return post(ActionResponse.class, uri("/scaling_group_instance/%s/action", groupId)).entity(entity).execute();
	}
}
