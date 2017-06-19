package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingPolicy;
import org.openstack4j.model.scaling.ScalingPolicyAction;
import org.openstack4j.model.scaling.ScheduledPolicy;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
@JsonRootName("scaling_policy")
public class ASAutoScalingPolicy implements ScalingPolicy {
	private static final long serialVersionUID = 4619092602746601471L;

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

	@JsonProperty("policy_status")
	private String policyStatus;

	@JsonProperty("create_time")
	private String createTime;
	
	public static class ASAutoScalingPolicys extends ListResult<ASAutoScalingPolicy> {
		private static final long serialVersionUID = -1002284271614932588L;
		
		@JsonProperty("scaling_policies")
		private List<ASAutoScalingPolicy> policies;

		@Override
		protected List<ASAutoScalingPolicy> value() {
			return policies;
		}
	}
}
