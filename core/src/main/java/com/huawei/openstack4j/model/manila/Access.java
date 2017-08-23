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
 * Represents the access to a a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface Access extends ModelEntity {
    enum Level {
        RW, RO;

        @JsonCreator
        public static Level value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    public enum Type {
        IP, CERT, USER;

        @JsonCreator
        public static Type value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    enum State {
        NEW, ACTIVE, ERROR;

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
     * @return the access rule ID
     */
    String getId();

    /**
     * @return the UUID of the share to which you are granted or denied access
     */
    String getShareId();

    /**
     * @return the access rule state
     */
    State getState();

    /**
     * @return the date and time stamp when the access rule was created
     */
    String getCreatedAt();

    /**
     * @return the date and time stamp when the access rule was updated
     */
    String getUpdatedAt();

    /**
     * @return the rule access type
     */
    Type getAccessType();

    /**
     * @return the access that the back end grants or denies
     */
    String getAccessTo();

    /**
     * @return the share access level
     */
    Level getAccessLevel();
}
