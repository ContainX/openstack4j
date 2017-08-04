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
package org.openstack4j.openstack.trove.domain;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.serializer.BooleanDeserializer;
import org.openstack4j.openstack.trove.constant.ParameterValueType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h3>Model represent attributes of Database Configuration parameter</h3>
 * 
 * Database Configuration parameter is the database parameter 
 * that has been set up for Database Configuration
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseConfigParam implements ModelEntity {

	static final long serialVersionUID = -3324036820846287512L;

	/**
	 * parameter name
	 */
	@JsonProperty("name")
	String name;

	/**
	 * parameter value
	 */
	@JsonProperty("value")
	String value;

	/**
	 * parameter type. (string, boolean, integer, float, etc)
	 */
	@JsonProperty("datatype")
	ParameterValueType type;

	/**
	 * parameter description
	 */
	@JsonProperty("description")
	String description;

	/**
	 * Valid parameter value range
	 */
	@JsonProperty("valueRange")
	String valueRange;

	/**
	 * is parameter readonly
	 */
	@JsonProperty("readonly")
	@JsonDeserialize(using = BooleanDeserializer.class)
	Boolean readonly;

	/**
	 * is restart required when parameter value modified
	 */
	@JsonProperty("needRestart")
	@JsonDeserialize(using = BooleanDeserializer.class)
	Boolean needRestart;

}
