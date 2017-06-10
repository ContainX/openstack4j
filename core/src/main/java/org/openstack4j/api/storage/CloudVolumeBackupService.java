/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.api.storage;

import org.openstack4j.common.RestService;
import org.openstack4j.model.storage.block.CloudVolumeBackupCreate;
import org.openstack4j.model.storage.block.CloudVolumeBackupJob;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:36:10
 */
public interface CloudVolumeBackupService extends RestService {

	public CloudVolumeBackupJob create(CloudVolumeBackupCreate cvbc);

}
