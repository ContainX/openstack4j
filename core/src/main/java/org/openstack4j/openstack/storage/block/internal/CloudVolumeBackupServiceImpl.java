/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import org.openstack4j.api.storage.CloudVolumeBackupService;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.storage.block.CloudVolumeBackupCreate;
import org.openstack4j.model.storage.block.CloudVolumeBackupJob;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupJob;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:47:47
 */
public class CloudVolumeBackupServiceImpl extends BaseOpenStackService implements CloudVolumeBackupService {

	public CloudVolumeBackupServiceImpl() {
		super(ServiceType.CLOUD_VOLUME_BACKUP);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public CloudVolumeBackupJob create(CloudVolumeBackupCreate cvbc) {
		checkNotNull(cvbc);
		checkNotNull(cvbc.getVolumeId());
		return post(VBSVolumeBackupJob.class, uri("/cloudbackups")).entity(cvbc).execute();
	}

}
