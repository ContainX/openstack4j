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
import com.huawei.openstack4j.model.network.NetFloatingIP;

/**
 * Builder for a FloatingIP model class.
 *
 * @author Nathan Anderson
 */
public interface NetFloatingIPBuilder extends Builder<NetFloatingIPBuilder, NetFloatingIP> {
	
	/**
	 * sets Id of floating network
	 *
	 * @param networkId the network id
	 * @return the floating ip builder
	 */
	NetFloatingIPBuilder floatingNetworkId(String networkId);
  
  /**
   * Port id.
   *
   * @param portId the port id
   * @return the floating ip builder
   */
  NetFloatingIPBuilder portId(String portId);
	
}
