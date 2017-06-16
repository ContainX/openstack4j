package org.openstack4j.openstack.scaling.domain;

import org.openstack4j.model.scaling.InstanceConfig;
import org.openstack4j.model.scaling.ScalingConfigCreate;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ASAutoScalingConfigCreate implements ScalingConfigCreate {

	private static final long serialVersionUID = -8356492591040412347L;
	
	@JsonProperty("scaling_configuration_id")
	private String configId;
	
	@JsonProperty("scaling_configuration_name")
	private String configName;
	
	@JsonProperty("instance_config")
	private InstanceConfig instanceConfig;
}
