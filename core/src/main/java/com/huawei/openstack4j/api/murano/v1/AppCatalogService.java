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

/**
 * (Murano) App catalog Operations API
 *
 * @author Nikolay Mahotkin
 */
public interface AppCatalogService extends RestService {

    /**
     * Environments Service API
     *
     * @return the environments service
     */
    MuranoEnvironmentService environments();

    /**
     * Murano Sessions API
     *
     * @return the sessions API
     */
    MuranoSessionService sessions();

    /**
     * Murano Services API
     *
     * @return the servces API
     */
    MuranoApplicationService services();

    /**
     * Murano deployments API
     *
     * @return the deployments API
     */
    MuranoDeploymentService deployments();

    /**
     * Murano actions API
     *
     * @return the actions API
     */
    MuranoActionService actions();
}
