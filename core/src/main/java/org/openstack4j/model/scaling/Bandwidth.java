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
public class Bandwidth implements ModelEntity {

	private static final long serialVersionUID = -1154665269978660693L;
	
	@JsonProperty
	private String size;
	
	@JsonProperty("share_type")
	private String shareType;
	
	@JsonProperty("charging_mode")
	private String chargingMode;
}
