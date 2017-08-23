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

import java.util.List;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.identity.v2.UserService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v2.Role;
import com.huawei.openstack4j.model.identity.v2.User;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneUser;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneUser.Users;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity User based Operations
 * 
 * @author Jeremy Unruh
 */
public class UserServiceImpl extends BaseOpenStackService implements UserService {
	
	@Override
	public List<? extends User> list() {
		return get(Users.class, uri("/users")).execute().getList();
	}
	
	@Override
	public User get(String userId) {
		checkNotNull(userId);
		return get(KeystoneUser.class, uri("/users/%s", userId)).execute();
	}
	
	@Override
	public List<? extends User> listTenantUsers(String tenantId) {
		checkNotNull(tenantId);
		return get(Users.class, uri("/tenants/%s/users", tenantId)).execute().getList();
	}

	@Override
	public User create(String tenantId, String name, String password, String email, boolean enabled) {
		checkNotNull(name);
		checkNotNull(password);
		return create(KeystoneUser.builder().name(name).tenantId(tenantId).email(email).password(password).enabled(enabled).build());
	}
	
	@Override
	public User create(User user) {
		checkNotNull(user);
		return post(KeystoneUser.class, uri("/users")).entity(user).execute();
	}

	@Override
	public ActionResponse delete(String userId) {
		checkNotNull(userId);
		return deleteWithResponse(uri("/users/%s", userId)).execute();
	}

	@Override
	public User enableUser(String userId, boolean enabled) {
		checkNotNull(userId);
		return put(KeystoneUser.class, uri("/users/%s/OS-KSADM/enabled", userId)).entity(KeystoneUser.builder().enabled(enabled).build()).execute();
	}

	@Override
	public User update(User user) {
		checkNotNull(user);
		return put(KeystoneUser.class, uri("/users/%s", user.getId())).entity(user).execute();
	}

	@Override
	public ActionResponse changePassword(String userId, String password) {
		checkNotNull(userId);
		checkNotNull(password);
		return put(ActionResponse.class, uri("/users/%s/OS-KSADM/password", userId)).entity(KeystoneUser.builder().id(userId).password(password).build()).execute();
	}

	@Override
	public List<? extends Role> listRoles(String userId) {
		checkNotNull(userId);
		return Apis.getIdentityV2Services().roles().listRolesForUser(userId);
	}

	@Override
	public List<? extends Role> listRoles(User user) {
		checkNotNull(user);
		return listRoles(user.getId());
	}

	@Override
	public List<? extends Role> listRolesOnTenant(String userId, String tenantId) {
		return Apis.getIdentityV2Services().roles().listRolesForUser(userId, tenantId);
	}

	@Override
	public List<? extends Role> listRolesOnCurrentTenant(User user) {
		checkNotNull(user);
		return Apis.getIdentityV2Services().roles().listRolesForUser(user.getId(), user.getTenantId());
	}
	
	@Override
	public User getByName(String userName) {
	    checkNotNull(userName);
	    return get(KeystoneUser.class, "/users").param("name", userName).execute();   
	}
}
