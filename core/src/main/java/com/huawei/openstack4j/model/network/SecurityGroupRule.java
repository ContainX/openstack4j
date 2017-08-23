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
import com.huawei.openstack4j.model.network.builder.NetSecurityGroupRuleBuilder;

/**
 * The Interface SecurityGroupRule.
 * 
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupRule extends ModelEntity, Buildable<NetSecurityGroupRuleBuilder>{
  
  /**
   * Gets the direction.
   *
   * @return the direction
   */
  String getDirection();
  
  /**
   * Gets the ether type.
   *
   * @return the ether type
   */
  String getEtherType();
  
  /**
   * Gets the id.
   *
   * @return the id
   */
  String getId();
  
  /**
   * Gets the port range max.
   *
   * @return the port range max
   */
  Integer getPortRangeMax();
  
  /**
   * Gets the port range min.
   *
   * @return the port range min
   */
  Integer getPortRangeMin();
  
  /**
   * Gets the protocol.
   *
   * @return the protocol
   */
  String getProtocol();
  
  /**
   * Gets the remote group id.
   *
   * @return the remote group id
   */
  String getRemoteGroupId();
  
  /**
   * Gets the remote ip prefix.
   *
   * @return the remote ip prefix
   */
  String getRemoteIpPrefix();
  
  /**
   * Gets the security group id.
   *
   * @return the security group id
   */
  String getSecurityGroupId();
  
  /**
   * Gets the tenant id.
   *
   * @return the tenant id
   */
  String getTenantId();
  
  
}
