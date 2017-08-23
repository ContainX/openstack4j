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
package com.huawei.openstack4j.openstack.identity.v2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.identity.AuthStore;
import com.huawei.openstack4j.model.identity.AuthVersion;

@JsonRootName("auth")
public class RaxApiKeyCredentials extends Auth implements AuthStore {

    private static final long serialVersionUID = 1L;

    @JsonProperty("RAX-KSKEY:apiKeyCredentials")
    private ApiKeyCredentials apikeyCreds = new ApiKeyCredentials();


    public RaxApiKeyCredentials() {
        super(Type.RAX_APIKEY);
    }

    public RaxApiKeyCredentials(String username, String apiKey) {
        this();
        apikeyCreds.setCredentials(username, apiKey);
    }

    @Override
    @JsonIgnore
	public String getTenantId() {
        return super.getTenantId();
	}

    @Override
    @JsonIgnore
	public String getTenantName() {
        return super.getTenantName();
	}

    @JsonIgnore
    public String getUsername() {
        return apikeyCreds.username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return getApiKey();
    }

    @JsonIgnore
    public String getApiKey() {
        return apikeyCreds.apiKey;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return getTenantId();
    }

    @JsonIgnore
    @Override
    public String getName() {
        return getTenantName();
    }

    private static final class ApiKeyCredentials {

        @JsonProperty
        String username;
        @JsonProperty
        String apiKey;

        public void setCredentials(String username, String apiKey) {
            this.username = username;
            this.apiKey = apiKey;
        }
    }


    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V2;
    }
}
