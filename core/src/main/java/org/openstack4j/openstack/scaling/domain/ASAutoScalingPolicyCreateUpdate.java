package org.openstack4j.openstack.scaling.domain;

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
	private String policyType;
	
	@JsonProperty("alarm_id")
	private String alarmId;
	
	@JsonProperty("scheduled_policy")
	private ScheduledPolicy scheduledPolicy;
	
	@JsonProperty("scaling_policy_action")
	private ScalingPolicyAction scalingPolicyAction;
	
	@JsonProperty("cool_down_time")
	private Integer coolDownTime;
	
	public enum PolicyType {
		ALARM,
		SCHEDULED,
		RECURRENCE,
		;
	}
}
