package org.openstack4j.openstack.scaling.domain;

import java.util.List;

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
public class ASAutoScalingConfigDelete implements ModelEntity {
	private static final long serialVersionUID = -4904624408132674793L;
	
	@JsonProperty("scaling_configuration_id")
	private List<String> configIds;
}
