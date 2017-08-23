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
package com.huawei.openstack4j.model.compute;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.compute.builder.FloatingIPBuilder;

/**
 * The Interface FloatingIP.
 * 
 *
 * @author nanderson
 */
public interface FloatingIP extends ModelEntity, Buildable<FloatingIPBuilder> {

  /**
   * Gets the id.
   *
   * @return the id
   */
  String getId();

  /**
   * Gets the instance id.
   *
   * @return the instance id
   */
  String getInstanceId();

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
   * Gets the pool.
   *
   * @return the pool name
   */
  String getPool();
}