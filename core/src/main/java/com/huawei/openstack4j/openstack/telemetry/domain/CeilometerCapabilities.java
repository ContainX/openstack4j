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
package com.huawei.openstack4j.openstack.telemetry.domain;

import java.util.Map;

import com.huawei.openstack4j.model.telemetry.Capabilities;

/**
 * A single Representation for capabilities.
 * 
 * @author Shital Patil
 */

public class CeilometerCapabilities implements Capabilities {

    private static final long serialVersionUID = 1L;

    Map<String, Boolean> api;

    Map<String, Boolean> storage;

    Map<String, Boolean> eventStorage;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Boolean> getAPI() {
        return api;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Boolean> getStorage() {
        return storage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Boolean> getEventStorage() {
        return eventStorage;
    }

}
