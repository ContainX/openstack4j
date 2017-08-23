/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.model.compute.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.compute.BDMDestType;
import com.huawei.openstack4j.model.compute.BDMSourceType;
import com.huawei.openstack4j.model.compute.BlockDeviceMappingCreate;

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
	BlockDeviceMappingBuilder destinationType(BDMDestType type);

	/**
	 * Either snap or any other value, including a blank string. snap means that the volume was created from a snapshot.
	 *
	 * @param type the source type
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder sourceType(BDMSourceType type);

	/**
	 * Set to True to delete the volume when the instance is deleted. Set to False to retain the volume when the instance is deleted.
	 *
	 * @param deleteOnTermination
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder deleteOnTermination(boolean deleteOnTermination);

	/**
	 * Set to create a volume from a snapshot id
	 * @param snapshotId
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder snapshotId(String snapshotId);

	/**
	 * Set to create a volume from a volume id
	 * 
	 * @param volumeId
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder volumeId(String volumeId);
	
	/**
	 * Used to set the volume size of the destination volume (typically needed when source type is image)
	 * 
	 * @param size the size of the volume
	 * @return BlockDeviceMappingBuilder
	 */
	BlockDeviceMappingBuilder volumeSize(Integer size);
	
    /**
     * Used to set the disk_bus, low level detail that some hypervisors
     * (currently only libvirt) may support.
     * 
     * @see <a href=
     *      "https://docs.openstack.org/developer/nova/block_device_mapping.html#block-device-mapping-v2">https://docs.openstack.org/developer/nova/block_device_mapping.html#block-device-mapping-v2</a>
     * 
     * @param disk_bus
     *            type, e.g ide, usb, virtio, scsi
     * @return BlockDeviceMappingBuilder
     */
    BlockDeviceMappingBuilder diskBus(String diskBus);

    /**
     * Used to set the device_type, low level detail that some hypervisors
     * (currently only libvirt) may support.
     * 
     * @see <a href=
     *      "https://docs.openstack.org/developer/nova/block_device_mapping.html#block-device-mapping-v2">https://docs.openstack.org/developer/nova/block_device_mapping.html#block-device-mapping-v2</a>
     * 
     * @param device_type,
     *            disk, cdrom, floppy, lun
     * @return BlockDeviceMappingBuilder
     */
    BlockDeviceMappingBuilder deviceType(String deviceType);

}
