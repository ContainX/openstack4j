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
package com.huawei.openstack4j.openstack.manila.domain.actions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Action changing the size of shares.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public abstract class SizeAction implements ShareAction {
    private static final long serialVersionUID = 1L;

    @JsonProperty("new_size")
    private Integer newSize;

    protected SizeAction(Integer newSize) {
        this.newSize = newSize;
    }

    public Integer getNewSize() {
        return newSize;
    }

    @JsonRootName("os-extend")
    public static class Extend extends SizeAction {
        Extend(Integer newSize) {
            super(newSize);
        }
    }

    @JsonRootName("os-shrink")
    public static class Shrink extends SizeAction {
        Shrink(Integer newSize) {
            super(newSize);
        }
    }
}
