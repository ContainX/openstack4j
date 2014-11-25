package org.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.Builders;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeType;
import org.openstack4j.openstack.storage.block.domain.CinderVolume;
import org.openstack4j.openstack.storage.block.domain.CinderVolume.Volumes;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeType.VolumeTypes;

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
	public Volume create(Volume volume) {
		checkNotNull(volume);
		return post(CinderVolume.class, uri("/volumes")).entity(volume).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse update(String volumeId, String name, String description) {
		checkNotNull(volumeId);
		if (name == null && description == null)
		    return ActionResponse.actionFailed("Name and Description are both required");
		
		return put(ActionResponse.class, uri("/volumes/%s", volumeId))
        		    .entity(Builders.volume().name(name).description(description).build())
        		    .execute();
	}

}
