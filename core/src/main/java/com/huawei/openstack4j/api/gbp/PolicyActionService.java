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
import com.huawei.openstack4j.model.gbp.PolicyAction;
import com.huawei.openstack4j.model.gbp.PolicyActionUpdate;

/**
 * This interface defines all methods for the manipulation of policy actions
 * 
 * @author vinod borole
 *  
 */
public interface PolicyActionService {
    /**
     * List all policy actions
     * 
     * @return List of policy actions
     */
    List<? extends PolicyAction> list();
    /**
     * Returns list of policy actions filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyAction> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy action by ID
     *
     * @param id the policy action id
     * @return policy action or null if not found
     */
    PolicyAction get(String id);
    /**
     * Delete of the policy action
     * @param id the policy action id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy action
     *
     * @param policy action
     * @return the newly created policy action
     */
    PolicyAction create(PolicyAction policyAction);
    /**
     * Updates an existing policy action
     * 
     * @param policy action identifier
     * @param policy action that is be used to updated
     * @return the updated policy action
     */
    PolicyAction update(String policyActionId,PolicyActionUpdate policyAction);
}
