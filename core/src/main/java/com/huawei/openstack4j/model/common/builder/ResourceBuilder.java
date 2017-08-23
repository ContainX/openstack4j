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
package com.huawei.openstack4j.model.common.builder;

import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.identity.v3.Tenant;

/**
 * Abstract Resource Builder
 *
 * @param <M> the model type
 * @param <T> the builder type
 */
public abstract class ResourceBuilder<M extends Resource, T extends ResourceBuilder<M,T>> extends BasicResourceBuilder<M, T> {

	/**
	 * Set the Tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the builder
	 */
	public T tenantId(String tenantId) {
		reference().setTenantId(tenantId);
		return self();
	}

	/**
	 * Sets the Tenant.
	 *
	 * @param tenant the tenant
	 * @return the builder
	 */
	public T tenant(Tenant tenant) {
		reference().setTenantId(tenant.getId());
		return self();
	}
	
}
