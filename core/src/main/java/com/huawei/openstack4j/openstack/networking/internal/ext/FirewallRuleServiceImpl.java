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

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.FirewallRuleService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.FirewallRule;
import com.huawei.openstack4j.model.network.ext.FirewallRuleUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule.FirewallRules;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) FwaaS FirewallRule Rule Extension API
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallRuleServiceImpl extends BaseNetworkingServices implements FirewallRuleService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallRule> list() {
		return get(FirewallRules.class, uri("/fw/firewall_rules")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallRule> list(Map<String, String> filteringParams) {
		Invocation<FirewallRules> req = get(FirewallRules.class, uri("/fw/firewall_rules"));
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
	public FirewallRule get(String firewallRuleId) {
		checkNotNull(firewallRuleId);
		return get(NeutronFirewallRule.class, uri("/fw/firewall_rules/%s", firewallRuleId)).execute();	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String firewallRuleId) {
		checkNotNull(firewallRuleId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri("/fw/firewall_rules/%s", firewallRuleId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallRule create(FirewallRule firewall) {
		return post(NeutronFirewallRule.class, uri("/fw/firewall_rules")).entity(firewall)
				.execute(ExecutionOptions.<NeutronFirewallRule>create(PropagateOnStatus.on(404)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallRule update(String firewallRuleId, FirewallRuleUpdate firewallRuleUpdate) {
		checkNotNull(firewallRuleId);
        checkNotNull(firewallRuleUpdate);
        return put(NeutronFirewallRule.class, uri("/fw/firewall_rules/%s", firewallRuleId)).entity(firewallRuleUpdate).execute();
	}

}
