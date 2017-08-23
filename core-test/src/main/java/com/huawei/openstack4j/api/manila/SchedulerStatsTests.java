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
package com.huawei.openstack4j.api.manila;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.manila.BackendStoragePool;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Test cases for scheduler stats.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="SchedulerStats")
public class SchedulerStatsTests extends AbstractTest {
    private static final String JSON_SCHEDULER_STATS = "/manila/scheduler-stats.json";
    private static final String JSON_SCHEDULER_STATS_DETAIL = "/manila/scheduler-stats_detail.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void pools() throws Exception {
        respondWith(JSON_SCHEDULER_STATS);

        List<? extends BackendStoragePool> pools = osv3().share().schedulerStats().pools();
        assertEquals(pools.size(), 3);

        BackendStoragePool pool1 = pools.get(0);
        BackendStoragePool pool2 = pools.get(1);
        BackendStoragePool pool3 = pools.get(2);

        assertEquals(pool1.getHost(), "manila2");
        assertEquals(pool1.getName(), "manila2@generic1#GENERIC1");
        assertEquals(pool1.getPool(), "GENERIC1");
        assertEquals(pool1.getBackend(), "generic1");

        assertEquals(pool2.getHost(), "manila2");
        assertEquals(pool2.getName(), "manila2@unmanage1#UNMANAGE1");
        assertEquals(pool2.getPool(), "UNMANAGE1");
        assertEquals(pool2.getBackend(), "unmanage1");

        assertEquals(pool3.getHost(), "manila2");
        assertEquals(pool3.getName(), "manila2@ams_backend#AMS_BACKEND");
        assertEquals(pool3.getPool(), "AMS_BACKEND");
        assertEquals(pool3.getBackend(), "ams_backend");
    }

    @Test
    public void poolsDetail() throws Exception {
        respondWith(JSON_SCHEDULER_STATS_DETAIL);

        List<? extends BackendStoragePool> pools = osv3().share().schedulerStats().poolsDetail();
        assertEquals(pools.size(), 1);

        BackendStoragePool pool = pools.get(0);

        assertEquals(pool.getPool(), "LONDON");
        assertEquals(pool.getHost(), "nosb-devstack");
        assertEquals(pool.getName(), "nosb-devstack@london#LONDON");
        assertFalse(pool.getCapabilities().getQosSupport());
        assertEquals(
                pool.getCapabilities().getConsistencyGroupSupport(),
                BackendStoragePool.ConsistencyGroupSupport.POOL);
        assertEquals(pool.getCapabilities().getTimestamp(), "2015-09-21T08:58:56.190856");
        assertEquals(pool.getCapabilities().getShareBackendName(), "LONDON");
        assertEquals(pool.getCapabilities().getServerPoolsMapping().size(), 2);
        assertTrue(
                pool.getCapabilities().getServerPoolsMapping().get("1320689d-80f4-49f6-8a70-0e2c1ed8ad90").isEmpty());
        assertTrue(
                pool.getCapabilities().getServerPoolsMapping().get("3a4caac5-0880-4629-a334-6cdda88a0c0e").isEmpty());
        assertTrue(pool.getCapabilities().getDriverHandlesShareServers());
        assertEquals(pool.getCapabilities().getDriverVersion(), "1.0");
        assertEquals(pool.getCapabilities().getTotalCapacityGb(), "unknown");
        assertEquals((int) pool.getCapabilities().getReservedPercentage(), 0);
        assertNull(pool.getCapabilities().getPools());
        assertEquals(pool.getCapabilities().getVendorName(), "Open Source");
        assertTrue(pool.getCapabilities().getSnapshotSupport());
        assertEquals(pool.getCapabilities().getFreeCapacityGb(), "unknown");
        assertEquals(pool.getCapabilities().getStorageProtocol(), "NFS_CIFS");
        assertEquals(pool.getBackend(), "london");
    }
}
