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
package com.huawei.openstack4j.openstack.gbp.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.gbp.PolicyRuleService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyRule;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRule;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRule.PolicyRules;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Policy Rule API Implementation
 * 
 * @author vinod borole
 */
public class PolicyRuleServiceImpl extends BaseNetworkingServices implements PolicyRuleService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends PolicyRule> list() {
        return get(PolicyRules.class, uri("/grouppolicy/policy_rules")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PolicyRule> list(Map<String, String> filteringParams) {
        Invocation<PolicyRules> policyruleInvocation = buildInvocation(filteringParams);
        return policyruleInvocation.execute().getList();
    }
    
    private Invocation<PolicyRules> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyRules> policyruleInvocation = get(PolicyRules.class, "/grouppolicy/policy_rules");
        if (filteringParams == null) { 
            return policyruleInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policyruleInvocation = policyruleInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policyruleInvocation;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyRule get(String id) {
        checkNotNull(id);
        return get(GbpPolicyRule.class, uri("/grouppolicy/policy_rules/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_rules/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyRule create(PolicyRule policyRule) {
        return post(GbpPolicyRule.class, uri("/grouppolicy/policy_rules")).entity(policyRule).execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyRule update(String policyRuleId, PolicyRule policyRule) {
        checkNotNull(policyRuleId);
        checkNotNull(policyRule);
        return put(GbpPolicyRule.class, uri("/grouppolicy/policy_rules/%s", policyRuleId)).entity(policyRule).execute();
    }

}
