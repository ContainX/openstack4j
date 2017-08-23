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
package com.huawei.openstack4j.model.compute.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A Floating IP DNS Extension - Domain Entry
 * 
 * @author Jeremy Unruh
 */
public interface DomainEntry extends ModelEntity {
    
    public enum Scope {
        PUBLIC,
        PRIVATE
        ;
        
        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
        
        @JsonCreator
        public static Scope forValue(String value) {
            if (value != null)
            {
                for (Scope s : Scope.values()) {
                    if (s.name().equalsIgnoreCase(value))
                        return s;
                }
            }
            return Scope.PUBLIC;
        }
    }
    
    /**
     * The associated Availability Zone or NULL
     * 
     * @return availability zone or null
     */
    String getAvailabilityZone();
    
    /**
     * The FQDN domain name
     * 
     * @return the domain name
     */
    String getDomain();
    
    /**
     * The associated project name
     * 
     * @return the project or null if not defined
     */
    String getProject();
    
    /**
     * The scope of this domain name 
     * 
     * @return the current scope
     */
    Scope getScope();

}
