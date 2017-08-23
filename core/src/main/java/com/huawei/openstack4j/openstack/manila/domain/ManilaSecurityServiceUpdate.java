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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.SecurityService;
import com.huawei.openstack4j.model.manila.SecurityServiceUpdateOptions;

/**
 * Object used to update existing security services.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("security_service")
public class ManilaSecurityServiceUpdate implements ModelEntity {
    private static final long serialVersionUID = 1L;

    private SecurityService.Type type;
    private String name;
    private String description;
    @JsonProperty("dns_ip")
    private String dnsIp;
    private String user;
    private String password;
    private String domain;
    private String server;

    private ManilaSecurityServiceUpdate() {}

    /**
     * Creates a security service from options.
     * This can be used to update an existing security service.
     *
     * @param options the security service update options
     * @return a security service with the given options
     */
    public static ManilaSecurityServiceUpdate fromOptions(SecurityServiceUpdateOptions options) {
        ManilaSecurityServiceUpdate securityServiceUpdate = new ManilaSecurityServiceUpdate();
        securityServiceUpdate.type = options.getType();
        securityServiceUpdate.name = options.getName();
        securityServiceUpdate.description = options.getDescription();
        securityServiceUpdate.dnsIp = options.getDnsIp();
        securityServiceUpdate.user = options.getUser();
        securityServiceUpdate.password = options.getPassword();
        securityServiceUpdate.domain = options.getDomain();
        securityServiceUpdate.server = options.getServer();

        return securityServiceUpdate;
    }
}
