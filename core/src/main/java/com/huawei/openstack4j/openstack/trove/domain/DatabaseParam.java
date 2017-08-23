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
package com.huawei.openstack4j.openstack.trove.domain;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.trove.constant.ParameterValueType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h3>Model represent attributes of Database parameter</h3>
 * 
 * Database parameter is the configurable options of the database
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseParam implements ModelEntity {

	static final long serialVersionUID = -3324036820846287512L;

	/**
	 * parameter name
	 */
	@JsonProperty("name")
	String name;

	/**
	 * parameter type. (string, boolean, integer, float, etc)
	 */
	@JsonProperty("type")
	ParameterValueType type;

	/**
	 * parameter max value
	 */
	@JsonProperty("max")
	BigInteger max;

	/**
	 * parameter min value
	 */
	@JsonProperty("min")
	BigInteger min;

	/**
	 * datastore version id of this parameter
	 */
	@JsonProperty("datastore_version_id")
	String datastoreVersionId;

	/**
	 * is restart required when parameter value modified
	 */
	@JsonProperty("restart_required")
	Boolean restartRequired;
	
	
	public static class Parameters extends ListResult<DatabaseParam> {
		private static final long serialVersionUID = 7666104777418585874L;
		
		@JsonProperty("configuration-parameters")
		List<DatabaseParam> params;

		@Override
		protected List<DatabaseParam> value() {
			return params;
		}

	}

}
