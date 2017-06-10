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
package org.openstack4j.model.storage.block.builder;

import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.storage.block.Volume;

/**
 * Builder used for Creating or Updating Volumes
 * 
 * @author Jeremy Unruh
 */
public interface VolumeBuilder extends Builder<VolumeBuilder, Volume> {

	/**
	 * See {@link Volume#getName()}
	 * 
	 * @param name the name of the volume
	 * @return VolumeBuilder
	 */
	VolumeBuilder name(String name);
	
	/**
	 * See {@link Volume#getDescription()} <b>Optional</b>
	 * 
	 * @param description the description of the volume
	 * @return VolumeBuilder
	 */
	VolumeBuilder description(String description);
	
	/**
	 * To create a volume from an existing volume, specify the ID of the existing volume. <b>Optional</b>
	 * 
	 * @param uuid the id of an existing volume
	 * @return VolumeBuilder
	 */
	VolumeBuilder source_volid(String uuid);
	
	/**
	 * To create a volume from an existing snapshot, specify the ID of the existing volume snapshot. <b>Optional</b>
	 * 
	 * @param snapshotId the id of an existing volume snapshot
	 * @return VolumeBuilder
	 */
	VolumeBuilder snapshot(String snapshotId);
	
	/**
	 * The ID of the image from which you want to create the volume. Required to create a bootable volume. <b>Optional</b>
	 * 
	 * @param imageRef the id of an existing image
	 * @return VolumeBuilder
	 */
	VolumeBuilder imageRef(String imageRef);
	
	/**
	 * The size of the volume, in GB.
	 * 
	 * @param size the size in GB
	 * @return VolumeBuilder
	 */
	VolumeBuilder size(int size);
	
	/**
	 * The associated volume type. <b>Optional</b>
	 * 
	 * @param volumeType The associated volume type.
	 * @return VolumeBuilder
	 */
	VolumeBuilder volumeType(String volumeType);
	
	/**
	 * Enables or disables the bootable attribute. You can boot an instance from a bootable volume. <b>Optional</b>
	 * 
	 * @param isBootable true to enable the bootable flag
	 * @return VolumeBuilder
	 */
	VolumeBuilder bootable(boolean isBootable);
	
	/**
	 * One or more metadata key and value pairs to associate with the volume. <b>Optional</b>
	 * 
	 * @param metadata metadata to set
	 * @return VolumeBuilder
	 */
	VolumeBuilder metadata(Map<String, String> metadata);
	
	/**
     * The associated availability zone. <b>Optional</b>
     * 
     * @param zone The associated availability zone.
     * @return VolumeBuilder
     */
    VolumeBuilder zone(String zone);
	
}
