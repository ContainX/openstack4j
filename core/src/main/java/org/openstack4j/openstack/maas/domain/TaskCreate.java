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
package org.openstack4j.openstack.maas.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.maas.constants.TriggerCondition;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class TaskCreate implements ModelEntity {

	private static final long serialVersionUID = 836240938288167164L;

	/**
	 * source node
	 */
	@JsonProperty("src_node")
	private Node srcNode;

	/**
	 * destination node
	 */
	@JsonProperty("dst_node")
	private Node dstNode;

	/**
	 * whether enable KMS encryption
	 */
	private Boolean enableKMS;

	/**
	 * thread number, up to 5
	 */
	@JsonProperty("thread_num")
	private Integer threadNum;

	/**
	 * task description
	 */
	private String description;

	/**
	 * smn
	 */
	private SmnInfo smnInfo;

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Node {

		/**
		 * bucket region
		 */
		private String region;

		/**
		 * access key
		 */
		private String ak;

		/**
		 * secret key
		 */
		private String sk;

		/**
		 * object key
		 */
		@JsonProperty("object_key")
		private Object objectKey;

		/**
		 * bucket name
		 */
		private String bucket;

	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SmnInfo {

		/**
		 * smn topic urn
		 */
		private String topicUrn;

		/**
		 * language
		 */
		private String language;

		/**
		 * trigger conditions
		 */
		private List<TriggerCondition> triggerConditions;
	}
}
