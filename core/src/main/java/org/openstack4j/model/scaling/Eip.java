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
public class Eip implements ModelEntity {

	private static final long serialVersionUID = -1069053200085079737L;

	@JsonProperty("ip_type")
	private String ipType;
	
	@JsonProperty
	private Bandwidth bandwidth;
}
