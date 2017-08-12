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
package org.openstack4j.sample.database;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.database.domain.DatabaseBackupPolicy;
import org.openstack4j.openstack.database.domain.DatabaseInstance;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(suiteName = "Database/backup/Sample")
public class DatabaseBackSample extends AbstractSample {

	private DatabaseInstance instance;

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

	@Test(priority = 1)
	public void testGetDatabaseBackup() {
		DatabaseBackupPolicy backupPolicy = osclient.database().backups().getBackupPolicy(instance.getId());
		Assert.assertTrue(backupPolicy.getKeepDay() >= 0);
		Assert.assertNotNull(backupPolicy.getStartTime());
	}

	@Test(priority = 2)
	public void testUpdateDatabaseBackup() {
		DatabaseBackupPolicy policy = DatabaseBackupPolicy.builder().keepDay(3).startTime("03:10:00").build();
		ActionResponse response = osclient.database().backups().updateBackupPolicy(instance.getId(), policy);
		Assert.assertTrue(response.isSuccess());

		DatabaseBackupPolicy backupPolicy = osclient.database().backups().getBackupPolicy(instance.getId());
		Assert.assertEquals(backupPolicy.getKeepDay().intValue(), 3);

		// 实际测试的时候，秒的数据不会返回。。
		Assert.assertEquals(backupPolicy.getStartTime(), "03:10");
	}

}
