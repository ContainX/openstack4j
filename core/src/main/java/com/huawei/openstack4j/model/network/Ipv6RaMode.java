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
 * The IPv6 router advertisement specifies whether the networking service should transmit ICMPv6 packets, for a subnet
 * 
 * @author Taemin
 */
public enum Ipv6RaMode {
	SLAAC("slaac"),	
	DHCPV6_STATEFUL("dhcpv6-stateful"),	
	DHCPV6_STATELESS("dhcpv6-stateless");
	
	private final String ipv6RaMode;
	
	private Ipv6RaMode(String ipv6RaMode) {
		this.ipv6RaMode = ipv6RaMode;
	}
	
	@JsonValue
	public String getIpv6RaMode() {
		return ipv6RaMode;
	}

	@JsonCreator
	public static Ipv6RaMode forValue(String value) {
		if (value != null) {
			for (Ipv6RaMode v : Ipv6RaMode.values()) {
				if (v.ipv6RaMode.equalsIgnoreCase(value))
					return v;
			}		
		}
		return null;
	}
}
