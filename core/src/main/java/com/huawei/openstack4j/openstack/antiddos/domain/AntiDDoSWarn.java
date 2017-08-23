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
import com.huawei.openstack4j.openstack.antiddos.constants.SendFrequency;

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
public class AntiDDoSWarn implements ModelEntity {

	private static final long serialVersionUID = 5554288057606873003L;

	/**
	 * Warn configuration
	 */
	@JsonProperty("warn_config")
	private Config warnConfig;

	/**
	 * Warn topic urn
	 */
	@JsonProperty("topic_urn")
	private String topicUrn;

	/**
	 * Warn description
	 */
	@JsonProperty("display_name")
	private String displayName;

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Config {
		/**
		 * Whether DDoS attack
		 */
		private Boolean antiDDoS;

		/**
		 * Whether brute force
		 */
		@JsonProperty("bruce_force")
		private Boolean bruteForce;

		/**
		 * Whether remote login
		 */
		@JsonProperty("remote_login")
		private Boolean remoteLogin;

		/**
		 * Whether weak password
		 */
		@JsonProperty("weak_password")
		private Boolean weakPassword;

		/**
		 * Whether high privilege
		 */
		@JsonProperty("high privilege")
		private Boolean highPrivilege;

		/**
		 * Whether back doors
		 */
		@JsonProperty("back_doors")
		private Boolean backDoors;

		/**
		 * Whether waf
		 */
		private Boolean waf;

		/**
		 * Send frequency
		 */
		@JsonProperty("send_frequency")
		private SendFrequency sendFrequency;
	}
}
