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
package com.huawei.openstack4j.openstack.identity.v3.builder;

import com.huawei.openstack4j.model.identity.v3.builder.*;
import com.huawei.openstack4j.openstack.identity.v3.domain.*;

/**
 * The Identity V3 Builders
 */
public class KeystoneV3Builders implements IdentityV3Builders {

    private IdentityV3Builders KeystoneV3Builders() {
        return this;
    }

    @Override
    public CredentialBuilder credential() {
        return KeystoneCredential.builder();
    }

    @Override
    public DomainBuilder domain() {
        return KeystoneDomain.builder();
    }

    @Override
    public EndpointBuilder endpoint() {
        return KeystoneEndpoint.builder();
    }

    @Override
    public GroupBuilder group() {
        return KeystoneGroup.builder();
    }

    @Override
    public PolicyBuilder policy() {
        return KeystonePolicy.builder();
    }

    @Override
    public ProjectBuilder project() {
        return KeystoneProject.builder();
    }

    @Override
    public RegionBuilder region() {
        return KeystoneRegion.builder();
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
    public UserBuilder user() {
        return KeystoneUser.builder();
    }
}
