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
import com.huawei.openstack4j.model.compute.actions.RebuildOptions;

/**
 * An Action which Rebuilds an existing Server Instance
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("rebuild")
public class RebuildAction implements ServerAction {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private String imageRef;
    
    @JsonProperty
    private String name;
    
    @JsonProperty
    private String adminPass;
    
    public static RebuildAction create(RebuildOptions options) {
        RebuildAction action = new RebuildAction();
        
        if (options != null)
        {
            action.name = options.getName();
            action.adminPass = options.getAdminPass();
            action.imageRef = options.getImageRef();
        }
        return action;
    }

    public String getImageRef() {
        return imageRef;
    }

    public String getName() {
        return name;
    }

    public String getAdminPass() {
        return adminPass;
    }
    
}
