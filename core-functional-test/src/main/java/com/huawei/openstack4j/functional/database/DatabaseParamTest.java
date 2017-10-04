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
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.constants.InstanceType;
import com.huawei.openstack4j.openstack.database.constants.OperateInstanceParamResult;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.database.domain.DatabaseParam;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.InstanceParamOperationResult;

@Test(suiteName = "Database/Param/Test")
public class DatabaseParamTest extends AbstractTest {

	DatastoreVersion datastoreVersion = null;
	DatabaseInstance instance = null;
	List<DatabaseParam> params = null;

	@BeforeClass
	public void prepare() {
		// get the first datastore version of MySQL for test
		List<DatastoreVersion> versions = osclient.database().datastores().listDatastoreVersions(DatastoreType.MySQL);
		datastoreVersion = versions.get(0);
		
		// 从已有的实例中 查找名字以 SDK 开头（SDK创建的用于测试的实例），并且必须是 主实例，数据库类型是 MySQL
		List<DatabaseInstance> instances = osclient.database().instances().list();
		for (DatabaseInstance instance : instances) {
			String name = instance.getName();
			String status = instance.getStatus();
			InstanceType type = instance.getType();
			DatastoreType datastoreType = instance.getDatastore().getType();
			if (name.startsWith("SDK") && status.equals("ACTIVE") && type.equals(InstanceType.Master)
					&& datastoreType.equals(DatastoreType.MySQL)) {
				this.instance = instance;
				break;
			}
		}
	}

	@Test
	public void testListDatabaseParams() {
		params = osclient.database().params().list(datastoreVersion.getId());
		Assert.assertTrue(params.size() >= 1);
	}

	@Test(dependsOnMethods = { "testListDatabaseParams" })
	public void testGetDatabaseParam() {
		DatabaseParam databaseParam = params.get(0);
		DatabaseParam get = osclient.database().params().get(datastoreVersion.getId(), databaseParam.getName());
		Assert.assertEquals(get.getDatastoreVersionId(), databaseParam.getDatastoreVersionId());
		Assert.assertEquals(get.getDescription(), databaseParam.getDescription());
		Assert.assertEquals(get.getName(), databaseParam.getName());
		Assert.assertEquals(get.getValueRange(), databaseParam.getValueRange());
		Assert.assertEquals(get.getMax(), databaseParam.getMax());
		Assert.assertEquals(get.getMin(), databaseParam.getMin());
		Assert.assertEquals(get.getRestartRequired(), databaseParam.getRestartRequired());
		Assert.assertEquals(get.getType(), databaseParam.getType());
	}

	@Test
	public void testConfigDatabaseParams() {
		Assert.assertNotNull(this.instance);
		Map<String, Object> params = Maps.newHashMap();
		params.put("connect_timeout", 100);
		InstanceParamOperationResult result = osclient.database().params().config(instance.getId(), params);
		Assert.assertFalse(result.getShouldRestart());
		Assert.assertEquals(result.getResult(), OperateInstanceParamResult.SUCCESS);
	}
	
	@Test
	public void testRestoreDatabaseParams() {
		Assert.assertNotNull(this.instance);
		InstanceParamOperationResult result = osclient.database().params().restore(instance.getId());
		Assert.assertTrue(result.getShouldRestart());
		Assert.assertEquals(result.getResult(), OperateInstanceParamResult.SUCCESS);
	}

}
