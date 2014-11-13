package org.openstack4j.model.compute;

import org.openstack4j.model.ModelEntity;

/**
 * Provides volume attachment result
 * 
 * @author Octopus Zhang
 */
public interface VolumeAttachment extends ModelEntity {
	
	/**
	 * the volume's id int this attachment
	 * @return the id
	 */
	String getVolumeId();
}