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

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.network.NetQuota;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 */
@Test(suiteName = "Network/quota", enabled = true)
public class NetQuotaTest extends AbstractTest {
    private static final String QUOTA_JSON = "/network/quota.json";

    public void testListQuota() throws IOException {
        respondWith(QUOTA_JSON);
        NetQuota netQuota = osv3().networking().quotas().get("tenant-id");
        assertEquals(10, netQuota.getSubnet());
        assertEquals(11, netQuota.getRouter());
        assertEquals(12, netQuota.getPort());
        assertEquals(13, netQuota.getNetwork());
        assertEquals(14, netQuota.getFloatingIP());
        assertEquals(15, netQuota.getSecurityGroup());
        assertEquals(16, netQuota.getSecurityGroupRule());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
