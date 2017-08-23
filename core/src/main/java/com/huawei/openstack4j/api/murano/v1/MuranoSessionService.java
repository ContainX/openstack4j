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

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.AppCatalogSession;

/**
 * @author Nikolay Mahotkin.
 */
public interface MuranoSessionService extends RestService {
    /**
     * Creates a new configuration session.
     *
     * @param environmentId environment identifier.
     * @return Session object.
     */
    AppCatalogSession configure(String environmentId);

    /**
     * Gets session details.
     *
     * @param environmentId environment identifier.
     * @param sessionId session identifier.
     * @return Session object.
     */
    AppCatalogSession get(String environmentId, String sessionId);

    /**
     * Sends signal to deploy to OpenStack.
     *
     * @param environmentId environment identifier.
     * @param sessionId session identifier.
     * @return ActionResponse (status 200 in case of success)
     */
    ActionResponse deploy(String environmentId, String sessionId);

    /**
     * Deletes a configuration session.
     *
     * @param environmentId environment identifier.
     * @param sessionId session identifier.
     * @return ActionResponse (status 200 in case of success)
     */
    ActionResponse delete(String environmentId, String sessionId);
}
