package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.InstanceConfig;
import org.openstack4j.model.scaling.ScalingConfig;
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
@JsonRootName("scaling_configuration")
public class ASAutoScalingConfig implements ScalingConfig {

	private static final long serialVersionUID = -8356492591040412347L;
	
	@JsonProperty("scaling_configuration_id")
	private String configId;
	
	@JsonProperty("scaling_configuration_name")
	private String configName;
	
	@JsonProperty("instance_config")
	private InstanceConfig instanceConfig;
	
	@JsonProperty
	private String tenant;
	
	@JsonProperty("create_time")
	private String createTime;

	public static class ASAutoScalingConfigs extends ListResult<ASAutoScalingConfig> {

		private static final long serialVersionUID = -1873594481944340934L;
		
		@JsonProperty("scaling_configurations")
		private List<ASAutoScalingConfig> configs;

		@Override
		protected List<ASAutoScalingConfig> value() {
			return configs;
		}
	}
}
