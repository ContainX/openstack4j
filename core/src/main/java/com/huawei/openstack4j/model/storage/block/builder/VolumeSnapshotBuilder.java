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
package com.huawei.openstack4j.model.storage.block.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;

/**
 * A Builder which creates a Volume Snapshot.
 *
 * NOTE: The only <B>REQUIRED</B> field when creating a snapshot is
 * {@link #volume(String)} . All other fields are optional
 *
 * @author Jeremy Unruh
 */
public interface VolumeSnapshotBuilder extends Builder<VolumeSnapshotBuilder, VolumeSnapshot> {

	/**
	 * Name of the snapshot
	 *
	 * @param name the name
	 * @return the volume snapshot builder
	 */
	VolumeSnapshotBuilder name(String name);

	/**
	 * Description of the snapshot
	 *
	 * @param description the description
	 * @return the volume snapshot builder
	 */
	VolumeSnapshotBuilder description(String description);

	/**
	 * The ID of of the existing Volume <B>(REQUIRED)</b>
	 *
	 * @param volumeId the volume id
	 * @return the volume snapshot builder
	 */
	VolumeSnapshotBuilder volume(String volumeId);

	/**
	 * One or more metadata key and value pairs to associate with the volume snapshot. <b>Optional</b>
	 *
	 * @param metadata metadata to set
	 * @return VolumeSnapshotBuilder
	 */
	VolumeSnapshotBuilder metadata(Map<String, String> metadata);

	/**
	 * [True/False] Indicate whether to snapshot, even if the volume is attached.
	 *
	 * @param force true to force an attached volume to be a snapshot
	 * @return the volume snapshot builder
	 */
	VolumeSnapshotBuilder force(boolean force);

}
