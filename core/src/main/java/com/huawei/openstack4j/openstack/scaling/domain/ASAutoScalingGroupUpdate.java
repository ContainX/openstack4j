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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.model.scaling.ScalingGroupUpdate;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

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
public class ASAutoScalingGroupUpdate implements ScalingGroupUpdate {
	private static final long serialVersionUID = 7168645684244117704L;

	@JsonProperty("scaling_group_id")
	String groupId;
	
	@JsonProperty("scaling_group_name")
	String groupName;

	@JsonProperty("scaling_configuration_id")
	String configId;

	@JsonProperty("desire_instance_number")
	Integer desireInstanceNumber;

	@JsonProperty("min_instance_number")
	Integer minInstanceNumber;

	@JsonProperty("max_instance_number")
	Integer maxInstanceNumber;

	@JsonProperty("cool_down_time")
	Integer coolDownTime;

	@JsonProperty("lb_listener_id")
	String lbListenerId;

	@JsonProperty("available_zones")
	List<String> availabilityZones;

	@JsonProperty("networks")
	List<IdResourceEntity> networks;

	@JsonProperty("security_groups")
	List<IdResourceEntity> securityGroups;

	@JsonProperty("health_periodic_audit_method")
	HealthPeriodicAuditMethod healthPeriodicAuditMethod;

	@JsonProperty("health_periodic_audit_time")
	Integer healthPeriodicAuditTime;

	@JsonProperty("instance_terminate_policy")
	InstanceTerminatePolicy instanceTerminatePolicy;

	@JsonProperty("notifications")
	List<String> notifications;

	@JsonProperty("delete_publicip")
	Boolean deletePublicip;

	public static ASAutoScalingGroupUpdate fromScalingGroup(ScalingGroup group) {
		return ASAutoScalingGroupUpdate.builder()
			.groupName(group.getGroupName())
			.desireInstanceNumber(group.getDesireInstanceNumber())
			.minInstanceNumber(group.getMinInstanceNumber())
			.maxInstanceNumber(group.getMaxInstanceNumber())
			.coolDownTime(group.getCoolDownTime())
			.availabilityZones(group.getAvailabilityZones())
			.networks(group.getNetworks())
			.securityGroups(group.getSecurityGroups())
			.lbListenerId(group.getLbListenerId())
			.healthPeriodicAuditMethod(group.getHealthPeriodicAuditMethod())
			.healthPeriodicAuditTime(group.getHealthPeriodicAuditTime())
			.instanceTerminatePolicy(group.getInstanceTerminatePolicy())
			.configId(group.getConfigId())
			.notifications(group.getNotifications())
			.deletePublicip(group.getDeletePublicip()).build();
	}
}
