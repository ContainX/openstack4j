package com.huawei.openstack4j.model.network;

import com.huawei.openstack4j.model.ModelEntity;

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
public interface FloatingIP extends ModelEntity {

  /**
   * Gets the id.
   *
   * @return the id
   */
  public abstract String getId();

  /**
   * Gets the router id.
   *
   * @return the router id
   */
  public abstract String getRouterId();

  /**
   * Gets the tenant id.
   *
   * @return the tenant id
   */
  public abstract String getTenantId();

  /**
   * Gets the floating network id.
   *
   * @return the floating network id
   */
  public abstract String getFloatingNetworkId();

  /**
   * Gets the floating ip address.
   *
   * @return the floating ip address
   */
  public abstract String getFloatingIpAddress();

  /**
   * Gets the fixed ip address.
   *
   * @return the fixed ip address
   */
  public abstract String getFixedIpAddress();

  /**
   * Gets the port id.
   *
   * @return the port id
   */
  public abstract String getPortId();

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public abstract void setId(String id);

  /**
   * Sets the router id.
   *
   * @param routerId the new router id
   */
  public abstract void setRouterId(String routerId);

  /**
   * Sets the tenant id.
   *
   * @param tenantId the new tenant id
   */
  public abstract void setTenantId(String tenantId);

  /**
   * Sets the floating network id.
   *
   * @param floatingNetworkId the new floating network id
   */
  public abstract void setFloatingNetworkId(String floatingNetworkId);

  /**
   * Sets the floating ip address.
   *
   * @param floatingIpAddress the new floating ip address
   */
  public abstract void setFloatingIpAddress(String floatingIpAddress);

  /**
   * Sets the fixed ip address.
   *
   * @param fixedIpAddress the new fixed ip address
   */
  public abstract void setFixedIpAddress(String fixedIpAddress);

  /**
   * Sets the port id.
   *
   * @param portId the new port id
   */
  public abstract void setPortId(String portId);

}