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
package com.huawei.openstack4j.openstack.identity.v3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.identity.AuthStore;
import com.huawei.openstack4j.model.identity.AuthVersion;

@JsonRootName("auth")
public class Credentials extends Auth implements AuthStore {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "passwordCredentials")
    private PasswordCredentials passwordCreds = new PasswordCredentials();

    public Credentials() {
        super(Type.CREDENTIALS);
    }

    public Credentials(String username, String password) {
        this();
        passwordCreds.setCredentials(username, password);
    }

    public Credentials(String username, String password, String domainId) {
        this();
        passwordCreds.setCredentials(username, password);
        setDomainId(domainId);
    }

    @JsonIgnore
    public String getUsername() {
        return passwordCreds.username;
    }

    @JsonIgnore
    public String getPassword() {
        return passwordCreds.password;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return getDomainId();
    }

    @JsonIgnore
    @Override
    public String getName() {
        return getDomainName();
    }

    private static final class PasswordCredentials {

        @JsonProperty
        String username;
        @JsonProperty
        String password;

        public void setCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }
}
