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
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.Application;

/**
 * @author Nikolay Mahotkin.
 */
public interface MuranoApplicationService extends RestService {
    /**
     * List all services
     *
     * @param environmentId the environment identifier
     * @param sessionId the session identifier
     * @return list of environments or empty list
     */
    List<? extends Application> list(String environmentId, String sessionId);

    List<? extends Application> list(String environmentId);

    /**
     * Gets services by environmentId
     * @param environmentId the environment identifier
     * @param path the path identifier
     * @param sessionId the session identifier
     * @return the list of requested services (this is a general case for services().get() )
     */
    List<? extends Application> get(String environmentId, String path, String sessionId);

    List<? extends Application> get(String environmentId, String path);

    /**
     * Creates a new service
     *
     * @param environmentId the environment to create
     * @param sessionId session identifier
     * @param data service data structure (object model)
     * @return the created service
     */
    Application create(String environmentId, String sessionId, Map<String, Object> data);

    /**
     *
     * @param jsonString raw String containing the apps configuration.
     * @return the list of created services.
     */
    List<? extends Application> create(String environmentId, String sessionId, String jsonString);

    /**
     * Updates services
     *
     * @param environmentId environment identifier
     * @param sessionId session identifier
     * @param data service data structure (object model)
     * @return create service
     */
    Application update(String environmentId, String sessionId, Map<String, Object> data);
    List<? extends Application> update(String environmentId, String sessionId, String jsonString);

    /**
     * Deletes the specified service
     *
     * @param environmentId the environment identifier
     * @param path the path identifier
     * @param sessionId the session identifier
     * @return the action response
     */
    ActionResponse delete(String environmentId, String path, String sessionId);
}
