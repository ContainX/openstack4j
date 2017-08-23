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
package com.huawei.openstack4j.model.manila;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents a Manila service and their binary.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface Service extends ModelEntity {
    enum Status {
        ENABLED,
        DISABLED;

        @JsonCreator
        public static Status value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    enum State {
        UP,
        DOWN;

        @JsonCreator
        public static State value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * @return the service ID
     */
    Integer getId();

    /**
     * @return the service status, which is <code>enabled</code> or <code>disabled</code>
     */
    Status getStatus();

    /**
     * @return the service binary name
     */
    String getBinary();

    /**
     * @return the availability zone
     */
    String getZone();

    /**
     * @return the host name
     */
    String getHost();

    /**
     * @return the current state of the service
     */
    State getState();

    /**
     * @return the date and time stamp when the service was updated
     */
    String getUpdatedAt();
}
