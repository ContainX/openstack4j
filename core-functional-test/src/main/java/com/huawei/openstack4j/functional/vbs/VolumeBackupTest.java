/*******************************************************************************
 *  Copyright 2017 Huawei TLD
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.functional.vbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupCreate;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.VolumeBackup;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
@Test(suiteName = "VolumeBackup/volume-backup")
public class VolumeBackupTest extends AbstractTest {

	static String backupName = randomName();
	Volume volume;
	VolumeBackup volumeBackup;
	AsyncVolumeBackupJob createJobDetail;

	@BeforeClass
	public void prepare() {
		// 取的avaiable状态的硬盘用于测试
		Map<String, String> filter = Maps.newHashMap();
		filter.put("limit", "1");
		filter.put("status", Volume.Status.AVAILABLE.toString());
		List<? extends Volume> list = osclient.blockStorage().volumes().list(filter);
		if (list != null && list.size() > 0) {
			volume = list.get(0);
		} else {
			throw new RuntimeException("No volume availble for test");
		}
	}

	@AfterClass
	public void cleanup() {

	}

	@Test
	public void testVolumeBackupCreate() {
		// 新的创建备份接口 创建一个备份
		AsyncVolumeBackupCreate vbc = Builders.asyncVolumeBackupCreate().name(backupName).volumeId(volume.getId())
				.build();
		final AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().create(vbc);
		Assert.assertNotNull(job.getId());

		// 每两分钟查询一次备份Job的状态，假如状态为 Success，则表示成功
		Retry retry = new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 40;
			}

			@Override
			public Integer delay() {
				return 30;
			}

			@Override
			public Object run() {
				AsyncVolumeBackupJob detail = osclient.blockStorage().jobs().get(job.getId());
				if (AsyncVolumeBackupJob.Status.SUCCESS.equals(detail.getStatus())) {
					return detail;
				}

				if (AsyncVolumeBackupJob.Status.FAIL.equals(detail.getStatus())) {
					throw new RuntimeException("Interval task failed");
				}

				return null;
			}
		};

		// 
		createJobDetail = (AsyncVolumeBackupJob) this.retry(retry);
	}

	@Test(priority = 1, dependsOnMethods = { "testVolumeBackupCreate" })
	public void testVolumeBackupGet() {
		Object backupId = createJobDetail.getEntities().get("backup_id");
		volumeBackup = osclient.blockStorage().backups().get(backupId.toString());
		Assert.assertEquals(backupName, volumeBackup.getName());
		Assert.assertEquals(volume.getId(), volumeBackup.getVolumeId());
	}

	@Test(priority = 2, dependsOnMethods = { "testVolumeBackupGet" })
	public void testVolumeBackupListFilter() {
		HashMap<String, String> filter = Maps.newHashMap();
		filter.put("name", backupName);

		// list backups with detail
		List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(true, filter);
		Assert.assertEquals(1, list.size());
		VolumeBackup volumeBackup = list.get(0);
		Assert.assertEquals(volumeBackup.getId(), volumeBackup.getId());
		Assert.assertEquals(volumeBackup.getVolumeId(), volumeBackup.getVolumeId());

		// list backups with brief attributes
		list = osclient.blockStorage().backups().list(false, filter);
		Assert.assertEquals(1, list.size());
		volumeBackup = list.get(0);
		Assert.assertEquals(volumeBackup.getId(), volumeBackup.getId());
		Assert.assertNull(volumeBackup.getVolumeId());
	}

	@Test(priority = 2, dependsOnMethods = { "testVolumeBackupGet" })
	public void testVolumeBackupList() {

		// list backups with detail
		List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(true);
		Assert.assertTrue(list.size() >= 1);
		VolumeBackup volumeBackup = list.get(0);
		Assert.assertNotNull(volumeBackup.getId());
		Assert.assertNotNull(volumeBackup.getVolumeId());
		Assert.assertNotNull(volumeBackup.getName());

		// list backups with brief attributes
		list = osclient.blockStorage().backups().list(false);
		Assert.assertTrue(list.size() >= 1);
		volumeBackup = list.get(0);
		Assert.assertNotNull(volumeBackup.getId());
		Assert.assertNotNull(volumeBackup.getName());
		Assert.assertNull(volumeBackup.getVolumeId());
	}

	@Test(priority = 2, dependsOnMethods = { "testVolumeBackupGet" })
	public void testVolumeBackupRestore() {

		// list backups with detail
		AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().restore(volumeBackup.getId(), volume.getId());
		Retry retry = new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 40;
			}

			@Override
			public Integer delay() {
				return 30;
			}

			@Override
			public Object run() {
				AsyncVolumeBackupJob detail = osclient.blockStorage().jobs().get(job.getId());
				if (AsyncVolumeBackupJob.Status.SUCCESS.equals(detail.getStatus())) {
					return detail;
				}

				if (AsyncVolumeBackupJob.Status.FAIL.equals(detail.getStatus())) {
					throw new RuntimeException("Inteval task failed");
				}

				return null;
			}
		};

		AsyncVolumeBackupJob restoreJob = (AsyncVolumeBackupJob) this.retry(retry);
		Assert.assertEquals("bksRestoreBackup", restoreJob.getType());
	}

	@Test(priority = 100, dependsOnMethods = { "testVolumeBackupGet" })
	public void testVolumeBackupDelete() {
		ActionResponse delete = osclient.blockStorage().backups().delete(volumeBackup.getId());
		Assert.assertTrue(delete.isSuccess());

		HashMap<String, String> filter = Maps.newHashMap();
		filter.put("name", backupName);

		// list backups with detail
		List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(true, filter);
		if (list.size() == 1) {
			Assert.assertEquals(list.get(0).getStatus(), VolumeBackup.Status.DELETING);
		}
	}

}
