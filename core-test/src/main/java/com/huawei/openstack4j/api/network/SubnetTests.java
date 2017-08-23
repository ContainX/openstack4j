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
package com.huawei.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.network.Ipv6AddressMode;
import com.huawei.openstack4j.model.network.Ipv6RaMode;
import com.huawei.openstack4j.model.network.Subnet;

/**
 * Tests the Neutron -> Subnet API against the mock webserver and spec based
 * json responses
 * 
 * @author Taemin 
 */
@Test(suiteName = "subnet")
public class SubnetTests extends AbstractTest {

	private static final String JSON_GET_SUBNET = "/network/subnet_ipv6.json";  
	
	private static final String SUBNET_NAME = "sub1";
	private static final String SUBNET_ID = "3b80198d-4f7b-4f77-9ef5-774d54e17126";	
	
	@Test
	public void getSubnetIpV6() throws Exception {		
		respondWith(JSON_GET_SUBNET);		 
		Subnet n = osv3().networking().subnet().get(SUBNET_ID);
		server.takeRequest();
		assertEquals(n.getName(), SUBNET_NAME);
		assertEquals(n.getIpv6AddressMode(), Ipv6AddressMode.DHCPV6_STATEFUL);
		assertEquals(n.getIpv6RaMode(), Ipv6RaMode.DHCPV6_STATEFUL);
	}

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    
}
