package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;

/**
 * Block Device Mapping
 * 
 * @author jaroslav.sovicka@oracle.com
 */
public interface BlockDeviceMappingBuilder extends Buildable.Builder<BlockDeviceMappingBuilder, BlockDeviceMappingCreate> {

    /**
     * The boot index
     * 
     * @param i
     * @return BlockDeviceMappingBuilder
     */
	BlockDeviceMappingBuilder bootIndex(int i);

	/**
	 * A device name where the volume is attached in the system at /dev/dev_name. This value is typically vda.
	 * 
	 * @param deviceName the device name
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder deviceName(String deviceName);

	/**
	 * The device ID.
	 * 
	 * @param id the device id
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder uuid(String id);

	/**
	 * Either snap or any other value, including a blank string. snap means that the volume was created from a snapshot.
	 * 
	 * @param type the destination type
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder destinationType(String type);

	/**
	 * Either snap or any other value, including a blank string. snap means that the volume was created from a snapshot.
	 * 
	 * @param type the source type
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder sourceType(String type);
	
	/**
	 * Set to True to delete the volume when the instance is deleted. Set to False to retain the volume when the instance is deleted.
	 * 
	 * @param deleteOnTermination
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder deleteOnTermination(boolean deleteOnTermination);

}
