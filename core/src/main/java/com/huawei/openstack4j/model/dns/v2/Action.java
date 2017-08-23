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
package com.huawei.openstack4j.model.dns.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of a designate v2 recordset action
 */
public enum Action {

	NONE, CREATE, DELETE, UPDATE;

	@JsonValue
	public String value() {
		return name().toUpperCase();
	}

	//default to PRIMARY
	@JsonCreator
	public static Action value(String v)
	{
		if (v == null) return NONE;
		try {
			return valueOf(v.toUpperCase());
		} catch (IllegalArgumentException e) {
			return NONE;
		}
	}

}
