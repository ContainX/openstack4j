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
package com.huawei.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.network.IP;

import com.google.common.base.MoreObjects;

/**
 * A Fixed IP Address
 *
 * @author Jeremy Unruh
 */
public class NeutronIP implements IP {

	private static final long serialVersionUID = 1L;

  @JsonProperty("ip_address")
  private String ipAddress;

  @JsonProperty("subnet_id")
  private String subnetId;

  public NeutronIP() { }

  public NeutronIP(String address, String subnetId) {
  	this.ipAddress = address;
  	this.subnetId = subnetId;
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSubnetId() {
		return subnetId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("ipAddress", ipAddress).add("subnetId", subnetId).toString();
	}

}
