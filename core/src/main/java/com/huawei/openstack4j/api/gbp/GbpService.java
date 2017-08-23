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
package com.huawei.openstack4j.api.gbp;

import com.huawei.openstack4j.common.RestService;
/**
 * This interface contains all available GbpServices
 * 
 * @author vinod borole
 * 
 */
public interface GbpService extends RestService {

    /**
     * Service implementation which provides methods for manipulation of external Policies
     * 
     * @return ExternalPolicyService
     */
    ExternalPolicyService externalPolicy();
    /**
     * Service implementation which provides methods for manipulation of external segments
     * 
     * @return ExternalSegmentService
     */     
    ExternalSegmentService externalSegment();
    
    /**
     * Service implementation which provides methods for manipulation of groups
     * 
     * @return GroupService
     */
    GroupService group();
    /**
     * Service implementation which provides methods for manipulation of l2policies
     * 
     * @return L2policyService
     */
    L2policyService l2Policy();
    /**
     * Service implementation which provides methods for manipulation of l3policies
     * 
     * @return L3policyService
     */
    L3policyService l3Policy();
    /**
     * Service implementation which provides methods for manipulation of nat pools
     * 
     * @return NatPoolService
     */
    NatPoolService natPool();
    /**
     * Service implementation which provides methods for manipulation of network services
     * 
     * @return NetworkPolicyService
     */
    NetworkPolicyService networkPolicyService();
    /**
     * Service implementation which provides methods for manipulation of policy actions
     * 
     * @return PolicyActionService
     */
    PolicyActionService policyAction();
    /**
     * Service implementation which provides methods for manipulation of policy rules
     * 
     * @return PolicyRuleService
     */
    PolicyRuleService policyRule();
    /**
     * Service implementation which provides methods for manipulation of policy rules Sets
     * 
     * @return PolicyRuleSetService
     */
    PolicyRuleSetService policyRuleSet();
    /**
     * Service implementation which provides methods for manipulation of policy targets
     * 
     * @return PolicyTargetService
     */
    PolicyTargetService policyTarget();
    /**
     * Service implementation which provides methods for manipulation of policy classifiers
     * 
     * @return PolicyClassifierService
     */
    PolicyClassifierService policyClassifier();
    /**
     * Service implementation which provides methods for manipulation of service chains 
     * 
     * @return ServicechainService
     */
    ServicechainService servicechain();
    /**
     * Service implementation which provides methods for manipulation of service profiles
     * 
     * @return ServiceProfileService
     */
    ServiceProfileService serviceProfile();
}
