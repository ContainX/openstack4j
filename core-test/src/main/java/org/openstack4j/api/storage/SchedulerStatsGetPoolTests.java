package org.openstack4j.api.storage;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.storage.block.domain.VolumeBackendPool;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Test cases for scheduler stats.
 *
 * @author Chen guofeng gf.chen40@gmail.com
 */
@Test(suiteName="SchedulerStatsGetPool")
public class SchedulerStatsGetPoolTests extends AbstractTest {
    private static final String JSON_SCHEDULER_STATS = "/storage/v2/cinder_scheduler-stats.json";
    private static final String JSON_SCHEDULER_STATS_DETAIL = "/storage/v2/cinder_scheduler-stats_detail.json";

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void pools() throws Exception {
        respondWith(JSON_SCHEDULER_STATS);

        List<? extends VolumeBackendPool> pools = osv3().blockStorage().schedulerStatsPools().pools();
        assertEquals(pools.size(), 3);

        VolumeBackendPool pool1 = pools.get(0);
        VolumeBackendPool pool2 = pools.get(1);
        VolumeBackendPool pool3 = pools.get(2);

        assertEquals(pool1.getName(), "cinder1@generic1#GENERIC1");

        assertEquals(pool2.getName(), "cinder2@unmanage1#UNMANAGE1");

        assertEquals(pool3.getName(), "cinder3@ams_backend#AMS_BACKEND");
    }

    @Test
    public void poolsDetail() throws Exception {
        respondWith(JSON_SCHEDULER_STATS_DETAIL);

        List<? extends VolumeBackendPool> pools = osv3().blockStorage().schedulerStatsPools().poolsDetail();
        assertEquals(pools.size(), 2);

        VolumeBackendPool pool4 = pools.get(0);
        VolumeBackendPool pool5 = pools.get(1);


        assertEquals(pool4.getName(), "pool1");
        assertFalse(pool4.getCapabilities().getQosSupport());
        assertEquals(pool4.getCapabilities().getDriverVersion(), "1.0.0");
        assertEquals((long) pool4.getCapabilities().getTotalCapacityGb(), 1024);
        assertEquals((int) pool4.getCapabilities().getReservedPercentage(), 0);
        assertEquals((long) pool4.getCapabilities().getFreeCapacityGb(), 100);
        assertEquals(pool4.getCapabilities().getStorageProtocol(), "iSCSI");


        assertEquals(pool5.getName(), "pool2");
        assertTrue(pool5.getCapabilities().getQosSupport());
        assertEquals(pool5.getCapabilities().getDriverVersion(), "1.0.1");
        assertEquals((long) pool5.getCapabilities().getTotalCapacityGb(), 512);
        assertEquals((int) pool5.getCapabilities().getReservedPercentage(), 0);
        assertEquals((long) pool5.getCapabilities().getFreeCapacityGb(), 200);
        assertEquals(pool5.getCapabilities().getStorageProtocol(), "iSER");
    }

}