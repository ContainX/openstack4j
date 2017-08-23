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
package com.huawei.openstack4j.model.gbp.builder;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.gbp.PolicyTargetGroupCreate;
/**
 * A builder which produces a Policy Target Group object
 * 
 * @author vinod borole
 */
public interface PolicyTargetGroupBuilder extends Builder<PolicyTargetGroupBuilder, PolicyTargetGroupCreate> {

    PolicyTargetGroupBuilder name(String name);
    PolicyTargetGroupBuilder description(String description);
    PolicyTargetGroupBuilder isShared(boolean shared);
    PolicyTargetGroupBuilder consumedPolicyRuleSets(List<String> policyRuleSet);
    PolicyTargetGroupBuilder providedPolicyRuleSets(List<String> policyRuleSet);
    PolicyTargetGroupBuilder policyTargets(List<String> policyTargets);
    PolicyTargetGroupBuilder networkServicePolicyId(String id);
    PolicyTargetGroupBuilder l2Policy(String id);
    PolicyTargetGroupBuilder serviceManagement(boolean serviceManagement);
}
 