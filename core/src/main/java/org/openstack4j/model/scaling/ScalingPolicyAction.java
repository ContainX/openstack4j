package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

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
public class ScalingPolicyAction implements ModelEntity {
	private static final long serialVersionUID = -5715857665624203418L;

	@JsonProperty
	private String operation;
	
	@JsonProperty("instance_number")
	private Integer instanceNumber;
}
