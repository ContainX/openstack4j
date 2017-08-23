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
import com.huawei.openstack4j.model.identity.v2.Tenant;
import com.huawei.openstack4j.model.identity.v2.TenantUser;

/**
 * Identity Tenant based Operations
 * 
 * @author Jeremy Unruh
 */
public interface TenantService extends RestService {

	/**
	 * Lists tenants to which the specified token has access.
	 *
	 * @return List of Tenants
	 */
	List<? extends Tenant> list();
	
	/**
	 * Gets detailed information about a specified tenant by ID
	 *
	 * @param tenantId the tenant id
	 * @return the tenant
	 */
	Tenant get(String tenantId);
	
	/**
	 * Gets detailed information about a specified tenant by name
	 *
	 * @param tenantName the tenant name
	 * @return the tenant
	 */
	Tenant getByName(String tenantName);
	
	/**
	 * Creates a new Tenant
	 *
	 * @param tenant the tenant to create
	 * @return the newly created tenant and it's assigned ID
	 */
	Tenant create(Tenant tenant);
	
	/**
	 * Deletes the specified tenant by ID
	 *
	 * @param tenantId the tenant id
	 * @return the action response
	 */
	ActionResponse delete(String tenantId);
	
	/**
	 * Updates the tenant (ID must be set within the inbound tenant)
	 *
	 * @param tenant the tenant
	 * @return the tenant
	 */
	Tenant update(Tenant tenant);
	
	/**
	 * Returns a list of users associated by the {@code tenantId}
	 * @param tenantId the tenant id to query users for
	 * @return List of TenantUser
	 */
	List<? extends TenantUser> listUsers(String tenantId);
	
}
