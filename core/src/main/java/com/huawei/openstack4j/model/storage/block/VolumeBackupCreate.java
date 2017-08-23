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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.storage.block.builder.VolumeBackupCreateBuilder;

/**
 *  Represents entity which is used for creating a volume backup
 * @author Huang Yi
 *
 */
public interface VolumeBackupCreate extends ModelEntity, Buildable<VolumeBackupCreateBuilder>  {
	
	/**
	 * @return the name of the Volume Transfer.
	 */
    String getName();
    
	/**
	 * @return the description of the volume backup
	 */
	String getDescription();
	
	/**
	 * @return The UUID of the volume.
	 */
	String getVolumeId();
	
	/**
	 * @return The container name or null.
	 */
	String getContainer();
	
	/**
	 * @return The backup mode. A valid value is true for incremental backup mode or false for full backup mode
	 */
	boolean isIncremental();

	/**
	 * @return Force mode. True to do backup while a volume is attached. Default is false.
	 */
	boolean isForce();

	/**
	 * @return Force mode. True to do backup while a volume is attached. Default is false.
	 */
	String getSnapshotId();


}
