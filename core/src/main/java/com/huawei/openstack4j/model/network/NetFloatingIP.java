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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.builder.NetFloatingIPBuilder;

/**
 * The Interface NetFloatingIP.
 * 
 *
 * @author nanderson
 */
public interface NetFloatingIP extends ModelEntity, Buildable<NetFloatingIPBuilder> {

  /**
   * Gets the id.
   *
   * @return the id
   */
   String getId();

  /**
   * Gets the router id.
   *
   * @return the router id
   */
   String getRouterId();

  /**
   * Gets the tenant id.
   *
   * @return the tenant id
   */
   String getTenantId();

  /**
   * Gets the floating network id.
   *
   * @return the floating network id
   */
   String getFloatingNetworkId();

  /**
   * Gets the floating ip address.
   *
   * @return the floating ip address
   */
   String getFloatingIpAddress();

  /**
   * Gets the fixed ip address.
   *
   * @return the fixed ip address
   */
   String getFixedIpAddress();

  /**
   * Gets the port id.
   *
   * @return the port id
   */
   String getPortId();
   
   /**
    * Gets the floating ip status
    * 
    * @return the floating ip status
    */
   String getStatus();
   

}