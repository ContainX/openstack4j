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

import java.util.Date;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.maas.constants.State;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
public class Task implements ModelEntity {

	private static final long serialVersionUID = -3434958298474972146L;

	/**
	 * task id
	 */
	private Long id;
	
	/**
	 * task name
	 */
	private String name;
	
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
	 * thread number
	 */
	@JsonProperty("thread_num")
	private Integer threadNum;
	
	/**
	 * status
	 */
	private State status;
	
	/**
	 * progress
	 */
	private Double progress;
	
	/**
	 * migrate speed
	 */
	@JsonProperty("migrate_speed")
	private Long migrateSpeed;
	
	/**
	 * whether enable KMS encryption
	 */
	private Boolean enableKMS;
	
	/**
	 * whether enable acceleration
	 */
	@JsonProperty("enable_tas")
	private Boolean enableTas;
	
	/**
	 * acceleration agent
	 */
	private Agent agent;
	
	/**
	 * description
	 */
	private String description;
	
	/**
	 * task error reason
	 */
	@JsonProperty("error_reason")
	private Object errorReason;
	
	/**
	 * total size
	 */
	@JsonProperty("total_size")
	private Long totalSize;
	
	/**
	 * complete size
	 */
	@JsonProperty("complete_size")
	private Long completeSize;
	
	/**
	 * start time
	 */
	@JsonProperty("start_time")
	@JsonFormat(shape = Shape.NUMBER)
	private Date startTime;
	
	/**
	 * left time
	 */
	@JsonProperty("left_time")
	private Long leftTime;
	
	/**
	 * total time
	 */
	@JsonProperty("total_time")
	private Long totalTime;
	
	/**
	 * success number
	 */
	@JsonProperty("success_num")
	private Long successNum;
	
	/**
	 * fail number
	 */
	@JsonProperty("fail_num")
	private Long failNum;
	
	/**
	 * total number
	 */
	@JsonProperty("total_num")
	private Long totalNum;
	
	/**
	 * smn info
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
	public static class Agent {
		
		/**
		 * agent name
		 */
		@JsonProperty("agent_name")
		private String agentName;
		
		/**
		 * agent ip
		 */
		private String ip;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SmnInfo {
		/**
		 * notify result
		 */
		private Boolean notifyResult;
		
		/**
		 * notify error message
		 */
		private String notifyErrorMessage;
		
		/**
		 * topic name
		 */
		private String topicName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
