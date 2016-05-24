package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.ShareServer;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for share servers
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="ShareServer")
public class ShareServerTests extends AbstractTest {
    private static final String JSON_SHARE_SERVERS = "/manila/share_servers.json";
    private static final String JSON_SHARE_SERVER = "/manila/share_server.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARE_SERVERS);

        List<? extends ShareServer> shareServers = osv3().share().shareServers().list();
        assertEquals(shareServers.size(), 1);

        ShareServer shareServer = shareServers.get(0);

        assertEquals(shareServer.getStatus(), ShareServer.Status.ACTIVE);
        assertEquals(shareServer.getUpdatedAt(), "2015-09-07T08:52:15.000000");
        assertEquals(shareServer.getShareNetworkId(), "713df749-aac0-4a54-af52-10f6c991e80c");
        assertEquals(shareServer.getHost(), "manila2@generic1");
        assertEquals(shareServer.getShareNetworkName(), "net_my");
        assertEquals(shareServer.getProjectId(), "16e1ab15c35a457e9c2b2aa189f544e1");
        assertEquals(shareServer.getId(), "ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SHARE_SERVER);

        ShareServer shareServer = osv3().share().shareServers().get("ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");

        assertEquals(shareServer.getStatus(), ShareServer.Status.ACTIVE);
        assertEquals(shareServer.getBackendDetails().size(), 8);
        assertEquals(shareServer.getBackendDetails().get("username"), "manila");
        assertEquals(shareServer.getBackendDetails().get("router_id"), "4b62ce91-56c5-45c1-b0ef-8cbbe5dd34f4");
        assertEquals(shareServer.getBackendDetails().get("pk_path"), "/opt/stack/.ssh/id_rsa");
        assertEquals(shareServer.getBackendDetails().get("subnet_id"), "16e99ad6-5191-461c-9f34-ac84a39c3adb");
        assertEquals(shareServer.getBackendDetails().get("ip"), "10.254.0.3");
        assertEquals(shareServer.getBackendDetails().get("instance_id"), "75f2f282-af65-49ba-a7b1-525705b1bf1a");
        assertEquals(shareServer.getBackendDetails().get("public_address"), "10.254.0.3");
        assertEquals(shareServer.getBackendDetails().get("service_port_id"), "8ff21760-961e-4b83-a032-03fd559bb1d3");
        assertEquals(shareServer.getCreatedAt(), "2015-09-07T08:37:19.000000");
        assertEquals(shareServer.getUpdatedAt(), "2015-09-07T08:52:15.000000");
        assertEquals(shareServer.getShareNetworkName(), "net_my");
        assertEquals(shareServer.getHost(), "manila2@generic1");
        assertEquals(shareServer.getShareNetworkId(), "713df749-aac0-4a54-af52-10f6c991e80c");
        assertEquals(shareServer.getProjectId(), "16e1ab15c35a457e9c2b2aa189f544e1");
        assertEquals(shareServer.getId(), "ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareServers().delete("ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
        assertTrue(response.isSuccess());
    }
}
