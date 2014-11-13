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

	private String volumeId;

	@Override
	public String getVolumeId() {
		return volumeId;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("volumeId", volumeId).toString();

	}
	
	public NovaVolumeAttachment(String volumeId) {
		this.volumeId = volumeId;
	}

}
