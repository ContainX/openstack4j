package org.openstack4j.model.scaling;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstanceConfig implements ModelEntity {

	private static final long serialVersionUID = 3766509894708168472L;

	@JsonProperty("instance_id")
	private String instanceId;

	@JsonProperty("instance_name")
	private String instanceName;

	@JsonProperty
	private String flavorRef;

	@JsonProperty
	private String imageRef;

	@JsonProperty("disk")
	private List<Disk> disks;

	@JsonProperty("key_name")
	private String keyName;

	@JsonProperty
	private String adminPass;

	@JsonProperty
	private List<Personality> personality;

	@JsonProperty("public_ip")
	private PublicIp publicIp;

	@JsonProperty
	private Map<String, String> metadata;

	@JsonProperty("user_data")
	private String userData;
}
