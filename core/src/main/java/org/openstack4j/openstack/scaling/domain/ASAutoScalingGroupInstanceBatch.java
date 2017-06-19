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
public class ASAutoScalingGroupInstanceBatch implements ModelEntity {

	private static final long serialVersionUID = -2406696695075277186L;

	@JsonProperty("instances_id")
	private List<String> instanceIds;

	@JsonProperty("instance_delete")
	private String delete;

	@JsonProperty
	private String action;
	
	public enum Action {
		ADD,
		REMOVE,
		;
	}
}
