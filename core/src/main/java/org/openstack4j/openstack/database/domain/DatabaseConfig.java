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
package org.openstack4j.openstack.database.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of Database Configuration
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("configuration")
public class DatabaseConfig implements ModelEntity {

	static final long serialVersionUID = -3324036820846287512L;

	@JsonProperty("id")
	String id;

	@JsonProperty("name")
	String name;
	
	@JsonProperty("description")
	String description;

	@JsonProperty("datastore_version_id")
	String datastoreVersionId;

	@JsonProperty("datastore_version_name")
	String datastoreVersionName;

	@JsonProperty("datastore_name")
	String datastoreName;

	@JsonProperty("instance_count")
	Integer instanceCount;
	
	@JsonProperty("allowed_updated")
	Boolean allowedUpdated;

	@JsonProperty("created")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date created;

	@JsonProperty("updated")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date updated;

	@JsonProperty("values")
	Map<String, String> values;

	@JsonProperty("parameters")
	List<DatabaseConfigParam> parameters;
	


	public static class Configs extends ListResult<DatabaseConfig> {
		private static final long serialVersionUID = 7666104777418585874L;

		@JsonProperty("configurations")
		List<DatabaseConfig> configs;

		@Override
		protected List<DatabaseConfig> value() {
			return configs;
		}

	}
}
