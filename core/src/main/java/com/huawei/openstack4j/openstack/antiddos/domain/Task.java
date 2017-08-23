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
package com.huawei.openstack4j.openstack.antiddos.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.antiddos.constants.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Anti-DDoS task
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Task implements ModelEntity {

	private static final long serialVersionUID = -1132106266003937714L;
	
	/**
	 * error code
	 */
	@JsonProperty("error_code")
	private String errorCode;
	
	/**
	 * error description
	 */
	@JsonProperty("error_description")
	private String errorDesc;
	
	/**
	 * task id
	 */
	@JsonProperty("task_id")
	private String taskId;
	
	/**
	 * task status
	 */
	@JsonProperty("task_status")
	private TaskStatus status;
	
	/**
	 * task message
	 */
	@JsonProperty("task_msg")
	private String msg;
}
