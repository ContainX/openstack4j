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
package com.huawei.openstack4j.api.identity.v3;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Policy;

public interface PolicyService extends RestService {

    /**
     * Create a policy
     *
     * @param policy the policy
     * @return the newly created policy
     */
    Policy create(Policy policy);

    /**
     * Create a policy
     *
     * @param blob the policy rule itself as a serialized blob
     * @param type the MIME media type of the serialized policy blob
     * @param projectId the uuid for the associated project
     * @param userId the id of the user who owns the policy
     * @return the newly created policy
     */
    Policy create(String blob, String type, String projectId, String userId);

    /**
     * Get detailed information on a policy by id
     *
     * @param policyId the policy id
     * @return the policy
     */
    Policy get(String policyId);

    /**
     * Update a policy
     *
     * @param policy the policy set to update
     * @return the updated policy
     */
    Policy update(Policy policy);

    /**
     * Delete a policy
     *
     * @param policyId the policy id
     * @return  the ActionResponse
     */
    ActionResponse delete(String policyId);

    /**
     * @return list of policies
     */
    List<? extends Policy> list();

}
