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

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;

@Test(suiteName = "Database/datastore/Test")
public class DatastoreTest extends AbstractTest {

	@Test
	public void testListDatastoreVersions() {
		List<DatastoreVersion> versions = osclient.database().datastores().listDatastoreVersions(DatastoreType.MySQL);
		Assert.assertTrue(versions.size() > 0);
		
		for (DatastoreVersion version : versions) {
			Assert.assertNotNull(version.getId());
			Assert.assertNotNull(version.getImage());
			Assert.assertNotNull(version.getDatastoreId());
			Assert.assertNotNull(version.getIsActive());
			Assert.assertNotNull(version.getName());
			Assert.assertTrue(version.getPackages().startsWith("MySQL"));
		}
		
	}

}
