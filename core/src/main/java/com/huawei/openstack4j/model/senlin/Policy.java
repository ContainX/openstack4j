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
package com.huawei.openstack4j.model.senlin;

import java.util.Date;
import java.util.Map;

import com.huawei.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Policy.
 * All getters map to the possible return values of
 * <code> GET /v1/policies/​{policy_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Policy extends ResourceEntity {

    /**
     * Returns the domain of the policy
     *
     * @return the domain of the policy
     */
    String getDomain();

    /**
     * Returns the project of the policy
     *
     * @return the project of the policy
     */
    String getProject();

    /**
     * Returns the user of the policy
     *
     * @return the user of the policy
     */
    String getUser();

    /**
     * Returns the data of the policy
     *
     * @return the data of the policy
     */
    Map<String, Object> getData();

    /**
     * Returns the spec of the policy
     *
     * @return the spec of the policy
     */
    Map<String, Object> getSpec();

    /**
     * Returns the type of the policy
     *
     * @return the type of the policy
     */
    String getType();

    /**
     * Returns the created at time of the policy
     *
     * @return the created at time of the policy
     */
    Date getCreatedAt();

    /**
     * Returns the updated at time of the policy
     *
     * @return the updated at time of the policy
     */
    Date getUpdatedAt();
}
