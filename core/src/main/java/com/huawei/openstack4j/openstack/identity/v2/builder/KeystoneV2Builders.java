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
package com.huawei.openstack4j.openstack.identity.v2.builder;


import com.huawei.openstack4j.model.identity.v2.builder.*;
import com.huawei.openstack4j.openstack.identity.v2.domain.*;


public class KeystoneV2Builders implements IdentityV2Builders {

    private IdentityV2Builders KeystoneV2Builders() {
        return this;
    }

    @Override
    public UserBuilder user() {
        return KeystoneUser.builder();
    }

    @Override
    public EndpointBuilder endpoint() {
        return KeystoneEndpoint.builder();
    }

    @Override
    public RoleBuilder role() {
        return KeystoneRole.builder();
    }

    @Override
    public ServiceBuilder service() {
        return KeystoneService.builder();
    }

    @Override
    public ServiceEndpointBuilder serviceEndpoint() {
        return KeystoneServiceEndpoint.builder();
    }

    @Override
    public TenantBuilder tenant() {
        return KeystoneTenant.builder();
    }
}
