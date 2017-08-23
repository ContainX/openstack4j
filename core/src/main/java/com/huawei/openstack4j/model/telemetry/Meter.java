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
package com.huawei.openstack4j.model.telemetry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * A Meter is a category of Measurement
 * 
 * @author Jeremy Unruh
 */
public interface Meter extends ModelEntity {

	/**
	 * The Meter Type
	 */
	public enum Type {
		GAUGE, DELTA, CUMULATIVE, UNRECOGNIZED;

		@JsonValue
		public String value() {
			return this.name().toLowerCase();
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Type fromValue(String type) {
			try {
				return valueOf(type.toUpperCase());
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}

	/**
	 * @return the unique identifier for the meter
	 */
	String getId();

	/**
	 * @return the unique name of the meter
	 */
	String getName();

	/**
	 * @return the ID of the Resource for which the measurements are taken
	 */
	String getResourceId();

	/**
	 * @return the ID of the project/tenant that owns the resource
	 */
	String getProjectId();

	/**
	 * @return the meter type
	 */
	Type getType();
	
	/**
	 * @return the unit of measure
	 */
	String getUnit();
	
	/**
   * @return The user id who last modified the resource
   */
	String getUserId();

}
