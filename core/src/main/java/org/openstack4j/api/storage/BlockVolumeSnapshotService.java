package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.storage.block.VolumeSnapshot;

/**
 * OpenStack (Cinder) Volume Snapshot Operations API.
 *
 * @author Jeremy Unruh
 */
public interface BlockVolumeSnapshotService extends RestService {

	/**
	 * Lists detailed information for all Block Storage snapshots that the tenant who submits the request can access.
	 *
	 * @return List of VolumeSnapshot
	 */
	List<? extends VolumeSnapshot> list();
	
	/**
	 * Shows information for a specified snapshot.
	 *
	 * @param snapshotId the snapshot identifier
	 * @return the volume snapshot or null
	 */
	VolumeSnapshot get(String snapshotId);
	
	/**
	 * Deletes a specified snapshot
	 *
	 * @param snapshotId the snapshot identifier
	 * @return the action response
	 */
	ActionResponse delete(String snapshotId);
	
	/**
	 * Updates the Name and/or Description for the specified snapshot
	 *
	 * @param snapshotId the snapshot identifier
	 * @param name the new name
	 * @param description the new description
	 * @return the action response
	 */
	ActionResponse update(String snapshotId, String name, String description);
	
	/**
	 * Creates a snapshot, which is a point-in-time copy of a volume. You can create a volume from the snapshot.
	 * 
	 * NOTE: the volume ID within the snapshot must be set or an NullPointerException will be thrown
	 *
	 * @param snapshot the snapshot to create
	 * @return the newly created snapshot
	 */
	VolumeSnapshot create(VolumeSnapshot snapshot);
	
}
