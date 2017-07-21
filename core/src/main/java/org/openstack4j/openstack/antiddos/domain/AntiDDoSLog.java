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
import org.openstack4j.openstack.antiddos.constants.LogStatus;
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
public class AntiDDoSLog implements ModelEntity {

	private static final long serialVersionUID = -9015043778002937322L;
	
	/**
	 * Start time
	 */
	@JsonProperty("start_time")
	@JsonFormat(shape = Shape.NUMBER)
	private Date startTime;
	
	/**
	 * End time
	 */
	@JsonProperty("end_time")
	@JsonFormat(shape = Shape.NUMBER)
	private Date endTime;
	
	/**
	 * Status
	 */
	private LogStatus status;
	
	/**
	 * Log trigger traffic per second(bit/s)
	 */
	@JsonProperty("trigger_bps")
	private Integer triggerBps;
	
	/**
	 * Log trigger data packet rate per second
	 */
	@JsonProperty("trigger_pps")
	private Integer triggerPps;
	
	/**
	 * Log trigger http request rate per second
	 */
	@JsonProperty("trigger_http_pps")
	private Integer triggerHttpPps;
	
	public static class AntiDDoSLogs extends ListResult<AntiDDoSLog> {

		private static final long serialVersionUID = 8436517976118230703L;

		private List<AntiDDoSLog> logs;
		
		@Override
		protected List<AntiDDoSLog> value() {
			return logs;
		}
		
	}
}
