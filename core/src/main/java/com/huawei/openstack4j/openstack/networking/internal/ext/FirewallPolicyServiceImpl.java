/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.FirewallPolicyService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.FirewallPolicy;
import com.huawei.openstack4j.model.network.ext.FirewallPolicyUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.FirewallRuleStrategy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicyRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.AbstractNeutronFirewallPolicy.FirewallPolicies;
import com.huawei.openstack4j.openstack.networking.domain.ext.FirewallRuleStrategy.RuleInsertStrategyType;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) FwaaS FirewallPolicy Extension API
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallPolicyServiceImpl extends BaseNetworkingServices implements FirewallPolicyService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallPolicy> list() {
		return get(FirewallPolicies.class, uri("/fw/firewall_policies")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallPolicy> list(Map<String, String> filteringParams) {
		Invocation<FirewallPolicies> req = get(FirewallPolicies.class, uri("/fw/firewall_policies"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy get(String firewallPolicyId) {
		checkNotNull(firewallPolicyId);
		return get(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).execute();	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String firewallPolicyId) {
		checkNotNull(firewallPolicyId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri("/fw/firewall_policies/%s", firewallPolicyId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy create(FirewallPolicy firewallPolicy) {
		return post(NeutronFirewallPolicy.class, uri("/fw/firewall_policies")).entity(firewallPolicy)
				.execute(ExecutionOptions.<NeutronFirewallPolicy>create(PropagateOnStatus.on(404)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy update(String firewallPolicyId, FirewallPolicyUpdate firewallPolicyUpdate) {
		checkNotNull(firewallPolicyId);
        checkNotNull(firewallPolicyUpdate);
        return put(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).entity(firewallPolicyUpdate).execute();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy insertFirewallRuleInPolicy(
			String firewallPolicyId, String firewallRuleId, RuleInsertStrategyType type, String insertAfterOrBeforeRuleId) {
		checkNotNull(firewallPolicyId);
		checkNotNull(firewallRuleId);
		return put(NeutronFirewallPolicyRule.class, uri("/fw/firewall_policies/%s/insert_rule", firewallPolicyId))
		          .entity(FirewallRuleStrategy.create(firewallRuleId, type, insertAfterOrBeforeRuleId))
				  .execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy removeFirewallRuleFromPolicy(String firewallPolicyId, String firewallRuleId) {
		checkNotNull(firewallPolicyId);
		checkNotNull(firewallRuleId);
		checkState(!(firewallPolicyId == null && firewallRuleId == null), 
				"Either a Firewall Policy or Firewall Rule identifier must be set");
		return put(NeutronFirewallPolicyRule.class, uri("/fw/firewall_policies/%s/remove_rule", firewallPolicyId))
		           .entity(FirewallRuleStrategy.remove(firewallRuleId))
		           .execute(ExecutionOptions.<NeutronFirewallPolicyRule>create(PropagateOnStatus.on(404)));
	}

}
