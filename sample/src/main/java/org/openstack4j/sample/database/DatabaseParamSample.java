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

import org.openstack4j.openstack.database.domain.DatabaseParam;
import org.openstack4j.openstack.trove.constant.DatastoreType;
import org.openstack4j.openstack.trove.domain.DatastoreVersion;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(suiteName = "Database/Version/Sample")
public class DatabaseParamSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseParamSample.class);

	DatastoreVersion datastoreVersion = null;
	
	@BeforeClass
	public void prepare() {
		// get the first datastore version of MySQL for test
		List<DatastoreVersion> versions = osclient.database().datastores().listDatastoreVersions(DatastoreType.MySQL);
		datastoreVersion = versions.get(0);
	}

	@Test
	public void testListDatabaseParams() {
		List<DatabaseParam> params = osclient.database().params().list(datastoreVersion.getId());
		logger.info("versions: {}", params);
		Assert.assertTrue(params.size() >= 1);
	}

	@Test(dependsOnMethods = { "testListDatabaseParams" })
	public void testGetVersion() {
	}

}
