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

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyRule;

/**
 * This interface defines all methods for the manipulation of policy rule
 * 
 * @author vinod borole
 *  
 */
public interface PolicyRuleService {
    /**
     * List all policy rules
     * 
     * @return List of policy rules
     */
    List<? extends PolicyRule> list();
    /**
     * Returns list of policy rules filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyRule> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy rule by ID
     *
     * @param id the policy rule id
     * @return policy rule or null if not found
     */
    PolicyRule get(String id);
    /**
     * Delete of the policy rule
     * @param id the policy rule id
     * @return the rule response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy rule
     *
     * @param policy rule
     * @return the newly created policy rule
     */
    PolicyRule create(PolicyRule policyRule);
    /**
     * Updates an existing policy rule
     * 
     * @param policy rule identifier
     * @param policy rule that is be used to updated
     * @return the updated policy rule
     */
    PolicyRule update(String policyRuleId,PolicyRule policyRule);
}
