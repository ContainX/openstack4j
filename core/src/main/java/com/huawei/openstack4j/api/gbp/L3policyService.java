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
import com.huawei.openstack4j.model.gbp.L3Policy;

/**
 * This interface defines all methods for the manipulation of l3policy
 * 
 * @author vinod borole
 *  
 */
public interface L3policyService{
    /**
     * List all l3 policies
     * 
     * @return List of l3 policies
     */
    List<? extends L3Policy> list();
    /**
     * Returns list of l3 policies filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends L3Policy> list(Map<String, String> filteringParams);
    /**
     * Get the specified l3 policy by ID
     *
     * @param id the l3 policy id
     * @return l3 policy or null if not found
     */
    L3Policy get(String id);
    /**
     * Delete of the l3 policy
     * @param id the l3 policy id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new l3 policy
     *
     * @param l3 policy
     * @return the newly created l3 policy
     */
    L3Policy create(L3Policy l3Policy);
    /**
     * Updates an existing l3 policy
     * 
     * @param l3 policy identifier
     * @param l3 policy that is be used to updated
     * @return the updated l3 policy
     */
    L3Policy update(String l3PolicyId,L3Policy l3Policy);
}
