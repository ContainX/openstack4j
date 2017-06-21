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
package org.openstack4j.openstack.scaling.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.scaling.ScalingGroupInstance;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
@JsonRootName("scaling_group_instances")
public class ASAutoScalingGroupInstance implements ScalingGroupInstance {
	private static final long serialVersionUID = 3870330001102212850L;

	@JsonProperty("instance_id")
	private String instanceId;

	@JsonProperty("instance_name")
	private String instanceName;

	@JsonProperty("scaling_group_id")
	private String groupId;

	@JsonProperty("scaling_group_name")
	private String groupName;

	@JsonProperty("life_cycle_state")
	private String lifeCycleState;

	@JsonProperty("health_status")
	private String healthStatus;

	@JsonProperty("scaling_configuration_name")
	private String configName;

	@JsonProperty("scaling_configuration_id")
	private String configId;

	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	private Date createTime;

	public static class ASAutoScalingGroupInstances extends ListResult<ASAutoScalingGroupInstance> {
		private static final long serialVersionUID = -2720812005861616356L;

		@JsonProperty("scaling_group_instances")
		private List<ASAutoScalingGroupInstance> instances;

		@Override
		protected List<ASAutoScalingGroupInstance> value() {
			return instances;
		}
	}

}
