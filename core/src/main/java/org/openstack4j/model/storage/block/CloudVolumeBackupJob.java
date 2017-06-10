/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.model.storage.block;

import org.openstack4j.model.ModelEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:42:15
 */
public interface CloudVolumeBackupJob extends ModelEntity {

	String getJobId();

	String getMessage();

}
