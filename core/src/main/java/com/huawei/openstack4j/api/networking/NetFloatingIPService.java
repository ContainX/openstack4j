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
package com.huawei.openstack4j.api.networking;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.NetFloatingIP;


/**
 * Provides Neutron-based FloatingIP services.
 *
 * @author Nathan Anderson
 */
public interface NetFloatingIPService extends RestService {

  /**
   * Returns list of floating IPs.
   *
   * @return List of NetFloatingIPs or empty
   */
  List<? extends NetFloatingIP> list();
  
  /**
   * Returns list of floating IPs filtered by parameters.
   * 
   * @param filteringParams map (name, value) of filtering parameters
   * @return 
   */
  List<? extends NetFloatingIP> list(Map<String, String> filteringParams);
  
  /**
   * Gets a NetFloatingIP by id.
   *
   * @param id the floating-ip identifier
   * @return the NetFloatingIP
   */
  NetFloatingIP get(String id);
  
  
  /**
   * Deletes NetFloatingIP by id.
   *
   * @param id the id
   * @return the action response
   */
  ActionResponse delete(String id);
  
  
  /**
   * Creates a new Floating IP
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP create(NetFloatingIP floatingIp);
  
  
  /**
   * Associates a Floating IP to a Port.
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP associateToPort(String id, String portId);
  
  
  /**
   * Deassociate's from port.
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP disassociateFromPort(String id);
  
}
