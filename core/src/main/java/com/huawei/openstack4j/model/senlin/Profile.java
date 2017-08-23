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
 * This interface describes the getter-methods (and thus components) of a Profile.
 * All getters map to the possible return values of
 * <code> GET /v1/profiles/​{profile_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Profile extends ResourceEntity {

    /**
     * Returns the created at time of the profile
     *
     * @return the created at time of the profile
     */
    Date getCreatedAt();

    /**
     * Returns the domain of the profile
     *
     * @return the domain of the profile
     */
    String getDomain();

    /**
     * Returns the project of the profile
     *
     * @return the project of the profile
     */
    String getProject();

    /**
     * Returns the metadata of the profile
     *
     * @return the metadata of the profile
     */
    Map<String, Object> getMetadata();

    /**
     * Returns the spec of the profile
     *
     * @return the spec of the profile
     */
    Map<String, Object> getSpec();

    /**
     * Returns the type of the profile
     *
     * @return the type of the profile
     */
    String getType();

    /**
     * Returns the updated at time of the profile
     *
     * @return the updated at time of the profile
     */
    Date getUpdatedAt();

    /**
     * Returns the user of the profile
     *
     * @return the user of the profile
     */
    String getUser();
}
