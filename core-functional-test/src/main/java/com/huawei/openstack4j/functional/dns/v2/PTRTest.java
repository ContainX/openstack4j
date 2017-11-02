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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.dns.v2.PTR;
import com.huawei.openstack4j.model.network.NetFloatingIP;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR.DesignatePTRBuilder;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-12 10:45:22
 */
public class PTRTest extends AbstractTest {

	private static final String REGION = "eu-de";
	private static final String PTRDNAME = "sdk.%s.unittest.com.";

	DesignatePTR ptr = null;
	NetFloatingIP eip = null;

	@BeforeClass
	public void testSetupPTR() {
		Map<String, String> filter = Maps.newHashMap();
		filter.put("status", "ACTIVE");
		eip = osclient.networking().floatingip().list(filter).get(0);

		String ptrdname = String.format(PTRDNAME, randomName());
		DesignatePTRBuilder builder = DesignatePTR.builder().ptrdname(ptrdname).description("sdk unittest")
				.region(REGION).floatingIpId(eip.getId()).ttl(600);
		DesignatePTR ptrRecord = builder.build();

		ptr = osclient.dns().ptrs().setup(ptrRecord);

		Assert.assertEquals(ptr.getDescription(), "sdk unittest");
		Assert.assertEquals(ptr.getPtrdname(), ptrdname);
		Assert.assertEquals(ptr.getTtl().intValue(), 600);
	}

	@AfterClass
	public void testRestorePTR() {
		ActionResponse response = osclient.dns().ptrs().restore(REGION, eip.getId());
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void getPTR() {
		DesignatePTR get = osclient.dns().ptrs().get(REGION, eip.getId());
		Assert.assertEquals(get.getId(), ptr.getId());
		Assert.assertEquals(get.getDescription(), ptr.getDescription());
		Assert.assertEquals(get.getPtrdname(), ptr.getPtrdname());
		Assert.assertEquals(get.getTtl(), ptr.getTtl());
		Assert.assertNotNull(get.getAddress());
	}

	@Test
	public void listPTR() {
		List<? extends PTR> list = osclient.dns().ptrs().list();
		List<String> collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(ptr.getId()));
	}

	@Test
	public void listWithFilterPTR() {
		Map<String, Object> filters = new HashMap<>();
		filters.put("limit", "50");
		List<? extends PTR> list = osclient.dns().ptrs().list(filters);
		List<String> collect = list.stream().map(ptr -> ptr.getId()).collect(Collectors.toList());
		Assert.assertTrue(collect.contains(ptr.getId()));
	}

}
