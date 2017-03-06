package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Ipv6AddressMode;
import org.openstack4j.model.network.Ipv6RaMode;
import org.openstack4j.model.network.Subnet;
//import org.testng.annotations.Test;

/**
 * Tests the Network -> Network API against the mock webserver and spec based
 * json responses
 * 
 * @author Taemin
 */
//@Test(suiteName = "subnet")
public class SubnetTests extends AbstractTest {

    private static final String JSON_GET_SUBNET = "/network/subnet_ipv6_get.json";    
    private static final String JSON_CREATE_SUBNET = "/network/subnet_ipv6_create.json";
    
    private static final String SUBNET_NAME = "sub1";
    private static final String SUBNET_ID = "3b80198d-4f7b-4f77-9ef5-774d54e17126";
    private static final String NETWORK_ID = "1d071916-2a5a-4eeb-9f15-0f0fecf38a0a";
    private static final String CIDR = "2620:0:2d0:200::/64";

    @Test
    public void getSubnetIpV6() throws Exception {
        respondWith(JSON_GET_SUBNET);
        Subnet n = osv3().networking().subnet().get(SUBNET_ID);
        server.takeRequest();	 
        assertEquals(n.getName(), SUBNET_NAME);
        assertEquals(n.getIpv6AddressMode(), Ipv6AddressMode.DHCPV6_STATEFUL);
        assertEquals(n.getIpv6RaMode(), Ipv6RaMode.DHCPV6_STATEFUL);
    }

    @Ignore
    public void createSubnetIpV6() throws Exception {
        respondWith(JSON_CREATE_SUBNET);
        Subnet s = osv3().networking().subnet().create(Builders.subnet().networkId(NETWORK_ID)
								        								 .name(SUBNET_NAME)
								        								 .ipVersion(IPVersionType.V6)
								                						 .cidr(CIDR)
								                						 .ipv6AddressMode(Ipv6AddressMode.DHCPV6_STATEFUL)
								                						 .ipv6RaMode(Ipv6RaMode.DHCPV6_STATEFUL)
								                						 .build());
        server.takeRequest();	 
        assertEquals(s.getName(), SUBNET_NAME);
        assertEquals(s.getIpv6AddressMode(), Ipv6AddressMode.DHCPV6_STATEFUL);
        assertEquals(s.getIpv6RaMode(), Ipv6RaMode.DHCPV6_STATEFUL);
    }

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

    
}
