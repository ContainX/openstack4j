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

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v3.Tenant;
import com.huawei.openstack4j.model.network.ExtraDhcpOptCreate;
import com.huawei.openstack4j.model.network.Port;
import com.huawei.openstack4j.model.network.State;

/**
 * A Builder which creates a Neutron Port
 * 
 * @author Jeremy Unruh
 */
public interface PortBuilder extends Builder<PortBuilder, Port> {

	/**
	 * @see Port#getName()
	 */
	PortBuilder name(String name);
	
	/**
	 * @see Port#getTenantId()
	 */
	PortBuilder tenantId(String tenantId);
	
	/**
	 * @see Port#getTenantId()
	 */
	PortBuilder tenant(Tenant tenant);
	
	/**
	 * @see Port#getNetworkId()
	 */
	PortBuilder networkId(String networkId);
	
	/**
	 * @see Port#getDeviceId()
	 */
	PortBuilder deviceId(String deviceId);
	
	/**
	 * @see Port#getDeviceOwner()
	 */
	PortBuilder deviceOwner(String deviceOwner);
	
	/**
	 * @see Port#getMacAddress()
	 */
	PortBuilder macAddress(String macAddress);
	
	/**
	 * Adds a fixed IP to the current list of fixed IP Addresses
	 * @param address the IP Address
	 * @param subnetId the subnet identifier
	 * @return PortBuilder
	 * @see Port#getFixedIps()
	 */
	PortBuilder fixedIp(String address, String subnetId);
	
	/**
   * Removes a fixed IP from the current list of fixed IP Addresses
   * @param address the IP Address
   * @param subnetId the subnet identifier
   * @return PortBuilder
   * 
   */
	PortBuilder removeFixedIp(String address, String subnetId);
	
	
	/**
	 * Adds an allowed address pair to the current list of allowed addresses
	 * @param ipAddress the Subnet Address (i.e. 192.168.1.0/24)
	 * @param macAddress the MAC Address
	 * @return PortBuilder
	 * @see Port#getAllowedAddressPairs()
	 */
	PortBuilder allowedAddressPair(String ipAddress, String macAddress);
	
	

	/**
	 * Removes an allowed address pair from the current list of allowed addresses
	 * @param ipAddress the Subnet Address (i.e. 192.168.1.0/24)
	 * @param macAddress the MAC address
	 * @return PortBuilder
	 * @see Port#getAllowedAddressPairs()
	 */
	PortBuilder removeAddressPair(String ipAddress, String macAddress);
	
	
	/**
	 * @see Port#isAdminStateUp()
	 */
	PortBuilder adminState(boolean adminStateUp);
	
	/**
	 * @see Port#getState()
	 */
	PortBuilder state(State state);
	
    PortBuilder extraDhcpOpt(ExtraDhcpOptCreate extraDhcpOptCreate);
	
	PortBuilder securityGroup(String groupName);
	
	PortBuilder portSecurityEnabled(Boolean portSecurityEnabled); 
	
    PortBuilder hostId(String hostId);

	PortBuilder vifType(String vifType);

	PortBuilder vifDetails(Map<String, Object> vifDetails);

	PortBuilder vNicType(String vNicType);

	PortBuilder profile(Map<String, Object> profile);

	
}
