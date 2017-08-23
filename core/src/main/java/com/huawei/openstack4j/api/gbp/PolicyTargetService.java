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
import com.huawei.openstack4j.model.gbp.PolicyTarget;

/**
 * This interface defines all methods for the manipulation of policy targets
 * 
 * @author vinod borole
 * 
 */
public interface PolicyTargetService{
    /** 
     * List all policy target
     * 
     * @return List of policy target
     */
    List<? extends PolicyTarget> list();
    /**
     * Returns list of policy target filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyTarget> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy target by ID
     *
     * @param id the policy target id
     * @return policy target or null if not found
     */
    PolicyTarget get(String id);
    /**
     * Delete of the policy target
     * @param id the policy target id
     * @return the target response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy target
     *
     * @param policy target
     * @return the newly created policy target
     */
   PolicyTarget create(PolicyTarget policyTarget);
   /**
    * Updates an existing policy target
    * 
    * @param policy target identifier
    * @param policy target that is be used to updated
    * @return the updated policy target
    */
   PolicyTarget update(String policyTargetId,PolicyTarget policyTarget);
}
