package org.openstack4j.api.storage;

import org.openstack4j.common.RestService;

/**
 * Block Storage (Cinder) Service Operation API
 * 
 * @author Jeremy Unruh
 */
public interface BlockStorageService extends RestService {

	/**
	 * @return the Volume Service API
	 */
	BlockVolumeService volumes();
	
	/**
	 * @return the Volume Snapshot Service API
	 */
	BlockVolumeSnapshotService snapshots();
	
}
