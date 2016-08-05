package org.openstack4j.api.storage;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.VolumeBackendStoragePool;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Test cases for scheduler stats.
 *
 * @author Daniel Gonzalez Nothnagel
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

        List<? extends VolumeBackendStoragePool> pools = os().blockStorage().schedulerStats().pools();
        assertEquals(pools.size(), 3);

        VolumeBackendStoragePool pool1 = pools.get(0);
        VolumeBackendStoragePool pool2 = pools.get(1);
        VolumeBackendStoragePool pool3 = pools.get(2);

        assertEquals(pool1.getName(), "cinder1@generic1#GENERIC1");

        assertEquals(pool2.getName(), "cinder2@unmanage1#UNMANAGE1");

        assertEquals(pool3.getName(), "cinder3@ams_backend#AMS_BACKEND");
    }

    @Test
    public void poolsDetail() throws Exception {
        respondWith(JSON_SCHEDULER_STATS_DETAIL);

        List<? extends VolumeBackendStoragePool> pools = os().blockStorage().schedulerStats().poolsDetail();
        assertEquals(pools.size(), 2);

        VolumeBackendStoragePool pool = pools.get(0);
        VolumeBackendStoragePool pool4 = pools.get(1);


        assertEquals(pool.getName(), "pool1");
        assertFalse(pool.getCapabilities().getQosSupport());
        assertEquals(pool.getCapabilities().getDriverVersion(), "1.0.0");
        assertEquals((int) pool.getCapabilities().getTotalCapacityGb(), 1024);
        assertEquals((int) pool.getCapabilities().getReservedPercentage(), 0);
        assertEquals((int) pool.getCapabilities().getFreeCapacityGb(), 100);
        assertEquals(pool.getCapabilities().getStorageProtocol(), "iSCSI");


        assertEquals(pool4.getName(), "pool2");
        assertTrue(pool4.getCapabilities().getQosSupport());
        assertEquals(pool4.getCapabilities().getDriverVersion(), "1.0.1");
        assertEquals((int) pool4.getCapabilities().getTotalCapacityGb(), 512);
        assertEquals((int) pool4.getCapabilities().getReservedPercentage(), 0);
        assertEquals((int) pool4.getCapabilities().getFreeCapacityGb(), 200);
        assertEquals(pool4.getCapabilities().getStorageProtocol(), "iSER");
    }

}
