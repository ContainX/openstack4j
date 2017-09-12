package org.openstack4j.api.octavia;


import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.LoadBalancerV2;
import org.openstack4j.model.octavia.LoadBalancerV2Stats;
import org.openstack4j.model.octavia.LoadBalancerV2StatusTree;
import org.openstack4j.model.octavia.LoadBalancerV2Update;
import org.testng.annotations.Test;

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
 * @author wei
 *
 */
@Test(suiteName="Octavia/loadBalancerV2", enabled = true)
public class LoadBalancerV2Tests extends AbstractTest {
    private static final String LOADBALANCERSV2_JSON = "/octavia/loadbalancersv2.json";
    private static final String LOADBALANCERV2_JSON = "/octavia/loadbalancerv2.json";
    private static final String LOADBALANCERV2_UPDATE_JSON = "/octavia/loadbalancerv2_update.json";
    private static final String LOADBALANCERV2_STATS_JSON = "/octavia/loadbalancerv2_stats.json";
    private static final String LOADBALANCERV2_STATUSES_JSON = "/octavia/loadbalancerv2_statuses.json";

    public void testListLoadBalancersV2() throws IOException {
        respondWith(LOADBALANCERSV2_JSON);
        List<? extends LoadBalancerV2> list = osv3().octavia().loadBalancerV2().list();
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).getName(), "lb1");
    }

    public void testListLoadBalancersV2Filter() throws IOException {
        respondWith(LOADBALANCERSV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("provider", "octavia");
        List<? extends LoadBalancerV2> list = osv3().octavia().loadBalancerV2().list(map);
        assertEquals(list.size(), 3);
    }

    public void testGetLoadBalancerV2() throws IOException {
        respondWith(LOADBALANCERV2_JSON);
        String id = "282b71ea-9ceb-4cd6-8881-cb511af2edb5";
        LoadBalancerV2 lb = osv3().octavia().loadBalancerV2().get(id);
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
        String projectId = "6f759d84e3ca496ab77f8c0ffaa0311e";
        LoadBalancerV2 create = Builders.octavia().loadBalancerV2()
                .adminStateUp(false)
                .name(name)
                .description(description)
                .address(address)
                .subnetId(subnetId)
                .projectId(projectId)
                .build();
        LoadBalancerV2 result = osv3().octavia().loadBalancerV2().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getVipAddress(), address);
        assertEquals(result.getVipSubnetId(), subnetId);
        assertFalse(result.isAdminStateUp());
        assertEquals(result.getProjectId(), projectId);
    }

    public void testUpdateLoadBalancerV2() throws IOException {
        respondWith(LOADBALANCERV2_UPDATE_JSON);
        String name = "lb_updated";
        String description = "im no longer a baby lb";
        LoadBalancerV2Update update = Builders.octavia().loadBalancerV2Update()
                .adminStateUp(true)
                .description(description)
                .name(name)
                .build();
        LoadBalancerV2 result = osv3().octavia().loadBalancerV2().update("282b71ea-9ceb-4cd6-8881-cb511af2edb5", update);
        assertTrue(result.isAdminStateUp());
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
    }

    public void testDeleteLoadbalancerV2() {
        respondWith(204);
        ActionResponse result = osv3().octavia().loadBalancerV2().delete("282b71ea-9ceb-4cd6-8881-cb511af2edb5");
        assertTrue(result.isSuccess());
    }

    public void testGetLoadBalancerV2Stats() throws IOException {
        respondWith(LOADBALANCERV2_STATS_JSON);
        String id = "d8b09924-d223-42a8-b7e7-410e60fd04c5";
        LoadBalancerV2Stats stats = osv3().octavia().loadBalancerV2().stats(id);
        assertNotNull(stats);
    }

    public void testGetLoadBalancerV2Statuses() throws IOException {
        respondWith(LOADBALANCERV2_STATUSES_JSON);
        String id = "d8b09924-d223-42a8-b7e7-410e60fd04c5";
        LoadBalancerV2StatusTree statuses = osv3().octavia().loadBalancerV2().statusTree(id);
        assertNotNull(statuses);
        assertNotNull(statuses.getLoadBalancerV2Status());
        assertNotNull(statuses.getLoadBalancerV2Status().getListenerStatuses());
        assertEquals(statuses.getLoadBalancerV2Status().getId(), id);
    }

    @Override
    protected Service service() {
        return Service.OCTAVIA;
    }
}
