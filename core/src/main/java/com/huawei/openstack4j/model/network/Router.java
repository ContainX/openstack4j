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
package com.huawei.openstack4j.model.network;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.network.builder.RouterBuilder;

/**
 * A router is used to interconnect subnets and forward traffic among them. Another feature of the router is to NAT internal traffic to external networks.
 * 
 * @author Jeremy Unruh
 */
public interface Router extends Resource, Buildable<RouterBuilder> {

	/**
	 * Host Routing entries for the router
	 *
	 * @return the routes for the router
	 */
	List<? extends HostRoute> getRoutes();
	
	/**
	 * Administrative state of the router
	 *
	 * @return true, if the administrative state is up
	 */
	boolean isAdminStateUp();
	
	/**
	 * Indicates whether a router is currently operational or not
	 *
	 * @return the state/status of the router
	 */
	State getStatus();
	
	/**
	 * Information on external gateway for the router
	 *
	 * @return the external gateway info
	 */
	ExternalGateway getExternalGatewayInfo();
	
	/**
	 * true indicates a distributed router. It is available when dvr extension is enabled.
	 * @return
	 */
	Boolean getDistributed();
}
