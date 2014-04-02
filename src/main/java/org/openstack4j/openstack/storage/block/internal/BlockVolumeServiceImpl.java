package org.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.storage.BlockVolumeService;
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
		return get(Volumes.class, uri("/volumes")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Volume get(String volumeId) {
		checkNotNull(volumeId);
		return get(CinderVolume.class, uri("/volumes/%s", volumeId)).execute();
	}

}
