package org.openstack4j.model.compute.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;

/**
 *
 * @author jaroslav.sovicka@oracle.com
 */
public interface BlockDeviceMappingBuilder extends Buildable.Builder<BlockDeviceMappingBuilder, BlockDeviceMappingCreate> {

	BlockDeviceMappingBuilder bootIndex(int i);

	BlockDeviceMappingBuilder deviceName(String deviceName);

	BlockDeviceMappingBuilder uuid(String id);

}
