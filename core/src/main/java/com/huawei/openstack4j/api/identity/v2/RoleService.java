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
package com.huawei.openstack4j.api.identity.v2;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v2.Role;

/**
 * Identity Role based Operations
 * 
 * @author Jeremy Unruh
 */
public interface RoleService extends RestService {

	/**
	 * Adds a global role to a user
	 *
	 * @param userId the user id
	 * @param roleId the role id to add
	 * @return the action response
	 */
	ActionResponse addUserRole(String userId, String roleId);
	
	/**
	 * Adds a tenant based role to a user
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @param roleId the role id
	 * @return the action response
	 */
	ActionResponse addUserRole(String tenantId, String userId, String roleId);

	/**
	 * Removes a global role from a user
	 *
	 * @param userId the user id
	 * @param roleId the role id
	 * @return the action response
	 */
	ActionResponse removeUserRole(String userId, String roleId);
	
	/**
	 * Removes the user role from a user and the associated tenant
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @param roleId the role id
	 * @return the action response
	 */
	ActionResponse removeUserRole(String tenantId, String userId, String roleId);
	
	/**
	 * Lists the global roles
	 *
	 * @return the list<? extends role>
	 */
	List<? extends Role> list();
	
	/**
	 * List roles for user.
	 *
	 * @param userId the user id
	 * @return the list<? extends role>
	 */
	List<? extends Role> listRolesForUser(String userId);
	
	/**
	 * List roles for user.
	 *
	 * @param userId the user id
	 * @param tenantId the tenant id
	 * @return the list<? extends role>
	 */
	List<? extends Role> listRolesForUser(String userId, String tenantId);

	/**
	 * Delete a role by it's ID
	 *
	 * @param roleId the role id
	 * @param the action response
	 */
	ActionResponse delete(String roleId);
	
	/**
	 * Gets a role by ID
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	Role get(String roleId);
	
	/**
	 * Gets a Role by Name
	 * @param name the name of the Role
	 * @return the Role or null if not found
	 */
	Role getByName(String name);
	
	/**
	 * Creates a new Role
	 *
	 * @param name the name of the role
	 * @return the role
	 */
	Role create(String name);
	
}
