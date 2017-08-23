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
package com.huawei.openstack4j.api.storage;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeBackup;
import com.huawei.openstack4j.model.storage.block.VolumeBackupCreate;
import com.huawei.openstack4j.model.storage.block.VolumeBackupRestore;

/**
 * A backup is a full copy of a volume stored in an external service. A backup
 * can subsequently be restored from the external service to either the same
 * volume that the backup was originally taken from or to a new volume.
 * {@link http://developer.openstack.org/api-ref/block-storage/v2/} 
 * 
 * @author Huang Yi
 *
 */
public interface BlockVolumeBackupService extends RestService {
	/**
	 * Lists detailed information for all Block Storage backups that the tenant who submits the request can access.
	 *
	 * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
	 * @return List of VolumeBackup
	 */
	List<? extends VolumeBackup> list(boolean detail);

	/**
	 * Returns list of Block Storage backups filtered by parameters.
	 * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
	 * @param filteringParams  map (name, value) of filtering parameters
	 * @return
	 */
	List<? extends VolumeBackup> list(boolean detail, Map<String, String> filteringParams);

	/**
	 * Shows information for a specified backup.
	 * @param id the backup identifier
	 * @return the volume backup or null
	 */
	VolumeBackup get(String backupId);

	/**
	 * Delete a specified volume backup 
	 * @param backupId the backup identifier
	 * @return the action response
	 */
	ActionResponse delete(String backupId);

	/**
	 * Create volume backup
	 * @param vbc the input for backup creation
	 * @return the volume backup created.
	 */
	VolumeBackup create(VolumeBackupCreate vbc);

	/**
	 * Restore volume from a a specified backup.
	 * If volumeId is provided, the backup will be restored to existng volume specified by the volume id.
	 * Otherwise, a new volume will be created from the backup with assigned volume name.
	 * @param backupId the backup identifier
	 * @param name  the volume name
	 * @param volumeId the identified of existing volume
	 * @return
	 */
	VolumeBackupRestore restore(String backupId, String name, String volumeId);

}
