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

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.gbp.ExternalPolicyService;
import com.huawei.openstack4j.api.gbp.ExternalSegmentService;
import com.huawei.openstack4j.api.gbp.GbpService;
import com.huawei.openstack4j.api.gbp.GroupService;
import com.huawei.openstack4j.api.gbp.L2policyService;
import com.huawei.openstack4j.api.gbp.L3policyService;
import com.huawei.openstack4j.api.gbp.NatPoolService;
import com.huawei.openstack4j.api.gbp.NetworkPolicyService;
import com.huawei.openstack4j.api.gbp.PolicyActionService;
import com.huawei.openstack4j.api.gbp.PolicyClassifierService;
import com.huawei.openstack4j.api.gbp.PolicyRuleService;
import com.huawei.openstack4j.api.gbp.PolicyRuleSetService;
import com.huawei.openstack4j.api.gbp.PolicyTargetService;
import com.huawei.openstack4j.api.gbp.ServiceProfileService;
import com.huawei.openstack4j.api.gbp.ServicechainService;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * GBP services
 * 
 * @author vinod borole
 */
public class GbpServiceImpl extends BaseNetworkingServices implements GbpService {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalPolicyService externalPolicy() {
        return Apis.get(ExternalPolicyService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalSegmentService externalSegment() {
        return Apis.get(ExternalSegmentService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupService group() { 
        return Apis.get(GroupService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public L2policyService l2Policy() {
        return Apis.get(L2policyService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public L3policyService l3Policy() {
        return Apis.get(L3policyService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NatPoolService natPool() {
        return Apis.get(NatPoolService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkPolicyService networkPolicyService() {
        return Apis.get(NetworkPolicyService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyActionService policyAction() {
        return Apis.get(PolicyActionService.class);
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public PolicyRuleService policyRule() {
        return Apis.get(PolicyRuleService.class);
    }
   /**
    * {@inheritDoc}
    */
    @Override
    public PolicyRuleSetService policyRuleSet() {
        return Apis.get(PolicyRuleSetService.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyTargetService policyTarget() {
        return Apis.get(PolicyTargetService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyClassifierService policyClassifier() {
        return Apis.get(PolicyClassifierService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServicechainService servicechain() {
        return Apis.get(ServicechainService.class);
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public ServiceProfileService serviceProfile() {
        return Apis.get(ServiceProfileService.class);
    }

}
