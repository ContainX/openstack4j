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
package com.huawei.openstack4j.api.heat;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.heat.SoftwareConfig;

/**
 * Software Configuration Service for HEAT Orchestration
 * 
 * @author Jeremy Unruh
 */
public interface SoftwareConfigService extends RestService {

    /**
     * Creates a new Software Config.  See {@link Builders#softwareConfig()} for creating the model
     * @param sc the software config to create
     * @return the newly created SoftwareConfig
     */
    SoftwareConfig create(SoftwareConfig sc);
    
    /**
     * Fetches a Software Configuration by ID
     * @param configId the configuration ID
     * @return SoftwareConfig
     */
    SoftwareConfig show(String configId);
    
    /**
     * Deletes a Software Config by ID
     * 
     * @param configId the software config ID to delete
     * @return the action response
     */
    ActionResponse delete(String configId);
    
}
