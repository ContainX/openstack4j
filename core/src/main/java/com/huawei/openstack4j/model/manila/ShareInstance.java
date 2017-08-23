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

import java.util.List;

/**
 * Representation of a share instance.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareInstance extends ModelEntity {
    enum Status {
        AVAILABLE, ERROR, CREATING, DELETING, ERROR_DELETING;

        @JsonCreator
        public static Status value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * @return the share instance status
     */
    Status getStatus();

    /**
     * @return the UUID of the share from which the share instance was created
     */
    String getShareId();

    /**
     * @return the availability zone
     */
    String getAvailabilityZone();

    /**
     * @return the date and time stamp when the share instance was created
     */
    String getCreatedAt();

    /**
     * @return the share instance export location
     */
    String getExportLocation();

    /**
     * @return a list of share instance export locations
     */
    List<String> getExportLocations();

    /**
     * @return the share network ID
     */
    String getShareNetworkId();

    /**
     * @return the share server ID
     */
    String getShareServerId();

    /**
     * @return the share instance host name
     */
    String getHost();

    /**
     * @return the share instance ID
     */
    String getId();
}
