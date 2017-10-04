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
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;

public class DatabaseInstanceFlavorTest extends BaseDatabaseTest {

	List<InstanceFlavor> flavors = null;

	@Test
	public void testListInstanceFlavors() {
		DatastoreVersion datastoreVersion = this.getFirstDatastoreVersion(DatastoreType.MySQL);
		flavors = osclient.database().flavors().list(datastoreVersion.getId(), "eu-de");
		Assert.assertTrue(flavors.size() > 0);
	}

	@Test(dependsOnMethods = { "testListInstanceFlavors" })
	public void testGetInstanceFlavors() {
		InstanceFlavor flavor = flavors.get(0);
		InstanceFlavor get = osclient.database().flavors().get(flavor.getId());
		Assert.assertEquals(flavor.getId(), get.getId());
		Assert.assertEquals(flavor.getName(), get.getName());
		Assert.assertEquals(flavor.getRam().intValue(), get.getRam().intValue());
		Assert.assertEquals(flavor.getSpecCode(), get.getSpecCode());
	}
}
