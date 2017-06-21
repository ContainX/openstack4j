/*******************************************************************************
 * 	Copyright 2017 TLD
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
package org.openstack4j.model.dns.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of a designate v2 zone
 */
public enum ZoneType {

	PRIVATE, PUBLIC;

	@JsonValue
	public String value() {
		return name().toLowerCase();
	}

	//default to PUBLIC
	@JsonCreator
	public static ZoneType value(String v)
	{
		if (v == null) return PUBLIC;
		try {
			return valueOf(v.toUpperCase());
		} catch (IllegalArgumentException e) {
			return PUBLIC;
		}
	}

}
