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
package com.huawei.openstack4j.api.murano.v1;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.Environment;

public interface MuranoEnvironmentService extends RestService {

    /**
     * List all environments
     *
     * @return list of environments or empty list
     */
    List<? extends Environment> list();

    /**
     * Creates a new environment
     *
     * @param env the environment to create
     * @return the created environment
     */
    Environment create(Environment env);

    /**
     * Gets an environment by ID
     * @param id the environment identifier
     * @return the cluster or null if not found
     */
    Environment get(String id);

    /**
     * Deletes the specified environment
     *
     * @param id the environment identifier
     * @return the action response
     */
    ActionResponse delete(String id);

    /**
     * Renames the specified environment
     *
     * @param id the environment identifier
     * @return the renamed environment
     */
    Environment rename(String id, String name);

}
