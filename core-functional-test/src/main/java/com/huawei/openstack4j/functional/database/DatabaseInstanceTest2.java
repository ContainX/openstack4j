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
import org.testng.annotations.Test;

import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;

/**
 * 数据库实例的操作，都是耗时操作（有的需要好几十分钟），
 * 而且这些操作都要求实例处于某个状态中，所以测试用例要自己手动一个个去跑。
 *
 * @author QianBiao.NG
 * @date   2017-08-08 13:52:22
 */
@Test(suiteName = "Database/Instance/Test2")
public class DatabaseInstanceTest2 extends BaseDatabaseTest {

	// ====================================================================//
	// 数据库实例的操作，都是耗时操作（有的需要好几十分钟）， //
	// 而且这些操作都要求实例处于某个状态中，所以测试用例要自己手动一个个去跑。 //
	// ====================================================================//

	@Test
	public void testResizeVolume() {
		// 需要改成实际的 instance id
		String instanceId = "bc8510b5-06bd-413d-9988-f8fec3d540cd";
		List<String> jobIds = osclient.database().instances().resize(instanceId, 120);
		Assert.assertTrue(jobIds.size() > 0);

		// 该实例有一个HA副本，所以会生成两个任务
		Assert.assertTrue(jobIds.size() == 2);
	}

	@Test
	public void testResizeFlavor() {
		// 需要改成实际的 instance id
		String instanceId = "bc8510b5-06bd-413d-9988-f8fec3d540cd";

		DatastoreVersion datastoreVersion = this.getFirstDatastoreVersion(DatastoreType.MySQL);
		List<InstanceFlavor> flavors = osclient.database().flavors().list(datastoreVersion.getId(), "eu-de");

		InstanceFlavor newFlavor = null;
		DatabaseInstance databaseInstance = osclient.database().instances().get(instanceId);
		String flavorId = databaseInstance.getFlavor().getId();
		for (InstanceFlavor flavor : flavors) {
			if (!flavor.getId().equals(flavorId)) {
				newFlavor = flavor;
			}
		}

		List<String> jobIds = osclient.database().instances().resize(instanceId, newFlavor.getId());
		Assert.assertTrue(jobIds.size() > 0);
	}

	@Test
	public void testRestartInstance() {
		// 需要改成实际的 instance id
		String instanceId = "b27be694-cbe0-40d8-8320-fa09f462cec3";
		List<String> jobIds = osclient.database().instances().restart(instanceId);
		Assert.assertTrue(jobIds.size() > 0);
	}

	@Test
	public void testDeleteInstance() {
		// 需要改成实际的 instance id
		String instanceId = "4538765a-dd22-46f7-a59c-0065993fcf93";
		String jobId = osclient.database().instances().delete(instanceId);
		Assert.assertNotNull(jobId);
	}

}
