/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.model.storage.block;

import org.openstack4j.model.ModelEntity;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-07 08:37:14
 */
public interface CloudVolumeBackupCreate extends ModelEntity {

	/**
	 * @return the name of the Volume Transfer.
	 */
	String getName();

	/**
	 * @return the description of the volume backup
	 */
	String getDescription();

	/**
	 * @return The UUID of the volume.
	 */
	String getVolumeId();

}
