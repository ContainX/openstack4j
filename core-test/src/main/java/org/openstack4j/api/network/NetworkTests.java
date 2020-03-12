package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.Agent;
import org.openstack4j.model.network.Agent.Type;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.State;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Tests the Compute -> Network API against the mock webserver and spec based
 * json responses
 * 
 * @author Yin Zhang
 * @author Qin An
 */
@Test(suiteName = "Network")
public class NetworkTests extends AbstractTest {

    private static final String JSON_NETWORK = "/network/network.json";
    private static final String JSON_AGENTS = "/network/agents.json";
    private static final String JSON_NETWORK_EXTERNAL = "/network/network-external.json";
    private static final String JSON_NETWORK_ZONE = "/network/network_zone.json";
    private static final String NETWORK_NAME = "net1";
    private static final String NETWORK_ID = "4e8e5957-649f-477b-9e5b-f1f75b21c03c";

    @Test
    public void getNetwork() throws Exception {
        respondWith(JSON_NETWORK);
        Network n = osv3().networking().network().get(NETWORK_ID);
        server.takeRequest();	 
        assertEquals(n.getName(), NETWORK_NAME);
        assertEquals(n.getStatus(), State.ACTIVE);
        assertEquals(n.isRouterExternal(), false);
    }

    @Test
    public void createNetwork() throws Exception {
        respondWith(JSON_NETWORK_EXTERNAL);
        Network n = osv3().networking().network()
                .create(Builders.network().name(NETWORK_NAME).isRouterExternal(true).adminStateUp(true).build());
        server.takeRequest();	 
        assertEquals(n.getName(), NETWORK_NAME);
        assertEquals(n.getStatus(), State.ACTIVE);
        assertEquals(n.isRouterExternal(), true);
    }
    
    @Test
    public void createNetworkWithZone() throws Exception {
        respondWith(JSON_NETWORK_ZONE);
        Network n = osv3().networking().network()
                .create(Builders.network().name(NETWORK_NAME).isRouterExternal(true).adminStateUp(true).addAvailabilityZoneHints("nova").build());
        server.takeRequest();	 
        assertEquals(n.getName(), NETWORK_NAME);
        assertEquals(n.getStatus(), State.ACTIVE);
        assertEquals(n.getAvailabilityZoneHints().get(0), "nova");
    }    

    @Test
    public void agentList() throws Exception {
        respondWith(JSON_AGENTS);
        List<? extends Agent> agentList = osv3().networking().agent().list();
        server.takeRequest();	 
        Agent agent = agentList.get(0);
        assertEquals(agentList.size(), 12);
        assertEquals(agent.getBinary(), "neutron-dhcp-agent");
        assertEquals(agent.getAdminStateUp(), true);
        assertEquals(agent.getAlive(), true);
        assertEquals(agent.getAgentType(), Type.DHCP);
        assertEquals(agent.getHost(), "cic-0-3");
        assertEquals(agent.getId(), "086d8a3d-ef23-4708-909b-0c459528e2a6");
		assertEquals(agent.getCreatedAt(), (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2015-03-18 20:28:02"));
        assertEquals(agent.getAgentType(), Type.DHCP);
        assertEquals(agent.getAgentType(), Type.DHCP);
    }

    @Test
    public void detachNetworkToDhcpAgent() throws Exception {
        respondWith(204);
        ActionResponse result = osv3().networking().agent().detachNetworkToDhcpAgent("190ecbc2-77e0-4e4f-a96b-aa849edb357b", "4e8e5957-649f-477b-9e5b-f1f75b21c03c");
        server.takeRequest();
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    
	@Test
	public void listNetworkWithFilter() throws Exception {
		
		respondWith("/network/networks_filtered.json");
		
		final String name = "netOK";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("name", name);
		
		List<? extends Network> networks = osv3().networking().network().list(filters);
		assertEquals(networks.size(), 1);

		// Check that the list request is the one we expect
		RecordedRequest listRequest = server.takeRequest();	 
	
		assertNotNull(listRequest.getHeader("X-Auth-Token")); 
		assertTrue(listRequest.getPath().contains("/networks?name=" + name));
		
		assertEquals(networks.get(0).getName(), name);
		assertEquals(networks.get(0).getSubnets().get(0), "0c4faf33-8c23-4dc9-8bf5-30dd1ab452f9" );
		assertEquals(networks.get(0).getId(), "73f6f1ac-5e58-4801-88c3-7e12c6ddfb39");
		assertEquals(networks.get(0).getNetworkType(),  NetworkType.VXLAN);
	}
}
