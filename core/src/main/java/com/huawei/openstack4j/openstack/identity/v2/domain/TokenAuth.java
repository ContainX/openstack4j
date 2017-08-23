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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Represents a Token class used for V2 token authentication
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("auth")
public class TokenAuth extends Auth {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private Token token;
    
    public TokenAuth(String tokenId, String tenantName, String tenantId) {
        super(Type.TOKEN);
        this.token = new Token(tokenId);
        setTenantName(tenantName);
        setTenantId(tenantId);
    }
    
    protected static class Token {

        @JsonProperty
        protected String id;
        
        protected Token(String id) {
            this.id = id;
        }
        
    }


}
