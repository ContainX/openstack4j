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
package com.huawei.openstack4j.model.identity.v2;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v2.builder.TenantBuilder;

/**
 * Tenant Model class use to group/isolate resources and/or identity objects
 * 
 * @author Jeremy Unruh
 * 
 * @see <a href="http://docs.openstack.org/api/openstack-identity-service/2.0/content/GET_listTenants_v2.0_tenants_Tenant_Operations.html#GET_listTenants_v2.0_tenants_Tenant_Operations-Response"
 */
public interface Tenant extends ModelEntity, Buildable<TenantBuilder> {
	
	/**
	 * By providing an ID it is assumed this object will be mapped to an existing Tenant.
	 *
	 * @return the id of the tenant
	 */
	String getId();
	
	/**
	 * @return the name of the tenant
	 */
	String getName();
	
	/**
	 * @return the description of the tenant
	 */
	String getDescription();
	
	/**
	 * @return if the tenant is enabled
	 */
	boolean isEnabled();
	
	void delete();
	
	void update();
	
	void addUser(String userId, String roleId);
	
	void removeUser(String userId, String roleId);
	
}
