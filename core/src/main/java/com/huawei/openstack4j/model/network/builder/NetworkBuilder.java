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
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.NetworkType;

/**
 * A Builder which creates a Network
 * 
 * @author Jeremy Unruh
 */
public interface NetworkBuilder extends Builder<NetworkBuilder, Network> {

	/**
	 * @see Network#getName()
	 */
	NetworkBuilder name(String name);
	
	/**
	 * @see Network#isAdminStateUp()
	 */
	NetworkBuilder adminStateUp(boolean adminStateUp);
	
	/**
	 * @see Network#getNetworkType()
	 */	
	NetworkBuilder networkType(NetworkType networkType);
	
	/**
	 * @see Network#getProviderPhyNet()
	 */
	NetworkBuilder physicalNetwork(String providerPhysicalNetwork);
	
	/**
	 * @see Network#getProviderSegID()
	 */
	NetworkBuilder segmentId(String providerSegmentationId);
	
	/**
	 * @see Network#getTenantId()
	 */
	NetworkBuilder tenantId(String tenantId);

	/**
	 * @see Network#isShared()
	 */
	NetworkBuilder isShared(boolean shared);
	
	/**
	 * @see Network#isRouterExternal()
	 */
	NetworkBuilder isRouterExternal(boolean routerExternal);
	
	/**
	 * @see Network#getAvailabilityZoneHints()
	 */
	NetworkBuilder addAvailabilityZoneHints(String availabilityZone);
}
