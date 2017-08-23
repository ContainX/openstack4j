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

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.huawei.openstack4j.model.ModelEntity;
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
public class AntiDDoSWeeklyData implements ModelEntity {

	private static final long serialVersionUID = 8160501990224385092L;

	/**
	 * DDoS intercept times weekly
	 */
	@JsonProperty("ddos_intercept_times")
	private Integer ddosInterceptTimes;

	/**
	 * Weekly data
	 */
	@JsonProperty("weekdata")
	private List<WeekData> weekData;

	/**
	 * Top 10 ip address
	 */
	@JsonProperty("top10")
	private List<IpData> top10;

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WeekData {
		/**
		 * DDoS intercept times
		 */
		@JsonProperty("ddos_intercept_times")
		private Integer ddosInterceptTimes;

		/**
		 * DDoS blackhole times
		 */
		@JsonProperty("ddos_blackhole_times")
		private Integer ddosBlackholeTimes;

		/**
		 * max attack traffic per second(bit/s)
		 */
		@JsonProperty("max_attack_bps")
		private Integer maxAttackBps;

		/**
		 * max attack connection number
		 */
		@JsonProperty("max_attack_conns")
		private Integer maxAttackConns;

		/**
		 * Start time
		 */
		@JsonProperty("period_start_date")
		@JsonFormat(shape = Shape.NUMBER)
		private Date periodStartDate;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IpData {

		/**
		 * floating ip address
		 */
		@JsonProperty("floating_ip_address")
		private String floatingIpAddress;

		/**
		 * DDoS intercept times
		 */
		private Integer times;
	}
}
