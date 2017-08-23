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
package com.huawei.openstack4j.openstack.identity.v2.internal;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.identity.v2.IdentityService;
import com.huawei.openstack4j.api.identity.v2.RoleService;
import com.huawei.openstack4j.api.identity.v2.ServiceManagerService;
import com.huawei.openstack4j.api.identity.v2.TenantService;
import com.huawei.openstack4j.api.identity.v2.UserService;
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.model.identity.v2.Endpoint;
import com.huawei.openstack4j.openstack.common.ExtensionValue.ExtensionList;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneEndpoint.Endpoints;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
import com.huawei.openstack4j.openstack.internal.OSClientSession;

public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

	@Override
	public TenantService tenants() {
		return Apis.get(TenantService.class);
	}

	@Override
	public UserService users() {
		return Apis.get(UserService.class);
	}

	@Override
	public List<? extends Extension> listExtensions() {
		return get(ExtensionList.class, PATH_EXTENSIONS).execute().getList();
	}

	@Override
	public RoleService roles() {
		return Apis.get(RoleService.class);
	}

	@Override
	public ServiceManagerService services() {
		return Apis.get(ServiceManagerService.class);
	}

	@Override
	public List<? extends Endpoint> listTokenEndpoints() {
		return get(Endpoints.class, uri("/tokens/%s/endpoints", OSClientSession.OSClientSessionV2.getCurrent().getTokenId())).execute().getList();
	}
	
}
