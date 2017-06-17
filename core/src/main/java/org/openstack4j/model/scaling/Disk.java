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
public class Disk implements ModelEntity {

	private static final long serialVersionUID = -8180543136177519493L;
	
	@JsonProperty
	private Integer size;
	
	@JsonProperty("volume_type")
	private String volumeType;
	
	@JsonProperty("disk_type")
	private String diskType;
}
