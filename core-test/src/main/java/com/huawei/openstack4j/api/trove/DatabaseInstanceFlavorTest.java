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
package com.huawei.openstack4j.api.trove;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.trove.domain.InstanceFlavor;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Trove/InstanceFlavor", enabled = true)
public class DatabaseInstanceFlavorTest extends AbstractTest {

	@Test
	public void testListFlavor() throws IOException, InterruptedException {
		respondWith("/trove/list_flavor_response.json");
		List<InstanceFlavor> flavors = osv3().trove().flavors().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/flavors");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(flavors.size(), 2);

		InstanceFlavor flavor = flavors.get(0);
		Assert.assertEquals(flavor.getId(), "1");
		Assert.assertEquals(flavor.getName(), "rds.pg.c2.medium");
		Assert.assertEquals(flavor.getRam().intValue(), 2048);
		Assert.assertEquals(flavor.getStrId(), "9ff2a3a5-c859-bbc0-67f7-86ce59432b1d" );
		Assert.assertEquals(flavor.getLinks().size(), 2);
		Assert.assertEquals(flavor.getLinks().get(0).getRel(), "self");
		Assert.assertEquals(flavor.getLinks().get(0).getHref(), "");
		Assert.assertEquals(flavor.getLinks().get(1).getRel(), "bookmark");
		Assert.assertEquals(flavor.getLinks().get(1).getHref(), "");
		
	}
	
	@Test
	public void testGetFlavor() throws IOException, InterruptedException {
		respondWith("/trove/get_flavor_response.json");
		InstanceFlavor flavor = osv3().trove().flavors().get("flavor-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/flavors/flavor-id");
		Assert.assertEquals(request.getMethod(), "GET");


		Assert.assertEquals(flavor.getId(), "1");
		Assert.assertEquals(flavor.getName(), "rds.pg.c2.medium");
		Assert.assertEquals(flavor.getRam().intValue(), 2);
		Assert.assertEquals(flavor.getStrId(), "9ff2a3a5-c859-bbc0-67f7-86ce59432b1d" );
		Assert.assertEquals(flavor.getLinks(), null);
		Assert.assertEquals(flavor.getFlavorDetail().size(), 3);
		Assert.assertEquals(flavor.getFlavorDetail().get(0).getName(), "cpu");
		Assert.assertEquals(flavor.getFlavorDetail().get(0).getValue(), "1");
		Assert.assertEquals(flavor.getFlavorDetail().get(1).getName(), "mem");
		Assert.assertEquals(flavor.getFlavorDetail().get(1).getValue(), "2");
		Assert.assertEquals(flavor.getFlavorDetail().get(2).getName(), "flavor");
		Assert.assertEquals(flavor.getFlavorDetail().get(2).getValue(), "normal1");
		Assert.assertEquals(flavor.getPriceDetail().size(), 0);
		
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
