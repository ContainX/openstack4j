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
package org.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.storage.BlockVolumeBackupService;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.VolumeBackup;
import org.openstack4j.model.storage.block.VolumeBackupCreate;
import org.openstack4j.model.storage.block.VolumeBackupRestore;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackup;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackup.VolumeBackups;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackupRestore;

import com.google.common.base.Strings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * OpenStack (Cinder) Volume Backup Operations API Implementation.
 *
 * @author Huang Yi
 */
public class BlockVolumeBackupServiceImpl extends BaseBlockStorageServices implements BlockVolumeBackupService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeBackup> list(boolean detail) {
		String uri = detail? "/backups/detail": "/backups";
		return get(VolumeBackups.class, uri).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeBackup> list(boolean detail, Map<String, String> filteringParams) {
		Invocation<VolumeBackups> invocation = buildInvocation(detail, filteringParams);
		return invocation.execute().getList();
	}

	private Invocation<VolumeBackups> buildInvocation(boolean detail, Map<String, String> filteringParams) {
		String uri = detail? "/backups/detail": "/backups";
		Invocation<VolumeBackups> invocation = get(VolumeBackups.class, uri);
		if (filteringParams == null) {
			return invocation;
		} else {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				invocation = invocation.param(entry.getKey(), entry.getValue());
			}
		}
		return invocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackup get(String backupId) {
		checkNotNull(backupId);
		return get(CinderVolumeBackup.class, uri("/backups/%s", backupId)).execute();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String backupId) {
		checkNotNull(backupId);
		return deleteWithResponse(uri("/backups/%s", backupId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackup create(VolumeBackupCreate vbc) {
		checkNotNull(vbc);
		checkNotNull(!Strings.isNullOrEmpty(vbc.getVolumeId()), "parameter `vbc.volumeId` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(vbc.getSnapshotId()), "parameter `vbc.snapshotId` should not be empty");
		return post(CinderVolumeBackup.class, uri("/backups")).entity(vbc).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupRestore restore(String backupId, String name, String volumeId) {
		_VolumeBackupRestore entity = new _VolumeBackupRestore(name, volumeId);
		return post(CinderVolumeBackupRestore.class, uri("/backups/%s/restore", backupId)).entity(entity).execute();
	}

	@JsonRootName("restore")
	private static class _VolumeBackupRestore implements ModelEntity {

		private static final long serialVersionUID = 1L;
		@JsonProperty("name")
		private String name;
		@JsonProperty("volume_id")
		private String volumeId;

		public _VolumeBackupRestore(String name, String volumeId) {
			this.name = name;
			this.volumeId = volumeId;
		}
	}

}
