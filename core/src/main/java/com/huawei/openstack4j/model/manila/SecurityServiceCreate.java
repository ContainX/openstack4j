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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.builder.SecurityServiceCreateBuilder;

/**
 * Object used to create new security services.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface SecurityServiceCreate extends ModelEntity, Buildable<SecurityServiceCreateBuilder> {
    /**
     * @return the security service type
     */
    SecurityService.Type getType();

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
}
