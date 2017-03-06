package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.NetQuota;
import org.testng.annotations.Test;

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
