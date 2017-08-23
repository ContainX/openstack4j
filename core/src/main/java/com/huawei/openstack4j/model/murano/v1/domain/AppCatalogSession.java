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
package com.huawei.openstack4j.model.murano.v1.domain;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * @author Nikolay Mahotkin.
 */
public interface AppCatalogSession extends ModelEntity {
    /**
     * @return the session id
     */
    String getId();

    /**
     * @return the environment id
     */
    String getEnvId();

    /**
     *
     * @return the user id
     */
    String getUserId();
    /**
     * @return the created date
     */
    String getCreated();

    /**
     * @return the updated date
     */
    String getUpdated();

    /**
     * @return the state of the session
     */
    String getState();

    /**
     * @return the version
     */
    String getVersion();
}
