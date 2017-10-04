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

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.constants.StatementType;
import com.huawei.openstack4j.openstack.database.domain.DatabaseErrorLog;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.database.domain.DatabaseSlowLog;
import com.huawei.openstack4j.openstack.database.options.ErrorLogListOptions;

@Test(suiteName = "Database/log/Test")
public class DatabaseLogTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseLogTest.class);

	private DatabaseInstance instance;

	@BeforeClass
	public void prepare() {
		// 获取一个我们创建的实例 (名字以 SDK 开头)
		List<DatabaseInstance> instances = osclient.database().instances().list();
		for (DatabaseInstance instance : instances) {
			if (instance.getName().startsWith("SDK") && instance.getDatastore().getType().equals(DatastoreType.MySQL)) {
				this.instance = instance;
				break;
			}
		}

		Assert.assertNotNull(instance);
	}

	@Test
	public void testListSlowLogs() {
		List<DatabaseSlowLog> list = osclient.database().logs().listSlowLogs(instance.getId(), StatementType.SELECT,
				30);
		logger.info("{}", list);
	}

	@Test
	public void testUpdateDatabaseBackup() {
		Date now = new Date();
		Date tenDaysAgo = new Date(now.getTime() - 10 * 24 * 3600 * 1000);
		ErrorLogListOptions options = ErrorLogListOptions.create().instanceId(instance.getId()).startDate(tenDaysAgo)
				.endDate(now).curPage(1).perPage(20);
		List<DatabaseErrorLog> list = osclient.database().logs().listErrorLogs(options);
		logger.info("{}", list);
	}

}
