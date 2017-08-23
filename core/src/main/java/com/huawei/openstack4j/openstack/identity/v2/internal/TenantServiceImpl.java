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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.identity.v2.TenantService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v2.Tenant;
import com.huawei.openstack4j.model.identity.v2.TenantUser;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneTenant;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneTenant.BackwardsCompatTenants;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneTenant.Tenants;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneTenantUser.TenantUsers;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class TenantServiceImpl extends BaseOpenStackService implements TenantService  {

	
	@Override
	public List<? extends Tenant> list() {
		return get(Tenants.class, PATH_TENANTS).execute().getList();
	}

	@Override
	public Tenant get(String tenantId) {
		checkNotNull(tenantId);
		return get(KeystoneTenant.class, PATH_TENANTS, "/", tenantId).execute();
	}

	@Override
	public Tenant getByName(String tenantName) {
		checkNotNull(tenantName);
		return get(BackwardsCompatTenants.class, PATH_TENANTS).param("name", tenantName).execute().getOneOrNull();
	}

	@Override
	public Tenant create(Tenant tenant) {
		checkNotNull(tenant);
		return post(KeystoneTenant.class, PATH_TENANTS).entity(tenant).execute();
	}

	@Override
	public ActionResponse delete(String tenantId) {
		checkNotNull(tenantId);
		return deleteWithResponse(PATH_TENANTS, "/", tenantId).execute();
	}

	@Override
	public Tenant update(Tenant tenant) {
		checkNotNull(tenant);
		return post(KeystoneTenant.class, uri("/tenants/%s", tenant.getId())).entity(tenant).execute();
	}

    @Override
    public List<? extends TenantUser> listUsers(String tenantId) {
        checkNotNull(tenantId);
        return get(TenantUsers.class, uri("/tenants/%s/users", tenantId)).execute().getList();
    }
}
