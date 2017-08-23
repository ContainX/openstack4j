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
package com.huawei.openstack4j.api.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines the URL perspective in which the API is accessing data from within an OpenStack deployment.  For example: admin, public, internal
 * @author Jeremy Unruh
 */
public enum Facing {

	INTERNAL,
	ADMIN,
	PUBLIC
	;
	@JsonValue
	public String value() {
		return name().toLowerCase();
	}
	
	@JsonCreator
	public static Facing value(String facing) {
		if (facing == null || facing.isEmpty()) return PUBLIC;
		try
		{
			return valueOf(facing.toUpperCase());
		}
		catch (IllegalArgumentException e) {
			return PUBLIC;
		}
	}
	
}
