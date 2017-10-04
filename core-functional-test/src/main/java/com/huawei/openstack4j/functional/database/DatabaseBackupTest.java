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
package com.huawei.openstack4j.functional.database;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.database.constants.BackupStatus;
import com.huawei.openstack4j.openstack.database.domain.DatabaseBackup;
import com.huawei.openstack4j.openstack.database.domain.DatabaseBackupCreate;
import com.huawei.openstack4j.openstack.database.domain.DatabaseBackupCreateResponse;
import com.huawei.openstack4j.openstack.database.domain.DatabaseBackupPolicy;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.database.domain.DatabaseRestorePoint;

@Test(suiteName = "Database/backup/Test")
public class DatabaseBackupTest extends AbstractTest {

	String name = randomName();
	DatabaseInstance instance;
	DatabaseBackup backup;

	@BeforeClass
	public void prepare() {
		// 获取一个我们创建的实例 (名字以 SDK 开头)
		List<DatabaseInstance> instances = osclient.database().instances().list();
		for (DatabaseInstance instance : instances) {
			if (instance.getName().startsWith("SDK")) {
				this.instance = instance;
				break;
			}
		}

		Assert.assertNotNull(instance);
	}

	@AfterClass
	public void testDeleteDatabaseBackup() {
		if (backup != null) {
			// 等待还原完成
			this.retry(new Retry() {
				@Override
				public Integer maxRetryTimes() {
					return 30;
				}

				@Override
				public Integer delay() {
					return 15;
				}

				@Override
				public Object run() {
					DatabaseInstance dbInstance = osclient.database().instances().get(instance.getId());
					if(dbInstance.getStatus().equals("ACTIVE")) {
						return dbInstance;
					}
					return null;
				}
				
			});
			
			ActionResponse delete = osclient.database().backups().delete(backup.getId());
			Assert.assertTrue(delete.isSuccess());
		}
	}

	@Test(priority = 1)
	public void testGetDatabaseBackupPolicy() {
		DatabaseBackupPolicy backupPolicy = osclient.database().backups().getBackupPolicy(instance.getId());
		Assert.assertTrue(backupPolicy.getKeepDay() >= 0);
		Assert.assertNotNull(backupPolicy.getStartTime());
	}

	@Test(priority = 2)
	public void testUpdateDatabaseBackupPolicy() {
		DatabaseBackupPolicy policy = DatabaseBackupPolicy.builder().keepDay(3).startTime("03:10:00").build();
		ActionResponse response = osclient.database().backups().updateBackupPolicy(instance.getId(), policy);
		Assert.assertTrue(response.isSuccess());

		DatabaseBackupPolicy backupPolicy = osclient.database().backups().getBackupPolicy(instance.getId());
		Assert.assertEquals(backupPolicy.getKeepDay().intValue(), 3);

		// 实际测试的时候，秒的数据不会返回。。
		Assert.assertEquals(backupPolicy.getStartTime(), "03:10");
	}

	@Test(priority = 3)
	public void testCreateDatabaseBackup() {
		DatabaseBackupCreate creation = DatabaseBackupCreate.builder().name(name).description("sdk unittests")
				.instance(instance.getId()).build();
		DatabaseBackupCreateResponse response = osclient.database().backups().create(creation);
		backup = response.getBackup();
		Assert.assertNotNull(response.getExtendParam());
		Assert.assertEquals(response.getExtendParam().getJobs().size(), 1);
		Assert.assertNotNull(response.getExtendParam().getJobs().get(0).getId());

		Assert.assertNotNull(backup);
		Assert.assertNotNull(backup.getDatastore().getType());
		Assert.assertNotNull(backup.getDatastore().getVersion());
		Assert.assertNotNull(backup.getDatastore().getVersionId());

		Assert.assertEquals(backup.getDescription(), "sdk unittests");
		Assert.assertEquals(backup.getName(), name);
		Assert.assertEquals(backup.getBackupType(), "1");
		Assert.assertEquals(backup.getInstanceId(), instance.getId());
		Assert.assertEquals(backup.getStatus(), BackupStatus.BUILDING);

		// 等待备份完成
		this.retry(new Retry() {
			@Override
			public Integer maxRetryTimes() {
				return 30;
			}

			@Override
			public Integer delay() {
				return 15;
			}

			@Override
			public Object run() {
				List<DatabaseBackup> list = osclient.database().backups().list();
				DatabaseBackup found = null;
				for (DatabaseBackup temp : list) {
					if (temp.getId().equals(backup.getId())) {
						found = temp;
						break;
					}
				}

				if (found != null) {
					if (found.getStatus().equals(BackupStatus.COMPLETED)) {
						return found;
					}
					if (found.getStatus().equals(BackupStatus.FAILED)) {
						throw new RuntimeException("create backup failed");
					}
				}

				return null;
			}
		});
	}

	@Test(dependsOnMethods = { "testCreateDatabaseBackup" })
	public void testListDatabaseBackup() {
		List<DatabaseBackup> backups = osclient.database().backups().list();

		Assert.assertTrue(backups.size() > 0);

		DatabaseBackup found = null;
		for (DatabaseBackup temp : backups) {
			if (temp.getId().equals(this.backup.getId())) {
				found = temp;
				break;
			}
		}
		Assert.assertNotNull(found);

		Assert.assertEquals(found.getDescription(), "sdk unittests");
		Assert.assertEquals(found.getName(), name);
		Assert.assertEquals(found.getBackupType(), "1");
		Assert.assertEquals(found.getInstanceId(), instance.getId());
		Assert.assertEquals(found.getStatus(), BackupStatus.COMPLETED);
	}

	@Test(dependsOnMethods = { "testListDatabaseBackup" })
	public void testRestoreToExistsInstance() {
		DatabaseRestorePoint point = DatabaseRestorePoint.builder().backupRef(backup.getId())
				// .restoreTime(new Date())
				.build();
		List<String> jobIds = osclient.database().backups().restoreToExistInstance(instance.getId(), point);
		Assert.assertTrue(jobIds.size() > 0);
	}

}
