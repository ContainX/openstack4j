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
import com.huawei.openstack4j.model.network.builder.SubnetBuilder;

/**
 * A Subnet is a network with Pools and network based settings
 * 
 * @author Jeremy Unruh
 */
public interface Subnet extends Resource, Buildable<SubnetBuilder> {

	/**
	 * @return true if DHCP is enabled for this subnet, false if not.
	 */
	boolean isDHCPEnabled();

	/**
	 * @return the id of the network this subnet is associated with
	 */
	String getNetworkId();

	/**
	 * @return the DNS server names/IP
	 */
	List<String> getDnsNames();

	/**
	 * @return the sub-ranges of cidr available for dynamic allocation to ports
	 */
	List<? extends Pool> getAllocationPools();


	/**
	 * @return the set of routes that should be used by devices with IPs from this subnet
	 */
	List<? extends HostRoute> getHostRoutes();

	/**
	 * @return the ip version used by this subnet
	 */
	IPVersionType getIpVersion();


	/**
	 * @return the default gateway used by devices in this subnet
	 */
	String getGateway();

	/**
	 * @return the cidr representing the IP range for this subnet, based on IP version
	 */
	String getCidr();
	
	/**
	 * @return The IPv6 address modes specifies mechanisms for assigning IP addresses
	 */
	Ipv6AddressMode getIpv6AddressMode();
	
	/**
	 * @return the IPv6 router advertisement specifies whether the networking service should transmit ICMPv6 packets, for a subnet
	 */
	Ipv6RaMode getIpv6RaMode();		
}
