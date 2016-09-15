package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.Agent;
import org.openstack4j.model.network.Agent.Type;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.State;
import org.testng.annotations.Test;

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
    private static final String NETWORK_NAME = "net1";
    private static final String NETWORK_ID = "4e8e5957-649f-477b-9e5b-f1f75b21c03c";

    @Test
    public void getNetwork() throws Exception {
        respondWith(JSON_NETWORK);
        Network n = osv3().networking().network().get(NETWORK_ID);
        assertEquals(n.getName(), NETWORK_NAME);
        assertEquals(n.getStatus(), State.ACTIVE);
        assertEquals(n.isRouterExternal(), false);
    }

    @Test
    public void createNetwork() throws Exception {
        respondWith(JSON_NETWORK_EXTERNAL);
        Network n = osv3().networking().network()
                .create(Builders.network().name(NETWORK_NAME).isRouterExternal(true).adminStateUp(true).build());
        assertEquals(n.getName(), NETWORK_NAME);
        assertEquals(n.getStatus(), State.ACTIVE);
        assertEquals(n.isRouterExternal(), true);
    }

    @Test
    public void agentList() throws Exception {
        respondWith(JSON_AGENTS);
        List<? extends Agent> agentList = osv3().networking().agent().list();
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

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
