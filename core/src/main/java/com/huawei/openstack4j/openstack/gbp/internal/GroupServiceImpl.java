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

import com.huawei.openstack4j.api.gbp.GroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyTargetGroup;
import com.huawei.openstack4j.model.gbp.PolicyTargetGroupCreate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTargetGroup;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyTargetGroup.PolicyTargetGroups;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Policy target group API Implementation
 * 
 * @author vinod borole
 */
public class GroupServiceImpl extends BaseNetworkingServices implements GroupService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends PolicyTargetGroup> list() {
        return get(PolicyTargetGroups.class, uri("/grouppolicy/policy_target_groups")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PolicyTargetGroup> list(Map<String, String> filteringParams) {
        Invocation<PolicyTargetGroups> policyTargetGroupInvocation = buildInvocation(filteringParams);
        return policyTargetGroupInvocation.execute().getList();
    }
    private Invocation<PolicyTargetGroups> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyTargetGroups> policyTargetGroupInvocation = get(PolicyTargetGroups.class, "/grouppolicy/policy_target_groups");
        if (filteringParams == null) { 
            return policyTargetGroupInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policyTargetGroupInvocation = policyTargetGroupInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policyTargetGroupInvocation;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyTargetGroup get(String id) {
        checkNotNull(id);
        return get(GbpPolicyTargetGroup.class, uri("/grouppolicy/policy_target_groups/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_target_groups/%s", id)).execute();
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public PolicyTargetGroup create(PolicyTargetGroupCreate policyTargetGroup) {
        return post(GbpPolicyTargetGroup.class, uri("/grouppolicy/policy_target_groups")).entity(policyTargetGroup).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyTargetGroup update(String policyTargetGroupId, PolicyTargetGroupCreate policyTargetGroup) {
        checkNotNull(policyTargetGroupId);
        checkNotNull(policyTargetGroup);
        return put(GbpPolicyTargetGroup.class, uri("/grouppolicy/policy_target_groups/%s", policyTargetGroupId)).entity(policyTargetGroup).execute();
    }



}
