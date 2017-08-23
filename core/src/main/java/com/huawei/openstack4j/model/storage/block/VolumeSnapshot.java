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
package com.huawei.openstack4j.model.storage.block;

import java.util.Date;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.storage.block.Volume.Status;
import com.huawei.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;

/**
 * An OpenStack Volume Snapshot which is a point-in-time copy of a volume
 *
 * @author Jeremy Unruh
 */
public interface VolumeSnapshot extends ModelEntity, Buildable<VolumeSnapshotBuilder> {

	/**
	 * @return the snapshot identifier
	 */
	String getId();

	/**
	 * @return the name of the snapshot
	 */
	String getName();

	/**
	 * @return the description of the snapshot
	 */
	String getDescription();

	/**
	 * The volume identifier of an existing volume
	 *
	 * @return the volume identifier or null
	 */
	String getVolumeId();

	/**
	 * @return the status of the snapshot
	 */
	Status getStatus();

	/**
	 * Size in GBs
	 *
	 * @return the size of the snapshot in GB
	 */
	int getSize();

	/**
	 * @return the created data of the snapshot
	 */
	Date getCreated();

	/**
	 * @return extended meta data information. key value pair of String key, String value
	 */
	Map<String, String> getMetaData();
}
