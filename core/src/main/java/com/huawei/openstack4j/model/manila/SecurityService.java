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
 * A security service stores configuration information for clients for authentication and authorization (AuthN/AuthZ).
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface SecurityService extends ModelEntity {
    enum Type {
        LDAP, KERBEROS, ACTIVE_DIRECTORY;

        @JsonCreator
        public static Type value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * @return the security service status
     */
    String getStatus();

    /**
     * @return the security service ID
     */
    String getId();

    /**
     * @return the project where the security service was created
     */
    String getProjectId();

    /**
     * @return the security service type
     */
    Type getType();

    /**
     * @return the security service name
     */
    String getName();

    /**
     * @return the security service description
     */
    String getDescription();

    /**
     * @return the DNS IP address that is used inside the tenant network
     */
    String getDnsIp();

    /**
     * @return the security service user or group name that is used by the tenant
     */
    String getUser();

    /**
     * @return the user password
     */
    String getPassword();

    /**
     * @return the security service domain
     */
    String getDomain();

    /**
     * @return the security service host name or IP address
     */
    String getServer();

    /**
     * @return the date and time stamp when the security service was created
     */
    String getCreatedAt();

    /**
     * @return the date and time stamp when the security service was updated
     */
    String getUpdatedAt();

    /**
     * @return the share networks this security is added to
     */
    List<String> getShareNetworks();
}
