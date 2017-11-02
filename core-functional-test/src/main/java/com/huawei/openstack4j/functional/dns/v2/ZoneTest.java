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
package com.huawei.openstack4j.functional.dns.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.functional.Retry;
import com.huawei.openstack4j.model.dns.v2.Nameserver;
import com.huawei.openstack4j.model.dns.v2.Status;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-12 10:45:22
 */
public class ZoneTest extends AbstractTest {

	private static final String REGION = "eu-de";

	Zone publicZone = null;
	Zone privateZone = null;
	Router router2 = null;

	@BeforeClass
	public void testCreateZones() {
		// create public zone
		Zone zone = Builders.zone().name("sdk.public.unittest.com.").description("For sdk unittest").ttl(600)
				.email("admin@sdk.com").build();
		publicZone = osclient.dns().zones().create(zone);

		Assert.assertEquals(publicZone.getDescription(), "For sdk unittest");
		Assert.assertEquals(publicZone.getName(), "sdk.public.unittest.com.");
		Assert.assertEquals(publicZone.getEmail(), "admin@sdk.com");
		Assert.assertEquals(publicZone.getTTL().intValue(), 600);

		// create private zone
		Router vpc = this.getFirstRouter();
		DesignateZone.Router router = new DesignateZone.Router(vpc.getId(), REGION, null);
		Zone sourceZone = Builders.zone().name("sdk.private.unittest.com.").description("For sdk unittest").ttl(600)
				.email("admin@sdk.com").type(ZoneType.PRIVATE).router(router).build();
		privateZone = osclient.dns().zones().create(sourceZone);
		Assert.assertEquals(privateZone.getDescription(), "For sdk unittest");
		Assert.assertEquals(privateZone.getName(), "sdk.private.unittest.com.");
		Assert.assertEquals(privateZone.getEmail(), "admin@sdk.com");
		Assert.assertEquals(privateZone.getTTL().intValue(), 600);

	}

	@AfterClass
	public void testDeleteZones() {
		Zone zone1 = osclient.dns().zones().delete(publicZone.getId());
		Zone zone2 = osclient.dns().zones().delete(privateZone.getId());

		Assert.assertEquals(zone1.getDescription(), publicZone.getDescription());
		Assert.assertEquals(zone1.getName(), publicZone.getName());
		Assert.assertEquals(zone1.getId(), publicZone.getId());

		Assert.assertEquals(zone2.getDescription(), privateZone.getDescription());
		Assert.assertEquals(zone2.getName(), privateZone.getName());
		Assert.assertEquals(zone2.getId(), privateZone.getId());
	}

	@Test
	public void testGetZone() {
		Zone get = osclient.dns().zones().get(publicZone.getId());
		Assert.assertEquals(get.getDescription(), publicZone.getDescription());
		Assert.assertEquals(get.getName(), publicZone.getName());
		Assert.assertEquals(get.getId(), publicZone.getId());
		Assert.assertEquals(get.getEmail(), publicZone.getEmail());
		Assert.assertEquals(get.getTTL(), publicZone.getTTL());
		Assert.assertEquals(get.getSerial(), publicZone.getSerial());

		Zone get2 = osclient.dns().zones().get(privateZone.getId());
		Assert.assertEquals(get2.getDescription(), privateZone.getDescription());
		Assert.assertEquals(get2.getName(), privateZone.getName());
		Assert.assertEquals(get2.getId(), privateZone.getId());
		Assert.assertEquals(get2.getEmail(), privateZone.getEmail());
		Assert.assertEquals(get2.getTTL(), privateZone.getTTL());
		Assert.assertEquals(get2.getSerial(), privateZone.getSerial());
		Assert.assertEquals(get2.getRouters().size(), 1);
		Assert.assertEquals(get2.getRouters().get(0).getId(), this.getFirstRouter().getId());
		Assert.assertEquals(get2.getRouters().get(0).getRegion(), REGION);
	}

	@Test
	public void testListZones() {
		List<? extends Zone> list = osclient.dns().zones().list();
		List<String> collect = list.stream().map(zone -> zone.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(publicZone.getId()));
		Assert.assertTrue(collect.contains(privateZone.getId()));
	}

	@Test
	public void testListZonesWithParams() {
		List<? extends Zone> list1 = osclient.dns().zones().list(ZoneType.PUBLIC, null, 50);
		List<String> collect = list1.stream().map(zone -> zone.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(publicZone.getId()));

		List<? extends Zone> list2 = osclient.dns().zones().list(ZoneType.PRIVATE, null, 50);
		List<String> collect2 = list2.stream().map(zone -> zone.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect2.contains(privateZone.getId()));
	}

	@Test
	public void testListNameServers() {
		List<? extends Nameserver> listNameservers = osclient.dns().zones().listNameservers(publicZone.getId());
		Assert.assertTrue(listNameservers.size() > 0);

		List<? extends Nameserver> listNameservers2 = osclient.dns().zones().listNameservers(privateZone.getId());
		Assert.assertTrue(listNameservers2.size() > 0);
	}

	@Test(dependsOnMethods = { "testGetZone" })
	public void testAssociateRouter() {
		router2 = osclient.networking().router().list().get(1);
		DesignateZone.Router router = new DesignateZone.Router(router2.getId(), REGION, null);
		DesignateZone.Router result = osclient.dns().zones().associateRouter(privateZone.getId(), router);

		Assert.assertEquals(result.getId(), router2.getId());
		Assert.assertEquals(result.getRegion(), REGION);
	}

	@Test(dependsOnMethods = { "testAssociateRouter" })
	public void testDisassociateRouter() {

		this.retry(new Retry() {

			@Override
			public Integer maxRetryTimes() {
				return 10;
			}

			@Override
			public Integer delay() {
				return 5;
			}

			@Override
			public Object run() {
				Zone zone = osclient.dns().zones().get(privateZone.getId());
				if (zone.getStatus().equals(Status.ACTIVE)) {
					return zone;
				}
				
				return null;
			}
		});

		DesignateZone.Router router = new DesignateZone.Router(this.getFirstRouter().getId(), REGION, null);
		DesignateZone.Router result = osclient.dns().zones().disassociateRouter(privateZone.getId(), router);
		Assert.assertEquals(result.getId(), this.getFirstRouter().getId());
		Assert.assertEquals(result.getRegion(), REGION);
	}

}
