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
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Stats;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2StatusTree;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 *
 * @author ashleykasim
 *
 */
@Test(suiteName="Network/loadBalancerV2", enabled = true)
public class LoadBalancerV2Tests extends AbstractTest {
    private static final String LOADBALANCERSV2_JSON = "/network/loadbalancersv2.json";
    private static final String LOADBALANCERV2_JSON = "/network/loadbalancerv2.json";
    private static final String LOADBALANCERV2_UPDATE_JSON = "/network/loadbalancerv2_update.json";
    private static final String LOADBALANCERV2_STATS_JSON = "/network/loadbalancerv2_stats.json";
    private static final String LOADBALANCERV2_STATUSES_JSON = "/network/loadbalancerv2_statuses.json";

    public void testListLoadBalancersV2() throws IOException {
        respondWith(LOADBALANCERSV2_JSON);
        List<? extends LoadBalancerV2> list = osv3().networking().lbaasV2().loadbalancer().list();
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).getName(), "lb1");
    }

    public void testListLoadBalancersV2Filter() throws IOException {
        respondWith(LOADBALANCERSV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("provider", "octavia");
        List<? extends LoadBalancerV2> list = osv3().networking().lbaasV2().loadbalancer().list(map);
        assertEquals(list.size(), 3);
    }

    public void testGetLoadBalancerV2() throws IOException {
        respondWith(LOADBALANCERV2_JSON);
        String id = "282b71ea-9ceb-4cd6-8881-cb511af2edb5";
        LoadBalancerV2 lb = osv3().networking().lbaasV2().loadbalancer().get(id);
        assertNotNull(lb);
        assertNotNull(lb.getVipPortId());
        assertEquals(lb.getId(), id);
    }

    public void testCreateLoadBalancerV2() throws IOException {
        respondWith(LOADBALANCERV2_JSON);
        String name = "lb1";
        String description = "im a baby lb";
        String address = "10.0.0.13";
        String subnetId = "388c5684-86b0-49ab-90ef-944b1f7328f8";
        LoadBalancerV2 create = Builders.loadbalancerV2()
                .adminStateUp(false)
                .name(name)
                .description(description)
                .address(address)
                .subnetId(subnetId)
                .build();
        LoadBalancerV2 result = osv3().networking().lbaasV2().loadbalancer().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getVipAddress(), address);
        assertEquals(result.getVipSubnetId(), subnetId);
        assertFalse(result.isAdminStateUp());
    }

    public void testUpdateLoadBalancerV2() throws IOException {
        respondWith(LOADBALANCERV2_UPDATE_JSON);
        String name = "lb_updated";
        String description = "im no longer a baby lb";
        LoadBalancerV2Update update = Builders.loadBalancerV2Update()
                .adminStateUp(true)
                .description(description)
                .name(name)
                .build();
        LoadBalancerV2 result = osv3().networking().lbaasV2().loadbalancer().update("282b71ea-9ceb-4cd6-8881-cb511af2edb5", update);
        assertTrue(result.isAdminStateUp());
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
    }

    public void testDeleteLoadbalancerV2() {
        respondWith(204);
        ActionResponse result = osv3().networking().lbaasV2().loadbalancer().delete("282b71ea-9ceb-4cd6-8881-cb511af2edb5");
        assertTrue(result.isSuccess());
    }

    public void testGetLoadBalancerV2Stats() throws IOException {
        respondWith(LOADBALANCERV2_STATS_JSON);
        String id = "d8b09924-d223-42a8-b7e7-410e60fd04c5";
        LoadBalancerV2Stats stats = osv3().networking().lbaasV2().loadbalancer().stats(id);
        assertNotNull(stats);
    }

    public void testGetLoadBalancerV2Statuses() throws IOException {
        respondWith(LOADBALANCERV2_STATUSES_JSON);
        String id = "d8b09924-d223-42a8-b7e7-410e60fd04c5";
        LoadBalancerV2StatusTree statuses = osv3().networking().lbaasV2().loadbalancer().statusTree(id);
        assertNotNull(statuses);
        assertNotNull(statuses.getLoadBalancerV2Status());
        assertNotNull(statuses.getLoadBalancerV2Status().getListenerStatuses());
        assertEquals(statuses.getLoadBalancerV2Status().getId(), id);
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
