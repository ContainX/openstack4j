package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeType;

/**
 * Manages Volumes and Volume Type based operations against Block Storage (Cinder)
 * 
 * @author Jeremy Unruh
 */
public interface BlockVolumeService extends RestService {

	/**
	 *  The volume type defines the characteristics of a volume
	 *  
	 * @return List of VolumeType entities
	 */
	List<? extends VolumeType> listVolumeTypes();

	/**
	 * Lists summary information for all Block Storage volumes that the tenant who submits the request can access.
	 * 
	 * @return List of Volumes
	 */
	List<? extends Volume> list();
	
	/**
	 * Gets a Block Storage volume by ID
	 * @param volumeId the volume identifier
	 * @return the volume or null if not found
	 */
	Volume get(String volumeId);
}
