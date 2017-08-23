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

/**
 * A Builder which creates a Identity Tenant
 * 
 * @author jeremy
 */
public interface TenantBuilder extends Builder<TenantBuilder, Tenant> {
	
	/**
	 * @see Tenant#getName()
	 */
	TenantBuilder name(String name);
	
	/**
	 * @see Tenant#getDescription()
	 */
	TenantBuilder description(String desc);
	
	/**
	 * @see Tenant#getId()
	 */
	TenantBuilder id(String id);
	
	/**
	 * @see Tenant#getEnabled()
	 */
	TenantBuilder enabled(boolean enabled);

}
