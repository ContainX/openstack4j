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

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.maas.constants.Operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskStart implements ModelEntity {

	private static final long serialVersionUID = -3162059646934039449L;

	/**
	 * operation: start
	 */
	private Operation operaion = Operation.START;
	
	/**
	 * source node access key
	 */
	@JsonProperty("source_ak")
	private String sourceAk;
	
	/**
	 * source node secret key
	 */
	@JsonProperty("source_sk")
	private String sourceSk;
	
	/**
	 * target node access key
	 */
	@JsonProperty("target_ak")
	private String targetAk;
	
	/**
	 * target node secret key
	 */
	@JsonProperty("target_sk")
	private String targetSk;
	
	@Builder(toBuilder = true)
	public TaskStart(String sourceAk, String sourceSk, String targetAk, String targetSk) {
		this.sourceAk = sourceAk;
		this.sourceSk = sourceSk;
		this.targetAk = targetAk;
		this.targetSk = targetSk;
	}
}
