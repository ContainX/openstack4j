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
package org.openstack4j.openstack.identity.v3.internal;

import static org.openstack4j.core.transport.ClientConstants.PATH_EXTENSIONS;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.v3.*;
import org.openstack4j.model.common.Extension;
import org.openstack4j.openstack.common.ExtensionValue.ExtensionList;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity V3 service implementation
 *
 */
public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

    @Override
    public CredentialService credentials() {
        return Apis.get(CredentialService.class);
    }

    @Override
    public DomainService domains() {
        return Apis.get(DomainService.class);
    }

    @Override
    public ProjectService projects() {
        return Apis.get(ProjectService.class);
    }

    @Override
    public UserService users() {
        return Apis.get(UserService.class);
    }

    @Override
    public RoleService roles() {
        return Apis.get(RoleService.class);
    }

    @Override
    public GroupService groups() {
        return Apis.get(GroupService.class);
    }

    @Override
    public PolicyService policies() {
        return Apis.get(PolicyService.class);
    }

    @Override
    public ServiceEndpointService serviceEndpoints() {
        return Apis.get(ServiceEndpointService.class);
    }

    @Override
    public RegionService regions() {
        return Apis.get(RegionService.class);
    }

    @Override
    public TokenService tokens() {
        return Apis.get(TokenService.class);
    }
    
    @Override
    public List<? extends Extension> listExtensions() {
        return get(ExtensionList.class, PATH_EXTENSIONS).execute().getList();
    }

}
