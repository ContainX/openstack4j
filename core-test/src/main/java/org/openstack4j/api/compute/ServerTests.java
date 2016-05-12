package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.exceptions.ServerResponseException;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.Server.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test cases for Server based Services
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Servers")
public class ServerTests extends AbstractTest {

    private static final String JSON_SERVERS = "/compute/servers.json";
    private static final String JSON_SERVER_CREATE = "/compute/server_create.json";

    @Test
    public void listServer() throws Exception {
        respondWith(JSON_SERVERS);
        
        List<? extends Server> servers = osv3().compute().servers().list();
        assertEquals(1, servers.size());
        

        Server s = servers.get(0);
        assertEquals(1, s.getAddresses().getAddresses("private").size());
        assertEquals("192.168.0.3", s.getAddresses().getAddresses("private").get(0).getAddr());
        assertEquals(Status.ACTIVE, s.getStatus());
        assertEquals("new-server-test", s.getName());
    }
    
    @Test(expectedExceptions = ServerResponseException.class, invocationCount = 10)
    public void serverError() {
        String jsonResponse = "{\"computeFault\": {"
                + "\"message\": \"The server has either erred or is incapable of performing the requested operation.\", "
                + "\"code\": 500}}";

        respondWith(500, jsonResponse);
        osv3().compute().servers().get("05184ba3-00ba-4fbc-b7a2-03b62b884931");
        Assert.fail("Exception should have been thrown.");
    }
    
    @Test
    public void createServer() throws Exception {
        respondWith(JSON_SERVER_CREATE);
        
        Server server = osv3().compute().servers().boot(Builders.server().name("server-test-1").build());
        assertEquals("server-test-1", server.getName());
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
