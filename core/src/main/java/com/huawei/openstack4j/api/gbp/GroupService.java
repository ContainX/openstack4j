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
import com.huawei.openstack4j.model.gbp.PolicyTargetGroup;
import com.huawei.openstack4j.model.gbp.PolicyTargetGroupCreate;

/**
 * This interface defines all methods for the manipulation of groups
 * 
 * @author vinod borole
 * 
 */ 
public interface GroupService{
    /**
     * List all policy target group
     * 
     * @return List of policy target group
     */
    List<? extends PolicyTargetGroup> list();
    /**
     * Returns list of policy target group filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyTargetGroup> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy target group by ID
     *
     * @param id the policy target group id
     * @return policy target group or null if not found
     */
    PolicyTargetGroup get(String id);
    /**
     * Delete of the policy target group
     * @param id the policy target group id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy target group
     *
     * @param policy target group
     * @return the newly created policy target group
     */
    PolicyTargetGroup create(PolicyTargetGroupCreate policyTargetGroup);
    /**
     * Updates an existing policy target group
     * 
     * @param policy target group identifier
     * @param policy target group that is be used to updated
     * @return the updated policy target group
     */
    PolicyTargetGroup update(String policyTargetGroupId,PolicyTargetGroupCreate policyTargetGroup);
}
