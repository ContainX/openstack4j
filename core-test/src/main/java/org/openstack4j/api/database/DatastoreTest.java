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
package org.openstack4j.api.database;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.database.constants.DatastoreType;
import org.openstack4j.openstack.database.domain.DatastoreVersion;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/datastore", enabled = true)
public class DatastoreTest extends AbstractTest {

	@Test
	public void testListDatastoreVersion() throws IOException, InterruptedException {
		respondWith("/database/list_datastore_version_response.json");
		List<DatastoreVersion> versions = osv3().database().datastores().listDatastoreVersions(DatastoreType.MySQL);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/datastores/MySQL/versions");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(versions.size(), 5);
		
		DatastoreVersion version = versions.get(0);
		Assert.assertEquals(version.getId(), "e8a8b8cc-63f8-4fb5-8d4a-24c502317a62");
		Assert.assertEquals(version.getName(), "5.6.33");
		Assert.assertEquals(version.getDatastoreId(), "736270b9-27c7-4f03-823b-447d8245e1c2");
		Assert.assertEquals(version.getImage(), "988f7639-4bc9-4225-bdfe-f47bcde1a5f2");
		Assert.assertEquals(version.getPackages(), "MySQL-server-5.6.33");
		Assert.assertTrue(version.getIsActive());
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
