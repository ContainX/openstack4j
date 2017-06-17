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
public class Personality implements ModelEntity {

	private static final long serialVersionUID = -8050000642923839411L;
	
	@JsonProperty
	private String path;
	
	@JsonProperty
	private String content;
}
