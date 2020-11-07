package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.NetworkIPAvailability;
import org.openstack4j.model.network.options.NetIpAvailListOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Test cases for Network IP availabilities based Services
 *
 * @author bboyHan
 */
@Test(suiteName = "NetworkIPAvailabilityTests")
public class NetworkIPAvailabilityTests extends AbstractTest {

    private static final String NETWORK_IP_AVAILABILITY_JSON = "/network/network_ip_availability.json";
    private static final String NETWORK_IP_AVAILABILITIES_JSON = "/network/network_ip_availabilities.json";

    @Test
    public void get() throws Exception {
        respondWith(NETWORK_IP_AVAILABILITY_JSON);
        NetworkIPAvailability networkIPAvailability = osv3().networking().networkIPAvailability().get("networkId");
        server.takeRequest();
        assertEquals("6801d9c8-20e6-4b27-945d-62499f00002e", networkIPAvailability.getNetworkId());
    }

    @Test
    public void list() throws Exception {
        respondWith(NETWORK_IP_AVAILABILITIES_JSON);
        List<? extends NetworkIPAvailability> networkIPAvailabilities = osv3().networking().networkIPAvailability().list();
        server.takeRequest();
        assertEquals(2, networkIPAvailabilities.size());
    }

    @Test
    public void listByOptions() throws Exception {
        respondWith(NETWORK_IP_AVAILABILITIES_JSON);
        NetIpAvailListOptions options = NetIpAvailListOptions.create().networkId("networkId");
        List<? extends NetworkIPAvailability> networkIPAvailabilities = osv3().networking().networkIPAvailability().list();
        server.takeRequest();
        assertEquals(2, networkIPAvailabilities.size());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

}
