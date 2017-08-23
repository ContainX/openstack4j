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

import com.huawei.openstack4j.api.gbp.PolicyTargetService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyTarget;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTarget;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTarget.PolicyTargets;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Policy target API Implementation
 * 
 * @author vinod borole
 */
public class PolicyTargetServiceImpl extends BaseNetworkingServices implements PolicyTargetService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends PolicyTarget> list() {
        return get(PolicyTargets.class, uri("/grouppolicy/policy_targets")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PolicyTarget> list(Map<String, String> filteringParams) {
        Invocation<PolicyTargets> policytargetsInvocation = buildInvocation(filteringParams);
        return policytargetsInvocation.execute().getList();
    }
    
    private Invocation<PolicyTargets> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyTargets> policytargetsInvocation = get(PolicyTargets.class, "/grouppolicy/policy_targets");
        if (filteringParams == null) { 
            return policytargetsInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policytargetsInvocation = policytargetsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policytargetsInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyTarget get(String id) {
        checkNotNull(id);
        return get(GbpPolicyTarget.class, uri("/grouppolicy/policy_targets/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_targets/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyTarget create(PolicyTarget policyTarget) {
        return post(GbpPolicyTarget.class, uri("/grouppolicy/policy_targets")).entity(policyTarget).execute();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public PolicyTarget update(String policyTargetId, PolicyTarget policyTarget) {
        checkNotNull(policyTargetId);
        checkNotNull(policyTarget);
        return put(GbpPolicyTarget.class, uri("/grouppolicy/policy_targets/%s", policyTargetId)).entity(policyTarget).execute();
     }

}
