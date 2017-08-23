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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A Service represents a Nova compute service
 *
 * @author Stephan Latour
 */
public interface Service extends ModelEntity {
    /**
     * The status of a Nova service entity
     */
    public enum Status {
        DISABLED, ENABLED, UNRECOGNIZED;

        @JsonCreator
        public static Status forValue(String value) {
            if (value != null) {
                for (Status s : Status.values()) {
                    if (s.name().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return Status.UNRECOGNIZED;
        }
    }

    /**
     * The state of a Nova service entity
     */
    public enum State {
        DOWN, UNRECOGNIZED, UP;

        @JsonCreator
        public static State forValue(String value) {
            if (value != null) {
                for (State s : State.values()) {
                    if (s.name().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return State.UNRECOGNIZED;
        }
    }

    /**
     * @return the binary for this service
     */
    String getBinary();

    /**
     * @return the reason for disabled status of this service
     */
    String getDisabledReason();

    /**
     * @return the host for this service
     */
    String getHost();

    /**
     * @return the id for this service
     */
    String getId();

    /**
     * @return the status of the service
     */
    State getState();

    /**
     * @return the status of the service
     */
    Status getStatus();

    /**
     * 
     * @return last updated time
     */
    Date getUpdatedAt();

    /**
     * @return the zone for this service
     */
    String getZone();
    
    /**
     * @return Whether or not this service was forced down manually by an administrator
     */
    String getDorcedDown();
}