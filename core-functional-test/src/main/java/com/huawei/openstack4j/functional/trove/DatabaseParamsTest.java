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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseParam;

@Test(suiteName = "Trove/Config/Test")
public class DatabaseParamsTest extends BaseTroveTest {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseParamsTest.class);

	DatastoreVersion datastoreVersion = null;
	List<DatabaseParam> list = null;

	@BeforeClass
	public void prepare() {
		datastoreVersion = this.getFirstDatastoreVersion(DatastoreType.MySQL);
	}

	@Test
	public void testListParams() {
		list = osclient.trove().params().list(datastoreVersion.getId());
		Assert.assertTrue(list.size() > 0);
		for (DatabaseParam param : list) {
			Assert.assertNotNull(param.getName());
			if (param.getType() == null) {
				Assert.assertNotNull(param.getType());
			}
			Assert.assertNotNull(param.getRestartRequired());
			Assert.assertNotNull(param.getDatastoreVersionId());
		}
	}

	@Test(dependsOnMethods = { "testListParams" })
	public void testGetParams() {
		DatabaseParam param = list.get(0);
		DatabaseParam get = osclient.trove().params().get(datastoreVersion.getId(), param.getName());
		Assert.assertEquals(get.getDatastoreVersionId(), param.getDatastoreVersionId());
		Assert.assertEquals(get.getName(), param.getName());
		Assert.assertEquals(get.getMax(), param.getMax());
		Assert.assertEquals(get.getMin(), param.getMin());
		Assert.assertEquals(get.getRestartRequired(), param.getRestartRequired());
		Assert.assertEquals(get.getType(), param.getType());
	}

	@Test(dependsOnMethods = { "testListParams" })
	public void testGetDefaultParamsByInstance() {
		// 需要替换成实际环境的的 instance-id
		String instanceId = "bc8510b5-06bd-413d-9988-f8fec3d540cd";
		Map<String, String> params = osclient.trove().params().getDefaultParamsByInstance(instanceId);
		Assert.assertNotNull(params);
		Assert.assertEquals(params.get("character_set_connection"), "utf8");
	}

}
