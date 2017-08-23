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

import com.huawei.openstack4j.api.gbp.PolicyRuleSetService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyRuleSet;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRuleSet;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyRuleSet.PolicyRuleSets;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Policy Rule set API Implementation
 * 
 * @author vinod borole
 */
public class PolicyRuleSetServiceImpl extends BaseNetworkingServices implements PolicyRuleSetService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends PolicyRuleSet> list() {
        return get(PolicyRuleSets.class, uri("/grouppolicy/policy_rule_sets")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public List<? extends PolicyRuleSet> list(Map<String, String> filteringParams) {
        Invocation<PolicyRuleSets> policyrulesetsInvocation = buildInvocation(filteringParams);
        return policyrulesetsInvocation.execute().getList();
    }
    
    private Invocation<PolicyRuleSets> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyRuleSets> policyrulesetsInvocation = get(PolicyRuleSets.class, "/grouppolicy/policy_rule_sets");
        if (filteringParams == null) { 
            return policyrulesetsInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policyrulesetsInvocation = policyrulesetsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policyrulesetsInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyRuleSet get(String id) {
        checkNotNull(id);
        return get(GbpPolicyRuleSet.class, uri("/grouppolicy/policy_rule_sets/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_rule_sets/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public PolicyRuleSet create(PolicyRuleSet policyRuleSet) {
        return post(GbpPolicyRuleSet.class, uri("/grouppolicy/policy_rule_sets")).entity(policyRuleSet).execute();
    }

   /**
    * {@inheritDoc}
    */
   @Override
    public PolicyRuleSet update(String policyRuleSetId, PolicyRuleSet policyRuleSet) {
        checkNotNull(policyRuleSetId);
        checkNotNull(policyRuleSet);
        return put(GbpPolicyRuleSet.class, uri("/grouppolicy/policy_rule_sets/%s", policyRuleSetId)).entity(policyRuleSet).execute();

    }

}
