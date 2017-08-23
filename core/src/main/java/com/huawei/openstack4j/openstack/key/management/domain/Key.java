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
package com.huawei.openstack4j.openstack.key.management.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.serializer.BooleanDeserializer;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.key.management.constants.KeyState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Key implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	@JsonProperty("key_id")
	String id;

	/**
	 * key type
	 */
	@JsonProperty("key_type")
	String type;

	/**
	 * User domain ID
	 */
	@JsonProperty("domain_id")
	String domainId;

	/**
	 * Alias of the key
	 */
	@JsonProperty("key_alias")
	String alias;

	/**
	 * Region where a key resides
	 */
	@JsonProperty("realm")
	String realm;

	/**
	 * Description of the key
	 */
	@JsonProperty("key_description")
	String description;

	/**
	 * Creation time (timestamp) of the key
	 */
	@JsonProperty("creation_date")
	Date creationDate;

	/**
	 * Scheduled deletion time (timestamp) of the key
	 */
	@JsonProperty("scheduled_deletion_date")
	Date scheduledDeletionDate;

	/**
	 * key state
	 */
	@JsonProperty("key_state")
	KeyState state;

	/**
	 * identification that whether the key is a default CMK create by KMS-system
	 */
	@JsonProperty("default_key_flag")
	@JsonDeserialize(using = BooleanDeserializer.class)
	Boolean isDefaultKey;

	public static class Keys extends ListResult<String> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("keys")
		private List<String> keys;

		@JsonProperty("next_marker")
		String nextMarker;

		@JsonProperty("truncated")
		Boolean truncated;

		public List<String> value() {
			return keys;
		}

		public List<String> get() {
			return keys;
		}

		public String getNextMarker() {
			return nextMarker;
		}

		public Boolean getTruncated() {
			return truncated;
		}

	}

}
