package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
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
	
}
