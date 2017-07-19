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
package org.openstack4j.openstack.antiddos.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;

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
public class AntiDDoSConfig implements ModelEntity {

	private static final long serialVersionUID = 5186395504017778934L;

	/**
	 * traffic limited list
	 */
	@JsonProperty("traffic_limited_list")
	private List<TrafficLimited> trafficLimiteds;

	/**
	 * http limited list
	 */
	@JsonProperty("http_limited_list")
	private List<HttpLimited> httpLimiteds;

	/**
	 * connection limited list
	 */
	@JsonProperty("connection_limited_list")
	private List<ConnectionLimited> connectionLimiteds;

	/**
	 * user configuration list
	 */
	@JsonProperty("extend_ddos_config")
	private List<ExtendDDoSConfig> extendDDoSConfigs;

	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public class TrafficLimited {

		/**
		 * traffic position id
		 */
		@JsonProperty("traffic_pos_id")
		private Integer trafficPosId;

		/**
		 * traffic per second (Mbit/s)
		 */
		@JsonProperty("traffic_per_second")
		private Integer trafficPerSecond;

		/**
		 * packet per second
		 */
		@JsonProperty("packet_per_second")
		private Integer packetPerSecond;
	}

	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	private class HttpLimited {

		/**
		 * http request position id
		 */
		@JsonProperty("http_request_pos_id")
		private Integer httpRequestPosId;

		/**
		 * http packet per second
		 */
		@JsonProperty("http_packet_per_second")
		private Integer httpPacketPersecond;
	}

	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	private class ConnectionLimited {
		/**
		 * access position id when cleaning
		 */
		@JsonProperty("cleaning_access_pos_id")
		private Integer cleaningAccessPosId;

		/**
		 * new connection limited
		 */
		@JsonProperty("new_connection_limited")
		private Integer newConnectionLimited;

		/**
		 * total connection limited
		 */
		@JsonProperty("total_connection_limited")
		private Integer totalConnectionLimited;
	}

	@Getter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	private class ExtendDDoSConfig {

		/**
		 * new connection limited
		 */
		@JsonProperty("new_connection_limited")
		private Integer newConnectionLimited;

		/**
		 * total connection limited
		 */
		@JsonProperty("total_connection_limited")
		private Integer totalConnectionLimited;

		/**
		 * http packet per second
		 */
		@JsonProperty("http_packet_per_second")
		private Integer httpPacketPerSecond;

		/**
		 * traffic per second
		 */
		@JsonProperty("traffic_per_second")
		private Integer trafficPerSecond;

		/**
		 * packet per second
		 */
		@JsonProperty("packet_per_second")
		private Integer packetPerSecond;

		/**
		 * set id
		 */
		@JsonProperty("setID")
		private Integer setId;
	}
}
