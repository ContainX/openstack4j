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

import java.util.Map;

/**
 * A share server is created by multi-tenant back-end drivers where shares are hosted.
 * For example, with the <code>generic</code> driver, shares are hosted on Compute VMs.
 * With the <code>cluster_mode</code> driver from NetApp, shares are hosted on virtual storage servers,
 * also known as Vservers or SVMs.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareServer extends ModelEntity {
    enum Status {
        ACTIVE, ERROR, DELETING, CREATING;

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
     * @return the share server ID
     */
    String getId();

    /**
     * @return the project ID
     */
    String getProjectId();

    /**
     * @return the share server status
     */
    Status getStatus();

    /**
     * @return the back-end details for a server
     */
    Map<String, String> getBackendDetails();

    /**
     * @return the UUID of a share network that is associated with the share server
     */
    String getShareNetworkId();

    /**
     * @return the name of a share network that is associated with the share server
     */
    String getShareNetworkName();

    /**
     * @return the share server host name or IP address
     */
    String getHost();

    /**
     * @return the date and time stamp when the share server was created
     */
    String getCreatedAt();

    /**
     * @return the date and time stamp when the share server was updated
     */
    String getUpdatedAt();
}
