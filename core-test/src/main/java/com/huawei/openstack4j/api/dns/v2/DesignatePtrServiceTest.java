/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.dns.v2;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.dns.v2.PTR;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignatePtrServiceTest extends AbstractTest {

	private static final String JSON_PTR_LIST = "/dns/v2/list_ptrs.json";
	private static final String JSON_PTR_SETUP = "/dns/v2/setup_ptr.json";
	private static final String PTRDNAME = "www.example.com";
	private static final String DESCRIPTION = "Description for this PTR record";
	private static final String REGION = "eu-de";
	private static final String FLOATING_IP_ID = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
	private static final int TTL = 300;
	public static final String ADDRESS = "160.44.201.194";

	@Override
	protected Service service() {
		return Service.DNS;
	}

	public void ptrListWithoutFilterTest() throws Exception {
		respondWith(JSON_PTR_LIST);
		List<? extends PTR> list = osv3().dns().ptrs().list();
		assertNotNull(list);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getAddress(), ADDRESS);
	}

	public void ptrListWithFilterTest() throws Exception {
		respondWith(JSON_PTR_LIST);
		Map<String, Object> filters = new HashMap<>();
		filters.put("limit", "2");
		List<? extends PTR> list = osv3().dns().ptrs().list(filters);
		assertNotNull(list);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getAddress(), ADDRESS);
	}

	public void setupPTRSuccessTest() throws Exception {
		respondWith(JSON_PTR_SETUP);

		DesignatePTR.DesignatePTRBuilder builder = Builders.ptr().ptrdname(PTRDNAME).description(DESCRIPTION)
				.region(REGION).floatingIpId(FLOATING_IP_ID).ttl(TTL);
		DesignatePTR ptrRecord = builder.build();
		PTR ptr = osv3().dns().ptrs().setup(ptrRecord);
		assertNotNull(ptr);
		assertEquals(ptr.getAddress(), ADDRESS);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void setupPTRFailedTest() throws Exception {
		int ttl = 299;
		DesignatePTR.DesignatePTRBuilder builder = Builders.ptr().ptrdname(PTRDNAME).description(DESCRIPTION)
				.region(REGION).floatingIpId(FLOATING_IP_ID).ttl(ttl);
		DesignatePTR ptrRecord = builder.build();
		osv3().dns().ptrs().setup(ptrRecord);
	}

	public void getPTRTest() throws Exception {
		respondWith(JSON_PTR_SETUP);

		PTR ptr = osv3().dns().ptrs().get(REGION, FLOATING_IP_ID);
		assertNotNull(ptr);
		assertEquals(ptr.getAddress(), ADDRESS);
	}

	public void restoreSuccessPTRTest() throws Exception {
		respondWith(202);

		ActionResponse ptrRestoreActionResponse = osv3().dns().ptrs().restore(REGION, FLOATING_IP_ID);
		assertTrue(ptrRestoreActionResponse.isSuccess());
	}

}
