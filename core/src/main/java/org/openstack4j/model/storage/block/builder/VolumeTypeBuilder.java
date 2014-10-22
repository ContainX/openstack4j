package org.openstack4j.model.storage.block.builder;

import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.storage.block.VolumeType;

public interface VolumeTypeBuilder extends Builder<VolumeTypeBuilder, VolumeType> {

	/**
	 * See {@link VolumeType#getName()}
	 * 
	 * @param name the name of the volume type
	 * @return VolumeTypeBuilder
	 */
	VolumeTypeBuilder name(String name);

	/**
	 * See {@link VolumeType#getExtraSpecs()} <b>Optional</b>
	 * 
	 * @param extraSpecs Defining extra specs for the volume type as a key-value map.
	 * @return VolumeTypeBuilder
	 */
	VolumeTypeBuilder extraSpecs(Map<String, String> extraSpecs);
}
