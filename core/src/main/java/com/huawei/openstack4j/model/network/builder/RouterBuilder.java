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
package com.huawei.openstack4j.model.network.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v3.Tenant;
import com.huawei.openstack4j.model.network.ExternalGateway;
import com.huawei.openstack4j.model.network.Router;

/**
 * A Builder that creates a Router
 * 
 * @author Jeremy Unruh
 */
public interface RouterBuilder extends Builder<RouterBuilder, Router> {

	/**
	 * @see Router#getId()
	 */
	RouterBuilder id(String id);
	
	/**
	 * @see Router#getName()
	 */
	RouterBuilder name(String name);
	
	/**
	 * @see Router#getTenantId()
	 */
	RouterBuilder tenantId(String tenantId);
	
	/**
	 * @see Router#getTenantId()
	 */
	RouterBuilder tenant(Tenant tenant);
	
	/**
	 * @see Router#isAdminStateUp()()
	 */
	RouterBuilder adminStateUp(boolean isAdminStateUp);
	
	/**
	 * @see Router#getExternalGatewayInfo()
	 */
	RouterBuilder externalGateway(String networkId);
	
	/**
	 * @see Router#getExternalGatewayInfo()
	 */
	RouterBuilder externalGateway(String networkId, Boolean enableSNAT);
	
	/**
	 * @see Router#getExternalGatewayInfo()
	 */
	RouterBuilder externalGateway(ExternalGateway externalGateway);
	
	/**
	 * Removes the external gateway from the router during an update operation
	 */
	RouterBuilder clearExternalGateway();

	/**
	 * @see Router#getRoutes()
	 */
	RouterBuilder route(String destination, String nexthop);

	/**
	 * Removes the static routes from the router during an update operation
	 */
	RouterBuilder noRoutes();
	
	/**
	 * 
	 * @param distributed:true indicates a distributed router. It is available when dvr extension is enabled.
	 * @return
	 */
	RouterBuilder distributed(Boolean distributed);
}
