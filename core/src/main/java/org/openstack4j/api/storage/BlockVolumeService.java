package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
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
	
	/**
	 * Deletes the specified volume
	 * 
	 * @param volumeId the volume identifier
	 * @return the action response
	 */
	ActionResponse delete(String volumeId);

	/**
	 * Attempt forced removal of volume, regardless of the state.
	 * It's dangerous but useful. It's not 100% success.
	 * 
	 * @param volumeId the volume id
	 * @return the action response
	 */
	ActionResponse forceDelete(String volumeId);

	/**
	 * Creates a new Block Storage Volume
	 * @param volume the volume for create
	 * @return the created volume
	 */
	Volume create(Volume volume);
	
	/**
	 * OpenStack only allows name or description to be updated. This call enforces that based on the API docs.
	 * 
	 * @param volumeId the volume id
	 * @param name the name to update (null indicates no name update)
	 * @param description the description to update (null indicates no description update)
	 * @return the action response
	 */
	ActionResponse update(String volumeId, String name, String description);

}
