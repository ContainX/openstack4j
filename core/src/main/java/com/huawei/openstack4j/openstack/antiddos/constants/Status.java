/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package com.huawei.openstack4j.openstack.antiddos.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Strings;

/**
 * Anti-DDoS status
 */
public enum Status {
	NORMAL("normal"),
	CONFIGGING("configging"),
	NOTCONFIG("notConfig"),
	PACKETCLEANING("packetcleaning"),
	PACKETDROPPING("packetdropping"),
	;
	
	private String val;
	
	private Status(String val) {
		this.val = val;
	}
	
	public String getVal() {
		return this.val;
	}
	
	@JsonCreator
	public static Status forValue(String val) {
		if(!Strings.isNullOrEmpty(val)) {
			for (Status status : Status.values()) {
				if(val.equalsIgnoreCase(status.getVal())) {
					return status;
				}
			}
		}
		
		return null;
	}
}
