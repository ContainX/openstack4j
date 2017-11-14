/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.functional.mrs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.DataSource;
import com.huawei.openstack4j.model.map.reduce.DataSource.DataSourceType;
import com.huawei.openstack4j.model.map.reduce.options.DataSourceListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSource;

public class DataSourceTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

	String name = randomName();
	DataSource createdDataSource;

	@BeforeClass
	public void testCreateDataSource() {

		DataSource build = MapReduceDataSource.builder().name(name).url("/sdk/unittest/input").type(DataSourceType.HDFS)
				.isProtect(true).isPublic(false).description("sdk unittests").build();
		createdDataSource = osclient.mrs().dataSources().create(build);
		Assert.assertEquals(createdDataSource.getName(), name);
		Assert.assertEquals(createdDataSource.getURL(), "/sdk/unittest/input");
		Assert.assertEquals(createdDataSource.getType(), DataSourceType.HDFS);
		Assert.assertTrue(createdDataSource.isProtected());
		Assert.assertFalse(createdDataSource.isPublic());
	}

	@AfterClass
	public void testDeleteDataSource() {
		ActionResponse delete = osclient.mrs().dataSources().delete(createdDataSource.getId());
		Assert.assertTrue(delete.isSuccess());
	}

	@Test
	public void testGetDataSource() {
		DataSource dataSource = osclient.mrs().dataSources().get(createdDataSource.getId());
		Assert.assertEquals(dataSource.getId(), createdDataSource.getId());
		Assert.assertEquals(dataSource.getName(), name);
		Assert.assertEquals(dataSource.getURL(), "/sdk/unittest/input");
		Assert.assertEquals(dataSource.getType(), DataSourceType.HDFS);
		Assert.assertTrue(dataSource.isProtected());
		Assert.assertFalse(dataSource.isPublic());
	}

	@Test(dependsOnMethods = { "testGetDataSource" })
	public void testUpdateDataSource() {
		DataSource build = MapReduceDataSource.builder().id(createdDataSource.getId()).name("sdk-new-name").type(DataSourceType.HDFS)
				.url("/sdk/unittest/input2").build();
		createdDataSource = osclient.mrs().dataSources().update(build);
		Assert.assertEquals(createdDataSource.getURL(), "/sdk/unittest/input2");
		Assert.assertEquals(createdDataSource.getName(), "sdk-new-name");
	}

	@Test(dependsOnMethods = { "testUpdateDataSource" })
	public void testListDataSource() {
		DataSourceListOptions options = DataSourceListOptions.create().desc("created_at").limit(10);
		List<? extends DataSource> list = osclient.mrs().dataSources().list(options);
		boolean found = false;
		for (DataSource dataSource : list) {
			if (dataSource.getId().equals(createdDataSource.getId())) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);
	}

}
