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
import com.huawei.openstack4j.model.network.builder.NetworkBuilder;

/**
 * An OpenStack (Neutron) network
 * 
 * @author Jeremy Unruh
 */
public interface Network extends Resource, Buildable<NetworkBuilder> {

	/**
	 * @return the status of the network
	 */
	State getStatus();

	/**
	 * @return list of subnet identifiers associated with the network
	 */
	List<String> getSubnets();

	/**
	 * @return the physical network provider or null
	 */
	String getProviderPhyNet();

	/**
	 * @return true if the administrative state is up
	 */
	boolean isAdminStateUp();

	/**
	 * @return the network type
	 */
	NetworkType getNetworkType();

	/**
	 * @return true if the router is external
	 */
	boolean isRouterExternal();

	/**
	 * @return true if the network is shared
	 */
	boolean isShared();

	/**
	 * @return the provider segmentation identifier
	 */
	String getProviderSegID();

	/**
	 * @return the list of Subnets
	 */
	List<? extends Subnet> getNeutronSubnets();

	/**
	 * @return The maximum transmission unit (MTU) value to address fragmentation. Minimum value is 68 for IPv4, and 1280 for IPv6.
	 */
	Integer getMTU();
	
	/**
	 * @return the list of the availability zone candidate for the network.
	 */
	List<String> getAvailabilityZoneHints();
	
	/**
	 * @return the list of the availability zone for the network.
	 */
	List<String> getAvailabilityZones();
	
	
}
