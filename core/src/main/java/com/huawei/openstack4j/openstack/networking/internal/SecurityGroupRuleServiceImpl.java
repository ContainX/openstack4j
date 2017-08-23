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
package com.huawei.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.SecurityGroupRuleService;
import com.huawei.openstack4j.model.network.SecurityGroupRule;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroupRule.SecurityGroupRules;

/**
 * FloatingIPService implementation that provides Neutron Floating-IP based Service Operations.
 *
 * @author Nathan Anderson
 */
public class SecurityGroupRuleServiceImpl extends BaseNetworkingServices implements SecurityGroupRuleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupRule get(String id) {
        checkNotNull(id);
        return get(NeutronSecurityGroupRule.class, uri("/security-group-rules/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String ruleId) {
        checkNotNull(ruleId);
        delete(Void.class, uri("/security-group-rules/%s", ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupRule create(SecurityGroupRule rule) {
        checkNotNull(rule);
        return post(NeutronSecurityGroupRule.class, uri("/security-group-rules")).entity(rule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityGroupRule> list(Map<String, String> filteringParams) {
        Invocation<SecurityGroupRules> securityGroupRulesInvocation = get(SecurityGroupRules.class, "/security-group-rules");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                securityGroupRulesInvocation = securityGroupRulesInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return securityGroupRulesInvocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityGroupRule> list() {
        return get(SecurityGroupRules.class, uri("/security-group-rules")).execute().getList();
    }
}
