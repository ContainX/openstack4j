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
import com.huawei.openstack4j.model.manila.SecurityService;
import com.huawei.openstack4j.model.manila.SecurityServiceCreate;
import com.huawei.openstack4j.model.manila.builder.SecurityServiceCreateBuilder;

@JsonRootName("security_service")
public class ManilaSecurityServiceCreate implements SecurityServiceCreate {
    private SecurityService.Type type;
    private String name;
    private String description;
    @JsonProperty("dns_ip")
    private String dnsIp;
    private String user;
    private String password;
    private String domain;
    private String server;

    @Override
    public SecurityService.Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDnsIp() {
        return dnsIp;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getServer() {
        return server;
    }

    public static SecurityServiceCreateBuilder builder() {
        return new SecurityServiceCreateConcreteBuilder();
    }

    @Override
    public SecurityServiceCreateBuilder toBuilder() {
        return new SecurityServiceCreateConcreteBuilder(this);
    }

    public static class SecurityServiceCreateConcreteBuilder implements SecurityServiceCreateBuilder {
        ManilaSecurityServiceCreate securityServiceCreate;

        SecurityServiceCreateConcreteBuilder() {
            this(new ManilaSecurityServiceCreate());
        }

        SecurityServiceCreateConcreteBuilder(ManilaSecurityServiceCreate securityServiceCreate) {
            this.securityServiceCreate = securityServiceCreate;
        }

        @Override
        public SecurityServiceCreateBuilder type(SecurityService.Type type) {
            securityServiceCreate.type = type;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder name(String name) {
            securityServiceCreate.name = name;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder description(String description) {
            securityServiceCreate.description = description;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder dnsIp(String dnsIp) {
            securityServiceCreate.dnsIp = dnsIp;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder user(String user) {
            securityServiceCreate.user = user;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder password(String password) {
            securityServiceCreate.password = password;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder domain(String domain) {
            securityServiceCreate.domain = domain;
            return this;
        }

        @Override
        public SecurityServiceCreateBuilder server(String server) {
            securityServiceCreate.server = server;
            return this;
        }

        @Override
        public SecurityServiceCreate build() {
            return securityServiceCreate;
        }

        @Override
        public SecurityServiceCreateBuilder from(SecurityServiceCreate in) {
            securityServiceCreate = (ManilaSecurityServiceCreate) in;
            return this;
        }
    }
}
