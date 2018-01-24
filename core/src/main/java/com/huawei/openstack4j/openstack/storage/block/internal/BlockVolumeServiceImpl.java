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
package com.huawei.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.storage.BlockVolumeService;
import com.huawei.openstack4j.api.storage.BlockVolumeTransferService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.VolumeType;
import com.huawei.openstack4j.model.storage.block.VolumeUploadImage;
import com.huawei.openstack4j.model.storage.block.options.UploadImageData;
import com.huawei.openstack4j.openstack.storage.block.domain.AttachAction;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderUploadImageData;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolume;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolume.Volumes;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeMigration;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeType;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeType.VolumeTypes;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeUploadImage;
import com.huawei.openstack4j.openstack.storage.block.domain.ExtendAction;
import com.huawei.openstack4j.openstack.storage.block.domain.ForceDeleteAction;
import com.huawei.openstack4j.openstack.storage.block.domain.ForceDetachAction;
import com.huawei.openstack4j.openstack.storage.block.domain.ForceDetachConnector;
import com.huawei.openstack4j.openstack.storage.block.domain.ResetStatusAction;
import com.huawei.openstack4j.openstack.storage.block.domain.UpdateReadOnlyFlagAction;

/**
 * Manages Volumes and Volume Type based operations against Block Storage (Cinder)
 *
 * @author Jeremy Unruh
 */
public class BlockVolumeServiceImpl extends BaseBlockStorageServices implements BlockVolumeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeType> listVolumeTypes() {
		return get(VolumeTypes.class, uri("/types")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Volume> list() {
		return get(Volumes.class, uri("/volumes/detail")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Volume> list(Map<String, String> filteringParams) {
		Invocation<Volumes> volumeInvocation = buildInvocation(filteringParams);
		return volumeInvocation.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Volume get(String volumeId) {
		checkNotNull(volumeId);
		return get(CinderVolume.class, uri("/volumes/%s", volumeId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String volumeId) {
		checkNotNull(volumeId);
		return deleteWithResponse(uri("/volumes/%s", volumeId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse forceDelete(String volumeId) {
		checkNotNull(volumeId);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(new ForceDeleteAction())
				.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse resetState(String volumeId, Volume.Status status) {
		checkNotNull(volumeId);
		checkNotNull(status);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(new ResetStatusAction(status))
				.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse extend(String volumeId, Integer newSize) {
		checkNotNull(volumeId);
		checkNotNull(newSize);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(new ExtendAction(newSize))
				.execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Volume create(Volume volume) {
		checkNotNull(volume);
		return post(CinderVolume.class, uri("/volumes")).entity(volume).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Volume create(Volume volume, Map<String, Object> schedulerHints) {
		checkNotNull(volume);
		if (schedulerHints == null || schedulerHints.isEmpty()) {
			return create(volume);
		} else {
			HashMap<Object, Object> repackage = Maps.newHashMap();
			repackage.put("volume", volume);
			repackage.put("OS-SCH-HNT:scheduler_hints", schedulerHints);
			return post(CinderVolume.class, uri("/volumes")).entity(repackage).execute();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse update(String volumeId, String name, String description) {
		checkNotNull(volumeId);
		if (name == null && description == null)
			return ActionResponse.actionFailed("Name and Description are both required", 412);

		return put(ActionResponse.class, uri("/volumes/%s", volumeId))
				.entity(Builders.volume().name(name).description(description).build()).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteVolumeType(String volumeTypeId) {
		checkNotNull(volumeTypeId);
		delete(Void.class, uri("/types/%s", volumeTypeId)).execute();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeType createVolumeType(VolumeType volumeType) {
		checkNotNull(volumeType);
		return post(CinderVolumeType.class, uri("/types")).entity(volumeType).execute();
	}

	@Override
	public ActionResponse migrate(String volumeId, String hostService, boolean forceHostCopy) {
		CinderVolumeMigration migration = new CinderVolumeMigration(hostService, forceHostCopy);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(migration).execute();
	}

	@Override
	public VolumeUploadImage uploadToImage(String volumeId, UploadImageData data) {
		checkNotNull(volumeId, "volumeId");
		checkNotNull(data, "UploadImageData");

		return post(CinderVolumeUploadImage.class, uri("/volumes/%s/action", volumeId))
				.entity(CinderUploadImageData.create(data)).execute();
	}

	@Override
	public BlockVolumeTransferService transfer() {
		return Apis.get(BlockVolumeTransferService.class);
	}

	private Invocation<Volumes> buildInvocation(Map<String, String> filteringParams) {
		Invocation<Volumes> volumeInvocation = get(Volumes.class, "/volumes/detail");
		if (filteringParams == null) {
			return volumeInvocation;
		} else {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				volumeInvocation = volumeInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return volumeInvocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse readOnlyModeUpdate(String volumeId, boolean readonly) {
		checkNotNull(volumeId);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
				.entity(new UpdateReadOnlyFlagAction(readonly)).execute();
	}

	/**
	 * <p>Description:Attach volume to a server</p>
	 * Volume status must be available.
	 * You should set instanceId or hostName.
	 * <p>Author:Wang Ting/王婷</p>
	 *
	 * @param volumeId
	 * @param instanceId
	 * @param mountpoint
	 * @param hostName
	 * @return ActionResponse
	 */
	@Override
	public ActionResponse attach(String volumeId, String instanceId, String mountpoint, String hostName) {
		checkNotNull(volumeId);
		checkNotNull(instanceId);
		checkNotNull(mountpoint);
		checkNotNull(hostName);
		AttachAction attach = new AttachAction(instanceId, mountpoint, hostName);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(attach).execute();
	}

	/**
	 * <p>Description:Force detach a volume</p>
	 * <p>Author:Wang Ting/王婷</p>
	 *
	 * @param volumeId
	 * @param initiator
	 * @param attachmentId
	 * @return
	 * @Title: forceDetach
	 * @see com.huawei.openstack4j.api.storage.BlockVolumeService#forceDetach(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ActionResponse forceDetach(String volumeId, String initiator, String attachmentId) {
		checkNotNull(volumeId);
		checkNotNull(initiator);
		checkNotNull(attachmentId);
		ForceDetachConnector connector = new ForceDetachConnector(initiator);
		ForceDetachAction detach = new ForceDetachAction(attachmentId, connector);
		return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(detach).execute();
	}
}
