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
package org.openstack4j.api.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Member;
import org.openstack4j.model.network.ext.MemberUpdate;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
/**
 * 
 * @author liujunpeng
 *
 */
@Test(suiteName="Network/member", enabled=false)
public class MemberTests extends AbstractTest{
	public void testListMember(){
		List<? extends Member> list = osv3().networking().loadbalancers().member().list();
		System.out.println("test lb member List"+list);
		assertEquals(1, list.size());
	}
	public void testListMemberFilter(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("address", "100.2.12.32");
		List<? extends Member> list = osv3().networking().loadbalancers().member().list(map);
		System.out.println("test lb member List filter"+list);
		assertEquals(1, list.size());
	}
	public void testGetMember(){
		String id = "99a28283-97b7-4210-af5c-b2594628217b";
		Member member = osv3().networking().loadbalancers().member().get(id);
		System.out.println("test get a member"+member);
		assertEquals(id, member.getId());

	}
	
	public void testCreateMember(){
	
		String address = "100.2.12.32";
		String poolId = "60cf0dbf-6137-41b4-9145-24232104ea96";
		Integer weight = 100;
		Member create = Builders.member()
				.address(address)
				.adminStateUp(true)
				.protocolPort(80)
				.weight(weight)
				.poolId(poolId).build();
		Member result = osv3().networking().loadbalancers().member().create(create);
		assertTrue(result.isAdminStateUp());
		assertEquals(weight, result.getWeight());
		assertEquals(address,result.getAddress());
	}
	
	public void testUpdateMember(){
	
		String address = "100.2.12.32";
		String poolId = "db083bf7-c455-4758-b1ad-203cf441a73a";
		String memberId = "5ee6d8b7-79ae-48cc-bc92-31ffee79858a";
		Integer weight = 101;
		MemberUpdate update = Builders.memberUpdate()
				.adminStateUp(true)
				.weight(weight)
				.poolId(poolId).build();
		Member result = osv3().networking().loadbalancers().member().update(memberId, update);
		assertTrue(result.isAdminStateUp());
		assertEquals(weight, result.getWeight());
		assertEquals(address,result.getAddress());
	}
	
	public void testDeleteMember(){
		String id = "99a28283-97b7-4210-af5c-b2594628217b";
		ActionResponse result = osv3().networking().loadbalancers().member().delete(id);
		System.out.println("test delete a member"+result);
		assertTrue(result.isSuccess());

	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
