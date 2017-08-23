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

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.ExternalPolicy;
import com.huawei.openstack4j.model.gbp.ExternalPolicyCreate;
 
/**
 * This interface defines all methods for the manipulation of external policies
 * 
 * @author vinod borole
 * 
 */
public interface ExternalPolicyService extends RestService{

    /**
     * List all external policies
     * 
     * @return List of external policies
     */
    List<? extends ExternalPolicy> list();
    /**
     * Returns list of external policies filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends ExternalPolicy> list(Map<String, String> filteringParams);
    /**
     * Get the specified external policy by ID
     *
     * @param id the external policy id
     * @return the external policy or null if not found
     */
    ExternalPolicy get(String id);
    /**
     * Delete of the external policy
     * @param id the external policy id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new external policy
     *
     * @param external policy
     * @return the newly created external policy
     */
    ExternalPolicy create(ExternalPolicyCreate externalPolicy);
    /**
     * Updates an existing external policy
     * 
     * @param external policy identifier
     * @param external policy that is be used to updated
     * @return the updated external policy
     */
    ExternalPolicy update(String externalPolicyId,ExternalPolicyCreate externalPolicy);
}
