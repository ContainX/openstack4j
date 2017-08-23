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
package com.huawei.openstack4j.openstack.compute.domain.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;

/**
 * Evacuate for a server from a failed host to a new host
 *  
 */
@JsonRootName("evacuate")
public class EvacuateAction implements ServerAction {

    private static final long serialVersionUID = 1L;

    @JsonProperty("host")
    private String host;
    
    @JsonProperty("adminPass")
    private String adminPass;
    
    @JsonProperty("onSharedStorage")
    private boolean onSharedStorage;
    
    public static EvacuateAction create(EvacuateOptions options) {
    	EvacuateAction action = new EvacuateAction();
        action.host = options.getHost();
        action.adminPass = options.getAdminPass();
        action.onSharedStorage = options.isOnSharedStorage();
        return action;
    }

    public String getHost() {
        return host;
    }

    @JsonIgnore
    public String getAdminPass() {
        return adminPass;
    }

    @JsonIgnore
    public boolean isOnSharedStorage() {
        return onSharedStorage;
    }
    
    @Override
    public String toString() {
        return "evacuate";
    }
    
}
