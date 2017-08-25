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
package com.huawei.openstack4j.api.loadbalance;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.common.Quota;

@Test(suiteName = "ELBLoadBalancer/QuotaV1")
public class ELBQuotaV1Tests extends AbstractTest {

	private static final String JSON_QUOTAS = "/loadbalance/elb_quotas.json";

	public void testListQuotas() throws IOException {
		respondWith(JSON_QUOTAS);
		List<Quota> quotas = osv3().loadBalancer().quotas().list();
		assertTrue(quotas.size() == 2);
	}

	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}
