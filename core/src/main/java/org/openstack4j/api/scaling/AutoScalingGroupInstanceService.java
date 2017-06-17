package org.openstack4j.api.scaling;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.scaling.options.ScalingGroupInstanceListOptions;

public interface AutoScalingGroupInstanceService extends RestService {
	
	public List<? extends ScalingGroupInstance> list(String groupId);
	
	public List<? extends ScalingGroupInstance> list(String groupId, ScalingGroupInstanceListOptions options);
	
	public ActionResponse delete(String instanceId, String delete);
	
	public ActionResponse batchOperate(String groupId, List<String> instanceIds, String delete, String action);
}
