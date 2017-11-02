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
package com.huawei.openstack4j.functional.loadbalancer;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;

public class ELBQuotaTest extends AbstractTest {

	@Test
	public void testListQuotas() {
		List<Quota> list = osclient.loadBalancer().quotas().list();
		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(list.get(0).getType(), ResourceType.ELB);
		Assert.assertNotNull(list.get(0).getUsed());
		Assert.assertNotNull(list.get(0).getQuota());
		Assert.assertNotNull(list.get(0).getMax());
		Assert.assertNotNull(list.get(0).getMin());
		
		Assert.assertEquals(list.get(1).getType(), ResourceType.LISTENER);
		Assert.assertNotNull(list.get(1).getUsed());
		Assert.assertNotNull(list.get(1).getQuota());
		Assert.assertNotNull(list.get(1).getMax());
		Assert.assertNotNull(list.get(1).getMin());
	}
}
