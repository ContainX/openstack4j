package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.ext.NetworkIPAvailability;
import org.testng.annotations.Test;

/**
 * Network IP availability test
 *
 * @author Xiangbin HAN
 */
@Test(suiteName = "Network/network_ip_availability", enabled = true)
public class NetworkIPAvailabilityTest extends AbstractTest {
    private static final String NETWORK_IP_AVAILABILITY_JSON = "/network/network_ip_availability.json";
    private static final String NETWORK_IP_AVAILABILITIES_JSON = "/network/network_ip_availabilities.json";

    public void testGetNetworkIPAvailability() throws IOException {
        respondWith(NETWORK_IP_AVAILABILITY_JSON);
        NetworkIPAvailability networkIPAvailability = osv3().networking().networkIPAvailability().get("network-id");
        assertEquals("private", networkIPAvailability.getNetworkName());
        assertEquals("6801d9c8-20e6-4b27-945d-62499f00002e", networkIPAvailability.getNetworkId());
        assertEquals("d56d3b8dd6894a508cf41b96b522328c", networkIPAvailability.getTenantId());
        assertEquals("d56d3b8dd6894a508cf41b96b522328c", networkIPAvailability.getProjectId());
        assertEquals(new BigInteger("18446744073709552000"), networkIPAvailability.getTotalIps());
        assertEquals(new BigInteger("4"), networkIPAvailability.getUsedIps());
        assertEquals(new BigInteger("2"), networkIPAvailability.getSubnetIPAvailabilities().get(0).getUsedIps());
    }

    public void testListNetworkIPAvailabilities() throws IOException {
        respondWith(NETWORK_IP_AVAILABILITIES_JSON);
        List<? extends NetworkIPAvailability> networkIPAvailabilities = osv3().networking().networkIPAvailability().get();
        assertEquals(2, networkIPAvailabilities.size());
        assertEquals("public", networkIPAvailabilities.get(0).getNetworkName());
        assertEquals("6801d9c8-20e6-4b27-945d-62499f00002e", networkIPAvailabilities.get(1).getNetworkId());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

}
