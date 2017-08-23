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
import com.huawei.openstack4j.model.network.AllowedAddressPair;

import com.google.common.base.MoreObjects;

/**
 * A Fixed IP Address
 *
 * @author Jeremy Unruh
 */
public class NeutronAllowedAddressPair implements AllowedAddressPair {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ip_address")
	private String ipAddress;

	@JsonProperty("mac_address")
	private String macAddress;

	public NeutronAllowedAddressPair() { }

	public NeutronAllowedAddressPair(String ipAddress, String macAddress) {
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIpAddress() {
		return ipAddress;
	}

	@Override
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("ipAddress", ipAddress).add("macAddress", macAddress).toString();
	}

}
