package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingGroupInstance;
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
	private String createTime;

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
