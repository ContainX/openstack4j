package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.HealthMonitorType;
import org.openstack4j.model.network.ext.HealthMonitorV2;
import org.openstack4j.model.network.ext.HealthMonitorV2Update;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author ashleykasim
 *
 */
@Test(suiteName="Network/healthMonitor", enabled = true)
public class HealthMonitorV2Tests extends AbstractTest {
    private static final String HEALTHMONITORSV2_JSON = "/network/healthmonitorsv2.json";
    private static final String HEALTHMONITORV2_JSON = "/network/healthmonitorv2.json";
    private static final String HEALTHMONITORV2_UPDATE_JSON = "/network/healthmonitorv2_update.json";

    public void testListHealthMonitorsV2() throws IOException {
        respondWith(HEALTHMONITORSV2_JSON);
        List<? extends HealthMonitorV2> list = osv3().networking().lbaasV2().healthMonitor().list();
        assertEquals(list.size(), 3);
        assertEquals("350576d8-5015-4d4e-b73f-23df2397e4c4", list.get(0).getId());
    }

    public void testListHealthMonitorsV2Filter() throws IOException {
        respondWith(HEALTHMONITORSV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("tenant_id", "6f759d84e3ca496ab77f8c0ffaa0311e");
        List<? extends HealthMonitorV2> list = osv3().networking().lbaasV2().healthMonitor().list(map);
        assertEquals(list.size(), 3);
    }

    public void testGetHealthMonitorV2() throws IOException {
        respondWith(HEALTHMONITORV2_JSON);
        String id = "350576d8-5015-4d4e-b73f-23df2397e4c4";
        HealthMonitorV2 hm = osv3().networking().lbaasV2().healthMonitor().get(id);
        assertNotNull(hm);
        assertEquals(hm.getId(), id);
    }

    public void testCreateHealthMonitorV2() throws IOException {
        respondWith(HEALTHMONITORV2_JSON);
        Integer delay = 2;
        Integer timeout = 3;
        HealthMonitorType type = HealthMonitorType.HTTP;
        HealthMonitorV2 create = Builders.healthmonitorV2()
                .adminStateUp(true)
                .delay(delay)
                .type(type)
                .timeout(timeout)
                .build();
        HealthMonitorV2 result = osv3().networking().lbaasV2().healthMonitor().create(create);
        assertEquals(result.getDelay(), delay);
        assertEquals(result.getTimeout(), timeout);
        assertEquals(result.getType(), type);
        assertTrue(result.isAdminStateUp());
    }

    public void testUpdateHealthMonitorV2() throws IOException {
        respondWith(HEALTHMONITORV2_UPDATE_JSON);
        Integer delay = 10;
        Integer timeout = 5;
        String id = "350576d8-5015-4d4e-b73f-23df2397e4c4";
        HealthMonitorV2Update update = Builders.healthMonitorV2Update()
                .delay(delay)
                .timeout(timeout)
                .build();
        HealthMonitorV2 result = osv3().networking().lbaasV2().healthMonitor().update(id, update);
        assertEquals(result.getDelay(), delay);
        assertEquals(result.getTimeout(), timeout);
    }

    public void testDeleteHealthMonitorV2() {
        respondWith(204);
        ActionResponse result = osv3().networking().lbaasV2().healthMonitor().delete("350576d8-5015-4d4e-b73f-23df2397e4c4");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
