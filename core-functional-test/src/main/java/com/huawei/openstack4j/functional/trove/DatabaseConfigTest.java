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
package com.huawei.openstack4j.functional.trove;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.trove.constant.DatastoreType;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfig;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigParam;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigUpdate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.trove.domain.Datastore;

@Test(suiteName = "Trove/Config/Test")
public class DatabaseConfigTest extends AbstractTest {

	DatabaseConfig create;
	List<DatabaseConfig> configs = null;
	String name = randomName();

	@BeforeClass
	public void prepare() {
		Map<String, Object> values = Maps.newHashMap();
		values.put("max_connections", "10");
		values.put("autocommit", "OFF");
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("5.6").build();
		DatabaseConfigCreate creation = DatabaseConfigCreate.builder().datastore(datastore).name(name)
				.description("openstack4j sdk unittest").values(values).build();
		create = osclient.trove().configs().create(creation);
		Assert.assertEquals(create.getName(), name);
	}

	@AfterClass
	public void cleanup() {
		// delete resource we created
		osclient.trove().configs().delete(create.getId());
	}

	@Test
	public void testListConfigs() {
		configs = osclient.trove().configs().list();
		Assert.assertTrue(configs.size() > 0);

		List<String> configIds = configs.stream().map(config -> config.getId()).collect(Collectors.toList());
		Assert.assertTrue(configIds.contains(create.getId()));
	}

	@Test
	public void testGetConfig() {
		DatabaseConfig get = osclient.trove().configs().get(create.getId());
		Assert.assertEquals(create.getId(), get.getId());
		Assert.assertEquals(create.getName(), name);
		Assert.assertEquals(create.getDescription(), "openstack4j sdk unittest");
		Assert.assertEquals(create.getDatastoreVersionId(), get.getDatastoreVersionId());
		Assert.assertEquals(create.getDatastoreVersionName(), get.getDatastoreVersionName());
		Assert.assertEquals(create.getDatastoreName(), "MySQL");
		Assert.assertEquals(create.getCreated(), get.getCreated());
		Assert.assertEquals(create.getUpdated(), get.getUpdated());
		Assert.assertEquals(create.getInstanceCount(), get.getInstanceCount());

		Assert.assertFalse(get.getValues().isEmpty());
		Assert.assertFalse(get.getParameters().isEmpty());

		// assert the values we setup
		Assert.assertEquals(get.getValues().get("max_connections"), "10");
		Assert.assertEquals(get.getValues().get("autocommit"), "OFF");

		// assert parameter values
		List<DatabaseConfigParam> parameters = get.getParameters();
		for (DatabaseConfigParam param : parameters) {
			String name = param.getName();
			String value = param.getValue();
			Assert.assertEquals(get.getValues().get(name), value);
		}
	}

	@Test(dependsOnMethods = { "testGetConfig" })
	public void testUpdateConfig() {
		Map<String, String> values = Maps.newHashMap();
		values.put("max_connections", "100");
		values.put("autocommit", "ON");

		DatabaseConfigUpdate update = DatabaseConfigUpdate.builder().id(create.getId())
				.name("sdk-unittest-updated-name").description("openstack4j sdk unittest2").values(values).build();
		ActionResponse response = osclient.trove().configs().update(update);
		Assert.assertTrue(response.isSuccess());

		DatabaseConfig updated = osclient.trove().configs().get(create.getId());

		Assert.assertEquals(updated.getName(), "sdk-unittest-updated-name");
		Assert.assertEquals(updated.getDescription(), "openstack4j sdk unittest2");

		// assert the values we setup
		Assert.assertEquals(updated.getValues().get("max_connections"), "100");
		Assert.assertEquals(updated.getValues().get("autocommit"), "ON");
	}
	
	@Test(dependsOnMethods = { "testUpdateConfig" })
	public void testAddParams() {
		Map<String, String> params = Maps.newHashMap();
		params.put("max_connections", "20");
		params.put("autocommit", "OFF");

		ActionResponse response = osclient.trove().configs().updateParams(create.getId(), params);
		Assert.assertTrue(response.isSuccess());

		DatabaseConfig updated = osclient.trove().configs().get(create.getId());

		Assert.assertEquals(updated.getName(), "sdk-unittest-updated-name");
		Assert.assertEquals(updated.getDescription(), "openstack4j sdk unittest2");

		// assert the values we setup
		Assert.assertEquals(updated.getValues().get("max_connections"), "20");
		Assert.assertEquals(updated.getValues().get("autocommit"), "OFF");
	}


	@Test
	public void testListInstances() {
		for (DatabaseConfig databaseConfig : configs) {
			if (databaseConfig.getInstanceCount() > 0) {
				List<DatabaseInstance> instances = osclient.trove().configs()
						.listDatabaseInstances(databaseConfig.getId());
				for (DatabaseInstance instance : instances) {
					Assert.assertNotNull(instance.getId());
					Assert.assertNotNull(instance.getName());
				}
				break;
			}
		}
	}
}
