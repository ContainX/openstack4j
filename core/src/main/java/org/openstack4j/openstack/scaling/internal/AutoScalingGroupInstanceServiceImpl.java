package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Strings;
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
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list")).execute().getList();
	}

	@Override
	public List<? extends ScalingGroupInstance> list(String groupId, ScalingGroupInstanceListOptions options) {
		checkNotNull(groupId, "groupId");
		return get(ASAutoScalingGroupInstances.class, uri("/scaling_group_instance/%s/list"))
				.params(options.getOptions()).execute().getList();
	}

	@Override
	public ActionResponse delete(String instanceId, String delete) {
		checkNotNull(instanceId, "instanceId");
		delete = Strings.isNullOrEmpty(delete) ? "no" : delete;
		return deleteWithResponse(uri("/scaling_group_instance/%s?instance_delete=%s", instanceId, delete)).execute();
	}

	@Override
	public ActionResponse batchOperate(String groupId, List<String> instanceIds, String delete, String action) {
		checkNotNull(groupId, "groupId");
		delete = Strings.isNullOrEmpty(delete) ? "no" : delete;
		ASAutoScalingGroupInstanceBatch entity = ASAutoScalingGroupInstanceBatch.builder()
			.instanceIds(instanceIds)
			.delete(delete)
			.action(action)
			.build();
		return post(ActionResponse.class, uri("/scaling_group_instance/%s/action", groupId)).entity(entity).execute();
	}
}
