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

import com.huawei.openstack4j.api.gbp.ExternalPolicyService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.ExternalPolicy;
import com.huawei.openstack4j.model.gbp.ExternalPolicyCreate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalPolicy;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalPolicy.ExternalPolicies;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * External Policy API Implementation
 * 
 * @author vinod borole
 */
public class ExternalPolicyServiceImpl extends BaseNetworkingServices implements ExternalPolicyService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends ExternalPolicy> list() {
        return get(ExternalPolicies.class, uri("/grouppolicy/external_policies")).execute().getList();
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ExternalPolicy> list(Map<String, String> filteringParams) {
        Invocation<ExternalPolicies> externalPolicyInvocation = buildInvocation(filteringParams);
        return externalPolicyInvocation.execute().getList();
    } 

    private Invocation<ExternalPolicies> buildInvocation(Map<String, String> filteringParams) {
        Invocation<ExternalPolicies> externalPolicyInvocation = get(ExternalPolicies.class, "/grouppolicy/external_policies");
        if (filteringParams == null) {
            return externalPolicyInvocation;
        }
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                externalPolicyInvocation = externalPolicyInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return externalPolicyInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalPolicy get(String id) {
        checkNotNull(id);
        return get(GbpExternalPolicy.class, uri("/grouppolicy/external_policies/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/external_policies/%s", id)).execute();
    }

   /**
    * {@inheritDoc}
    */
   @Override
    public ExternalPolicy create(ExternalPolicyCreate externalPolicy) {
         return post(GbpExternalPolicy.class, uri("/grouppolicy/external_policies")).entity(externalPolicy).execute();
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public ExternalPolicy update(String externalPolicyId, ExternalPolicyCreate externalPolicy) {
        checkNotNull(externalPolicyId);
        checkNotNull(externalPolicy);
        return put(GbpExternalPolicy.class, uri("/grouppolicy/external_policies/%s", externalPolicyId)).entity(externalPolicy).execute();
    }

   

}
