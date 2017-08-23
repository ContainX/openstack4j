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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.Server.Status;

/**
 * Resets the state of a server to a specified state.
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("os-resetState")
public class ResetStateAction implements ServerAction {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("state")
    private final Status state;
    
    public ResetStateAction(Status state) {
        this.state = state;
    }
    
    public static ResetStateAction create(Status state) {
        return new ResetStateAction(state);
    }

    public Status getState() {
        return state;
    }
}

