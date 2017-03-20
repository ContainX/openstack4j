package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.Ipv6AddressMode;
import org.openstack4j.model.network.Ipv6RaMode;
import org.openstack4j.model.network.Subnet;
import org.testng.annotations.Test;

/**
 * Tests the Neutron -> Subnet API against the mock webserver and spec based
 * json responses
 * 
 * @author Taemin 
 */
@Test(suiteName = "Subnet")
public class SubnetTests extends AbstractTest {

    private static final String JSON_SUBNET = "/network/subnet_ipv6.json";      
    private static final String SUBNET_NAME = "sub1";
    private static final String SUBNET_ID = "3b80198d-4f7b-4f77-9ef5-774d54e17126";

    @Test
    public void getSubnet() throws Exception {
        respondWith(JSON_SUBNET);
        Subnet n = osv3().networking().subnet().get(SUBNET_ID);
        server.takeRequest();	 
        assertEquals(n.getName(), SUBNET_NAME);
        assertEquals(n.getIpv6AddressMode(), Ipv6AddressMode.DHCPV6_STATEFUL.name());
        assertEquals(n.getIpv6RaMode(), Ipv6RaMode.DHCPV6_STATEFUL.name());        
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    
}
