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
import com.huawei.openstack4j.model.gbp.PolicyRuleSet;
 
public interface PolicyRuleSetService {
    /**
     * List all policy rule set
     * 
     * @return List of policy rule set
     */
    List<? extends PolicyRuleSet> list();
    /**
     * Returns list of policy rule set filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyRuleSet> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy rule set by ID
     *
     * @param id the policy rule set id
     * @return policy rule set or null if not found
     */
    PolicyRuleSet get(String id);
    /**
     * Delete of the policy rule set
     * @param id the policy rule set id
     * @return the rule set response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy rule set
     *
     * @param policy rule set
     * @return the newly created policy rule set
     */
    PolicyRuleSet create(PolicyRuleSet policyRuleSet);
    /**
     * Updates an existing policy rule set
     * 
     * @param policy rule set identifier
     * @param policy rule set that is be used to updated
     * @return the updated policy rule set
     */
    PolicyRuleSet update(String policyRuleSetId,PolicyRuleSet policyRuleSet);
}
