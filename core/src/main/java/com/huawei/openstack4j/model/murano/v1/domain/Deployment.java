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
public interface Deployment extends ModelEntity {
    /**
     *
     * @return state of the deployment.
     */
    String getState();

    /**
     *
     * @return date and time of the start of the deployment.
     */
    String getStarted();

    /**
     *
     * @return date and time of the finish of the deployment.
     */
    String getFinished();

    /**
     *
     * @return the environment id.
     */
    String getEnvironmentId();

    /**
     *
     * @return the id of the deployment.
     */
    String getId();

    /**
     *
     * @return the description Object.
     */
    EnvironmentDescription getDescription();

    /**
     *
     * @return created date.
     */
    String getCreated();

    /**
     *
     * @return updated date.
     */
    String getUpdated();

    /**
     *
     * @return the result.
     */
    DeploymentResult getResult();
}
