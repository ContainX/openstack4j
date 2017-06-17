package org.openstack4j.api.scaling;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import org.openstack4j.openstack.scaling.domain.action.ScalingPolicyAction;
import org.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;

public interface AutoScalingPolicyService extends RestService {
	
	public ScalingPolicyCreateUpdate create(ScalingPolicyCreateUpdate policy);
	
	public ScalingPolicyCreateUpdate update(ScalingPolicyCreateUpdate policy);

	public List<? extends ScalingPolicy> list(String groupId);
	
	public List<? extends ScalingPolicy> list(String groupId, ScalingPolicyListOptions options);
	
	public ScalingPolicy get(String policyId);
	
	public ActionResponse operate(String policyId, ScalingPolicyAction action);
	
	public ActionResponse delete(String policyId);
}
