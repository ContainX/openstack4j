/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.openstack.scaling.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.scaling.ScalingActivityLog;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingActivityLog implements ScalingActivityLog {
	private static final long serialVersionUID = 7380843131412884092L;

	@JsonProperty
	private Status status;

	@JsonProperty("start_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	private Date startTime;

	@JsonProperty("end_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	private Date endTime;

	@JsonProperty
	private String id;

	@JsonProperty("instance_removed_list")
	private String instanceRemovedList;

	@JsonProperty("instance_deleted_list")
	private String instanceDeletedList;

	@JsonProperty("instance_added_list")
	private String instanceAddedList;

	@JsonProperty("scaling_value")
	private String scalingValue;

	@JsonProperty
	private String description;

	@JsonProperty("instance_value")
	private Integer instanceValue;

	@JsonProperty("desire_value")
	private Integer desireValue;
	
	public static class ASAutoScalingActivityLogs extends ListResult<ASAutoScalingActivityLog> {
		private static final long serialVersionUID = -3295497788104277308L;

		@JsonProperty("scaling_activity_log")
		private List<ASAutoScalingActivityLog> logs;

		@Override
		protected List<ASAutoScalingActivityLog> value() {
			return logs;
		}
	}
}
