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
package com.huawei.openstack4j.model.identity.v2.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v2.Tenant;
import com.huawei.openstack4j.model.identity.v2.User;

/**
 * A Builder which creates an Identity User
 * 
 * @author Jeremy Unruh
 */
public interface UserBuilder extends Builder<UserBuilder, User> {
	
	/**
	 * @see User#getName()
	 */
	UserBuilder name(String name);
	
	/**
	 * ID should only ever be set if the user already exists and this is used for update based actions
	 * @param id the user id
	 * @return this for method chaining
	 */
	UserBuilder id(String id);

	/**
	 * Sets the initial password for the user
	 * @param password the password to set
	 * @return this builder
	 */
	UserBuilder password(String password);
	
	/**
	 * @see User#getEmail()
	 */
	UserBuilder email(String email);
	
	/**
	 * @see User#isEnabled()
	 */
	UserBuilder enabled(boolean enabled);
	
	/**
	 * @see User#getTenantId()
	 */
	UserBuilder tenantId(String tenantId);
	
	/**
	 * Accepts an existing tenant and uses the tenant's id
	 * @see User#getTenantId()
	 */
	UserBuilder tenant(Tenant tenant);

}
