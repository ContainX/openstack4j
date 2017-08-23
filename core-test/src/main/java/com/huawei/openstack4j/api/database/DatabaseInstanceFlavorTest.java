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
package com.huawei.openstack4j.api.database;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/InstanceFlavor", enabled = true)
public class DatabaseInstanceFlavorTest extends AbstractTest {

	@Test
	public void testListFlavor() throws IOException, InterruptedException {
		respondWith("/database/list_flavor_response.json");
		List<InstanceFlavor> flavors = osv3().database().flavors().list("databaseId", "region");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/flavors?region=region&dbId=databaseId");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(flavors.size(), 2);

		InstanceFlavor flavor = flavors.get(0);
		Assert.assertEquals(flavor.getId(), "bf07a6d4-844a-4023-a776-fc5c5fb71fb4");
		Assert.assertEquals(flavor.getName(), "OTC_MYHP_LARGE");
		Assert.assertEquals(flavor.getRam().intValue(), 4096);
		Assert.assertEquals(flavor.getSpecCode(), "rds.mysql.c2.large");
	}
	
	@Test
	public void testGetFlavor() throws IOException, InterruptedException {
		respondWith("/database/get_flavor_response.json");
		InstanceFlavor flavor = osv3().database().flavors().get("flavor-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/flavors/flavor-id");
		Assert.assertEquals(request.getMethod(), "GET");


		Assert.assertEquals(flavor.getId(), "f7f51f85-cfcd-4409-ae91-b3eae186d0ea");
		Assert.assertEquals(flavor.getName(), "OTC_MYLM_XLARGE");
		Assert.assertEquals(flavor.getRam().intValue(), 32768);
		Assert.assertEquals(flavor.getSpecCode(), "rds.mysql.m1.xlarge");
		
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
