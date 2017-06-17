package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingActivityLogService;
import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingActivityLog.ASAutoScalingActivityLogs;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;

import com.google.common.base.Strings;

public class AutoScalingActivityLogServiceImpl extends BaseAutoScalingServices
		implements AutoScalingActivityLogService {

	@Override
	public List<? extends ScalingActivityLog> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		return get(ASAutoScalingActivityLogs.class, uri("/scaling_activity_log/%s", groupId)).execute().getList();
	}

	@Override
	public List<? extends ScalingActivityLog> list(String groupId, ScalingActivityLogListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		checkArgument(options != null, "options required");
		return get(ASAutoScalingActivityLogs.class, uri("/scaling_activity_log/%s", groupId))
				.params(options.getOptions()).execute().getList();
	}

}
