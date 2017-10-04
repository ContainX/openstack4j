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
package com.huawei.openstack4j.sample.dns.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.dns.v2.RecordSetType;
import com.huawei.openstack4j.model.dns.v2.Recordset;
import com.huawei.openstack4j.model.dns.v2.Status;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone;
import com.huawei.openstack4j.sample.AbstractSample;
import com.huawei.openstack4j.sample.Retry;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-12 10:45:22
 */
public class RecordsetSample extends AbstractSample {

	private static final String REGION = "eu-de";

	Zone privateZone = null;
	Recordset recordset = null;

	@BeforeClass
	public void testCreateRecordset() {
		// create private zone
		Router vpc = this.getFirstRouter();
		DesignateZone.Router router = new DesignateZone.Router(vpc.getId(), REGION, null);

		String name = String.format("sdk.%s.unittest.com.", randomName()).toLowerCase();
		Zone zone = Builders.zone().name(name).description("For sdk unittest").ttl(600).email("admin@sdk.com")
				.type(ZoneType.PRIVATE).router(router).build();
		privateZone = osclient.dns().zones().create(zone);

		this.retry(new Retry() {

			@Override
			public Integer maxRetryTimes() {
				return 20;
			}

			@Override
			public Integer delay() {
				return 10;
			}

			@Override
			public Object run() {
				Zone get = osclient.dns().zones().get(privateZone.getId());
				if (get.getStatus().equals(Status.ACTIVE)) {
					return zone;
				}
				return null;
			}

		});

		ArrayList<String> records = Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3");
		recordset = osclient.dns().recordsets().create(privateZone.getId(), "sample." + name,
				"For dns recordset sdk unittest", "A", 7200, records);

		Assert.assertEquals(recordset.getName(), "sample." + name);
		Assert.assertEquals(recordset.getDescription(), "For dns recordset sdk unittest");
		Assert.assertEquals(recordset.getType(), RecordSetType.A);
		Assert.assertEquals(recordset.getTtl().intValue(), 7200);
		Assert.assertEquals(recordset.getZoneId(), privateZone.getId());
		Assert.assertEquals(recordset.getRecords().size(), 3);
		Assert.assertTrue(recordset.getRecords().contains("192.168.10.1"));
		Assert.assertTrue(recordset.getRecords().contains("192.168.10.2"));
		Assert.assertTrue(recordset.getRecords().contains("192.168.10.3"));
	}

	@AfterClass
	public void testDeleteRecordset() {
		Recordset deleted = osclient.dns().recordsets().delete(privateZone.getId(), recordset.getId());
		Assert.assertEquals(deleted.getName(), recordset.getName());
		Assert.assertEquals(deleted.getDescription(), "For dns recordset sdk unittest");
		Assert.assertEquals(deleted.getType(), RecordSetType.A);
		Assert.assertEquals(deleted.getTtl().intValue(), 7200);
		Assert.assertEquals(deleted.getZoneId(), privateZone.getId());
		Assert.assertEquals(deleted.getRecords().size(), 3);
		Assert.assertTrue(deleted.getRecords().contains("192.168.10.1"));
		Assert.assertTrue(deleted.getRecords().contains("192.168.10.2"));
		Assert.assertTrue(deleted.getRecords().contains("192.168.10.3"));
		
		osclient.dns().zones().delete(privateZone.getId());
	}

	@Test
	public void listRecordsets() {
		List<? extends Recordset> list = osclient.dns().recordsets().list(privateZone.getId());
		List<String> collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(recordset.getId()));

		list = osclient.dns().recordsets().list(privateZone.getId(), 50, null);
		collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(recordset.getId()));

		list = osclient.dns().recordsets().list();
		collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(recordset.getId()));

		list = osclient.dns().recordsets().list(50, null);
		collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(recordset.getId()));
	}

	@Test
	public void getRecordset() {
		Recordset get = osclient.dns().recordsets().get(privateZone.getId(), recordset.getId());

		Assert.assertEquals(get.getName(), recordset.getName());
		Assert.assertEquals(get.getDescription(), "For dns recordset sdk unittest");
		Assert.assertEquals(get.getType(), RecordSetType.A);
		Assert.assertEquals(get.getTtl().intValue(), 7200);
		Assert.assertEquals(get.getZoneId(), privateZone.getId());
		Assert.assertEquals(get.getRecords().size(), 3);
		Assert.assertTrue(get.getRecords().contains("192.168.10.1"));
		Assert.assertTrue(get.getRecords().contains("192.168.10.2"));
		Assert.assertTrue(get.getRecords().contains("192.168.10.3"));
	}

}
