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

import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyAction;
import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import org.openstack4j.model.scaling.ScheduledPolicy;

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
public class ASAutoScalingPolicyCreateUpdate implements ScalingPolicyCreateUpdate {
	private static final long serialVersionUID = -3734560645098808295L;

	@JsonProperty("scaling_policy_id")
	private String policyId;
	
	@JsonProperty("scaling_policy_name")
	private String policyName;
	
	@JsonProperty("scaling_group_id")
	private String groupId;
	
	@JsonProperty("scaling_policy_type")
	private ScalingPolicyType policyType;
	
	@JsonProperty("alarm_id")
	private String alarmId;
	
	@JsonProperty("scheduled_policy")
	private ScheduledPolicy scheduledPolicy;
	
	@JsonProperty("scaling_policy_action")
	private ScalingPolicyAction scalingPolicyAction;
	
	@JsonProperty("cool_down_time")
	private Integer coolDownTime;
	
	public static ASAutoScalingPolicyCreateUpdate fromScalingPolicy(ScalingPolicy policy) {
		return ASAutoScalingPolicyCreateUpdate.builder()
				.policyId(policy.getPolicyId())
				.policyName(policy.getPolicyName())
				.policyType(policy.getPolicyType())
				.alarmId(policy.getAlarmId())
				.scheduledPolicy(policy.getScheduledPolicy())
				.scalingPolicyAction(policy.getScalingPolicyAction())
				.coolDownTime(policy.getCoolDownTime())
				.build();
	}
}
