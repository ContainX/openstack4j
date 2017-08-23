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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The IPv6 address modes specifies mechanisms for assigning IP addresses
 * 
 * @author Taemin
 */
public enum Ipv6AddressMode {
	SLAAC("slaac"),	
	DHCPV6_STATEFUL("dhcpv6-stateful"),	
	DHCPV6_STATELESS("dhcpv6-stateless"),
	NULL("null");
	
	private final String ipv6AddressMode;
	
	private Ipv6AddressMode(String ipv6AddressMode) {
		this.ipv6AddressMode = ipv6AddressMode;
	}
	
	@JsonValue
	public String getIpv6AddressMode() {
		return ipv6AddressMode;
	}
	
	@JsonCreator
	public static Ipv6AddressMode forValue(String value) {
		if (value != null){
			for (Ipv6AddressMode s : Ipv6AddressMode.values()) {
				if (s.ipv6AddressMode.equalsIgnoreCase(value))
					return s;
			}
		}
		return null;
	}	
}
