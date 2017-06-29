/*******************************************************************************
 *  Copyright 2017 Huawei TLD                                   
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
package org.openstack4j.api.storage;

import org.openstack4j.common.RestService;
import org.openstack4j.model.storage.block.AsyncVolumeBackupCreate;
import org.openstack4j.model.storage.block.AsyncVolumeBackupJob;

/**
 * <h3>New Volume Backup provided by OTC. </h3>
 * 
 * <p>Volume backup job is executed asynchronous in this server</p>
 * 
 * @author QianBiao.NG
 * @date   2017-06-07 10:36:10
 */
public interface AsyncVolumeBackupService extends RestService {

	/**
	 * create a new volume backup asynchronously
	 * 
	 * @param cvbc volume backup create meta
	 * @return Asynchronous Volume Backup Job 
	 */
	public AsyncVolumeBackupJob create(AsyncVolumeBackupCreate cvbc);
	
	/**
	 * restore a volume backup to a volume asynchronously
	 * 
	 * @param volumeBackupId  the volume backup to be used
	 * @param volumeId		  the volume to be restored
	 * @return Asynchronous Volume Backup Job 
	 */
	public AsyncVolumeBackupJob restore(String volumeBackupId, String volumeId);
	

	/**
	 * 
	 * @return asynchronous volume backup jobs service 
	 */
	public AsyncVolumeBackupJobService jobs();
}
