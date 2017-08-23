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

/**
 * SecurityGroup related Actions
 * 
 * @author Jeremy Unruh
 */
public class SecurityGroupActions implements ServerAction {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("name")
    private String name;
    
    protected SecurityGroupActions(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static Add add(String name) {
        return new Add(name);
    }
    
    public static Remove remove(String name) {
        return new Remove(name);
    }
    
    @JsonRootName("addSecurityGroup")
    public static class Add extends SecurityGroupActions {

        private static final long serialVersionUID = 1L;

        public Add(String name) {
            super(name);
        }
    }
    
    @JsonRootName("removeSecurityGroup")
    public static class Remove extends SecurityGroupActions {

        private static final long serialVersionUID = 1L;

        public Remove(String name) {
            super(name);
        }
    }
}
