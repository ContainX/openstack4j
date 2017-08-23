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
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.model.identity.v2.Endpoint;

/**
 * Identity Serivce Operations API
 * 
 * @author Jeremy Unruh
 */
public interface IdentityService extends RestService {

	/**
	 * Tenants Service API
	 *
	 * @return the tenant service
	 */
	TenantService tenants();
	
	/**
	 * Users Service API
	 *
	 * @return the user service
	 */
	UserService users();
	
	/**
	 * Role Service API
	 *
	 * @return the role service
	 */
	RoleService roles();
	
	/**
	 * Service Management API
	 *
	 * @return the service manager service
	 */
	ServiceManagerService services();
	
	/**
	 * List extensions currently available on the OpenStack instance
	 *
	 * @return List of extensions
	 */
	List<? extends Extension> listExtensions();
	
	/**
	 * Lists endpoints available for the current authenticated token
	 *
	 * @return List of endpoints
	 */
	List<? extends Endpoint> listTokenEndpoints();
	
}
