/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample;

import org.openstack4j.model.storage.block.CloudVolumeBackupJob;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupCreate;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupJob;
import org.testng.annotations.Test;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
public class VolumeBackupSample extends AbstractSample {

	@Test
	public void testCloudVolumeBackup() {
		VBSVolumeBackupCreate vbc = VBSVolumeBackupCreate.builder().name("qianbiao-ng-os4j-1")
				.volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85").build();

		VBSVolumeBackupCreate.builder().build();
		VBSVolumeBackupJob.builder();
		CloudVolumeBackupJob create = osc.cloudVolumeBackup().create(vbc);
		System.out.println(create);
	}

}
