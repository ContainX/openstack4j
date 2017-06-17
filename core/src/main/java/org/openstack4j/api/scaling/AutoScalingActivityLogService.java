package org.openstack4j.api.scaling;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;

public interface AutoScalingActivityLogService extends RestService {
	public List<? extends ScalingActivityLog> list(String groupId);
	
	public List<? extends ScalingActivityLog> list(String groupId, ScalingActivityLogListOptions options);
}
