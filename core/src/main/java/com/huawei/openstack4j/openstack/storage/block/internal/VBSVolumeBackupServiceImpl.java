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
package com.huawei.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.storage.AsyncVolumeBackupJobService;
import com.huawei.openstack4j.api.storage.AsyncVolumeBackupService;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupCreate;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupJob;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:47:47
 */
public class VBSVolumeBackupServiceImpl extends BaseVolumeBackupServices implements AsyncVolumeBackupService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupJob create(AsyncVolumeBackupCreate cvbc) {
		checkNotNull(cvbc);
		checkNotNull(cvbc.getName());
		checkNotNull(cvbc.getVolumeId());
		return post(VBSVolumeBackupJob.class, ClientConstants.PATH_ASYNC_VOLUME_BACKUP).entity(cvbc).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupJob restore(String volumeBackupId, String volumeId) {
		checkNotNull(volumeBackupId);
		checkNotNull(volumeId);

		_AsyncVolumeBackupRestore entity = new _AsyncVolumeBackupRestore(volumeId);
		return post(VBSVolumeBackupJob.class, ClientConstants.PATH_ASYNC_VOLUME_BACKUP, "/", volumeBackupId, "/restore")
				.entity(entity).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupJobService jobs() {
		return Apis.get(AsyncVolumeBackupJobService.class);
	}

	@JsonRootName("restore")
	private static class _AsyncVolumeBackupRestore implements ModelEntity {
		private static final long serialVersionUID = 1L;
		@JsonProperty("volume_id")
		private String volumeId;

		public _AsyncVolumeBackupRestore(String volumeId) {
			this.volumeId = volumeId;
		}
	}

}
