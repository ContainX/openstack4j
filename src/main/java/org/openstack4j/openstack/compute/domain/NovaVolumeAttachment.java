package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.VolumeAttachment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * return a description for this volume attachment job
 * 
 * @author Octopus Zhang
 */
@JsonRootName("volumeAttachment")
public class NovaVolumeAttachment implements VolumeAttachment {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String device;

	@JsonProperty
	private String id;

	@JsonProperty
	private String serverId;

	@JsonProperty
	private String volumeId;

	@Override
	public String getDevice() {
		return device;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getServerId() {
		return serverId;
	}

	@Override
	public String getVolumeId() {
		return volumeId;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("device", device).add("id", id).add("serverId", serverId)
				.add("volumeId", volumeId).toString();

	}

}
