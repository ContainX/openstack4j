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
package org.openstack4j.api.loadbalance;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.loadbalance.Quotas;
import org.testng.annotations.Test;

@Test(suiteName = "ELBLoadBalancer/QuotaV1")
public class ELBQuotaV1Tests extends AbstractTest {

	private static final String JSON_QUOTAS = "/loadbalance/elb_quotas.json";

	public void testListQuotas() throws IOException {
		respondWith(JSON_QUOTAS);
		Quotas quotas = osv3().elasticLoadBalance().quotas().list();
		assertTrue(quotas.getResources().size() == 2);
	}
	
	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}
