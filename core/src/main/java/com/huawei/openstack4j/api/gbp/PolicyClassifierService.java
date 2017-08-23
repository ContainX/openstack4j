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
import com.huawei.openstack4j.model.gbp.PolicyClassifier;
import com.huawei.openstack4j.model.gbp.PolicyClassifierUpdate;

/**
 * This interface defines all methods for the manipulation of policy classifiers
 * 
 * @author vinod borole
 *  
 */
public interface PolicyClassifierService {
    /**
     * List all policy classifier
     * 
     * @return List of policy classifier
     */
    List<? extends PolicyClassifier> list();
    /**
     * Returns list of policy classifier filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends PolicyClassifier> list(Map<String, String> filteringParams);
    /**
     * Get the specified policy classifier by ID
     *
     * @param id the policy classifier id
     * @return policy classifier or null if not found
     */
    PolicyClassifier get(String id);
    /**
     * Delete of the policy classifier
     * @param id the policy classifier id
     * @return the classifier response
     */
    ActionResponse delete(String id);
    /**
     * Create a new policy classifier
     *
     * @param policy classifier
     * @return the newly created policy classifier
     */
    PolicyClassifier create(PolicyClassifier policyClassifier);
    /**
     * Updates an existing policy classifier
     * 
     * @param policy classifier identifier
     * @param policy classifier that is be used to updated
     * @return the updated policy classifier
     */
    PolicyClassifier update(String policyClassifierId,PolicyClassifierUpdate policyClassifier);
}
