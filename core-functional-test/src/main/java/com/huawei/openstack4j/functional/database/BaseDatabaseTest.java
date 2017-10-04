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

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;

public abstract class BaseDatabaseTest extends AbstractTest {

	private DatastoreVersion datastoreVersion = null;
	private InstanceFlavor flavor;

	public synchronized DatastoreVersion getFirstDatastoreVersion(DatastoreType type) {
		if (datastoreVersion == null) {
			List<DatastoreVersion> datastoreVersions = osclient.database().datastores().listDatastoreVersions(type);
			Assert.assertTrue(datastoreVersions.size() > 0, "no datastore for mysql is available for testing");
			datastoreVersion = datastoreVersions.get(0);
		}
		return datastoreVersion;
	}

	public synchronized InstanceFlavor getFirstFlavor(String databaseId) {
		if (flavor == null) {
			List<InstanceFlavor> flavors = osclient.database().flavors().list(databaseId, "eu-de");
			Assert.assertTrue(flavors.size() > 0, String.format("no flavor for database [%s] available", databaseId));
			flavor = flavors.get(0);
		}
		return flavor;
	}

}
