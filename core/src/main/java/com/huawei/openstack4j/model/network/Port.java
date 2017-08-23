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
import java.util.Map;
import java.util.Set;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.network.builder.PortBuilder;

/**
 * A Network (Neutron) Port
 * 
 * @author Jeremy Unruh
 */
public interface Port extends Resource, Buildable<PortBuilder> {

	 /**
   * @return the current state of the port
   */
  State getState();

  /**
   * @return the administrative state of port. If false, port does not forward packets.
   */
  boolean isAdminStateUp();

  /**
   * @return the id of the network where this port is associated with
   */
  String getNetworkId();

  /**
   * @return the id of the device (e.g. server) using this port.
   */
  String getDeviceId();

  /**
   * @return the entity (e.g.: DHCP Agent) using this port.
   */
  String getDeviceOwner();

  /**
   * @return the set of fixed IPs this port has been assigned
   */
  Set<? extends IP> getFixedIps();
  
  Set<? extends AllowedAddressPair> getAllowedAddressPairs();

  /**
   * @return the MacAddress of this port
   */
  String getMacAddress();
  
  /**
   * @return the security group identifiers assigned to this port
   */
  List<String> getSecurityGroups();

  /**
   * @return The port security status. A valid value is enabled (true) or disabled (false).
   */
  Boolean isPortSecurityEnabled();
  
  String getHostId();

  String getVifType();

  Map<String, Object> getVifDetails();

  String getvNicType();

  Map<String, Object> getProfile();
}
