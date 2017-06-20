/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingPolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import org.openstack4j.model.scaling.ScheduledPolicy;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicy.ASAutoScalingPolicys;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingPolicyCreateUpdate;
import org.openstack4j.openstack.scaling.domain.action.ScalingPolicyAction;
import org.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;

import com.google.common.base.Strings;

public class AutoScalingPolicyServiceImpl extends BaseAutoScalingServices implements AutoScalingPolicyService {

	@Override
	public ScalingPolicyCreateUpdate create(ScalingPolicyCreateUpdate policy) {
		checkArgument(policy != null, "policy required");
		checkArgument(!Strings.isNullOrEmpty(policy.getPolicyName()), "policyName required");
		checkArgument(!Strings.isNullOrEmpty(policy.getGroupId()), "groupId required");
		checkArgument(!Strings.isNullOrEmpty(policy.getPolicyType()), "policyType required");

		checkScheduledPolicyWhenPresent(policy.getScheduledPolicy());
		return post(ASAutoScalingPolicyCreateUpdate.class, uri("/scaling_policy")).entity(policy).execute();
	}

	@Override
	public ScalingPolicyCreateUpdate update(ScalingPolicyCreateUpdate policy) {
		checkArgument(policy != null, "policy required");
		checkArgument(!Strings.isNullOrEmpty(policy.getPolicyId()), "policyId required");

		checkScheduledPolicyWhenPresent(policy.getScheduledPolicy());
		return put(ASAutoScalingPolicyCreateUpdate.class, uri("/scaling_policy/%s", policy.getPolicyId())).execute();
	}

	@Override
	public List<? extends ScalingPolicy> list(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		return get(ASAutoScalingPolicys.class, uri("/scaling_policy/%s/list", groupId)).execute().getList();
	}

	@Override
	public List<? extends ScalingPolicy> list(String groupId, ScalingPolicyListOptions options) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "groupId required");
		return get(ASAutoScalingPolicys.class, uri("/scaling_policy/%s/list", groupId)).params(options.getOptions())
				.execute().getList();
	}

	@Override
	public ScalingPolicy get(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId), "policyId required");
		return get(ASAutoScalingPolicy.class, uri("/scaling_policy/%s", policyId)).execute();
	}

	@Override
	public ActionResponse operate(String policyId, ScalingPolicyAction action) {
		checkArgument(!Strings.isNullOrEmpty(policyId), "policyId required");
		checkArgument(action != null, "action required");
		return post(ActionResponse.class, uri("/scaling_policy/%s/action", policyId)).entity(action).execute();
	}

	@Override
	public ActionResponse delete(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId), "policyId required");
		return deleteWithResponse(uri("/scaling_policy/%s", policyId)).execute();
	}

	private void checkScheduledPolicyWhenPresent(ScheduledPolicy scheduledPolicy) {
		if (scheduledPolicy != null)
			checkArgument(!Strings.isNullOrEmpty(scheduledPolicy.getLaunchTime()), "launchTime required");
	}

}
