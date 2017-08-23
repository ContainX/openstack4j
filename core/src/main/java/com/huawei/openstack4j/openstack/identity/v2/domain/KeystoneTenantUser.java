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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.identity.v2.TenantUser;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * A User from a Tenant perspective implemenation
 *
 * @author Jeremy Unruh
 */
public class KeystoneTenantUser implements TenantUser {

    private static final long serialVersionUID = 1L;

    String id;
    String name;
    String email;
    boolean enabled;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                      .add("id", id).add("name", name).add("email", email).add("enabled", enabled)
                      .toString();
    }

    public static class TenantUsers extends ListResult<KeystoneTenantUser> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("users")
        private List<KeystoneTenantUser> list;

        public List<KeystoneTenantUser> value() {
            return list;
        }
    }

}
