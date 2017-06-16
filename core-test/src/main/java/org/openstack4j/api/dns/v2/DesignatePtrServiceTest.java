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
package org.openstack4j.api.dns.v2;

import static org.testng.Assert.*;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.dns.v2.PTR;
import org.testng.annotations.Test;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignatePtrServiceTest extends AbstractTest {

	private static final String JSON_PTR_LIST = "/dns/v2/list_ptrs.json";

	@Override
	protected Service service() {
		return Service.DNS;
	}

	public void zoneCreateTest() throws Exception {
		respondWith(JSON_PTR_LIST);
		List<? extends PTR> list = osv3().dns().ptrs().list();
		assertNotNull(list);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getAddress(), "160.44.201.194");
	}

}
