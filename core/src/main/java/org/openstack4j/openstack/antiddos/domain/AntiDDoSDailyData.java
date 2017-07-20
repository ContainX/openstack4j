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

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

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
public class AntiDDoSDailyData implements ModelEntity {

	private static final long serialVersionUID = 7206838943743699879L;
	
	/**
	 * Start time
	 */
	@JsonProperty("period_start")
	@JsonFormat(shape = Shape.NUMBER)
	private Date periodStart;
	
	/**
	 * Inbound traffic per second(bit/s)
	 */
	@JsonProperty("bps_in")
	private Integer bpsIn;
	
	/**
	 * Attack traffic per second(bit/s)
	 */
	@JsonProperty("bps_attack")
	private Integer bpsAttack;
	
	/**
	 * Total traffic per second(bit/s)
	 */
	@JsonProperty("total_bps")
	private Integer totalBps;
	
	/**
	 * Inbound data packet rate per second
	 */
	@JsonProperty("pps_in")
	private Integer ppsIn;
	
	/**
	 * Attack data packet rate per second
	 */
	@JsonProperty("pps_attack")
	private Integer ppsAttack;
	
	/**
	 * Total data packet rate per second
	 */
	@JsonProperty("total_pps")
	private Integer totalPps;
	
	public class AntiDDoSDailyDatas extends ListResult<AntiDDoSDailyData> {

		private static final long serialVersionUID = 3674265240585920922L;

		private List<AntiDDoSDailyData> data;
		
		@Override
		protected List<AntiDDoSDailyData> value() {
			return data;
		}
		
	}
	
}
