package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.exceptions.ServerResponseException;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.Server.Status;
import org.openstack4j.model.compute.ServerPassword;
import org.openstack4j.model.compute.actions.EvacuateOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Test cases for Server based Services
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Servers")
public class ServerTests extends AbstractTest {

    private static final String JSON_SERVERS = "/compute/servers.json";
    private static final String JSON_SERVER_CREATE = "/compute/server_create.json";
    private static final String JSON_SERVER_EVACUATE = "/compute/server_evacuate.json";
    private static final String JSON_SERVER_CONSOLE_OUTPUT = "/compute/server_console_output.json";    

    @Test
    public void listServer() throws Exception {
        respondWith(JSON_SERVERS);
        
        List<? extends Server> servers = osv3().compute().servers().list();
        assertEquals(1, servers.size());
        
        takeRequest();
        
        Server s = servers.get(0);
        assertEquals(1, s.getAddresses().getAddresses("private").size());
        assertEquals("192.168.0.3", s.getAddresses().getAddresses("private").get(0).getAddr());
        assertEquals(Status.ACTIVE, s.getStatus());
        assertEquals("new-server-test", s.getName());
    }
    
    @Test(expectedExceptions = ServerResponseException.class, invocationCount = 10)
    public void serverError() throws Exception {
        String jsonResponse = "{\"computeFault\": {"
                + "\"message\": \"The server has either erred or is incapable of performing the requested operation.\", "
                + "\"code\": 500}}";

        respondWith(500, jsonResponse);
        osv3().compute().servers().get("05184ba3-00ba-4fbc-b7a2-03b62b884931");
        Assert.fail("Exception should have been thrown.");
        
        takeRequest();
    }
    
    @Test
    public void createServer() throws Exception {
        respondWith(JSON_SERVER_CREATE);
        
        Server server = osv3().compute().servers().boot(Builders.server().name("server-test-1").build());
        assertEquals("server-test-1", server.getName());
        
        takeRequest();
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
    
    @Test
    public void evacuateServer() throws Exception {
        respondWith(JSON_SERVER_EVACUATE);
        
        ServerPassword password =  osv3().compute().servers().evacuate("e565cbdb-8e74-4044-ba6e-0155500b2c46", EvacuateOptions.create().host("server-test-1").onSharedStorage(false));          
        assertEquals(password.getPassword(), "MySecretPass");
        
        takeRequest();
    }

    @Test
    public void createServerSnapshotWithNoMetadata() throws Exception {
        createServerSnapshot(null);
    }

    @Test
    public void createServerSnapshotWithMetadata() throws Exception {
        Map<String, String> metadata = new HashMap<String, String>() {{ put("image_type", "image"); }};
        createServerSnapshot(metadata);
    }

    private void createServerSnapshot(Map<String, String> metadata) throws Exception {
        final String serverSnapshotId = "72f759b3-2576-4bf0-9ac9-7cb4a5b9d541";
        String serverId = "e565cbdb-8e74-4044-ba6e-0155500b2c46";
        Map<String, String> headers = new HashMap<String, String>() {{
            put("location", "http://127.0.0.1:9292/images/" + serverSnapshotId);
        }};

        respondWith(headers, 202);

        String imageId = osv3().compute().servers().createSnapshot(serverId, "server-snapshot", metadata);
        assertEquals(imageId, serverSnapshotId);

        takeRequest();
    }

    @Test
    public void getServerConsoleOutput() throws Exception {                
        // Get console output with explicit length
        int length = 50;
        respondWith(JSON_SERVER_CONSOLE_OUTPUT);
        String console = osv3().compute().servers().getConsoleOutput("existing-uuid", length);
        
        // Check that the request is the one we expect
         RecordedRequest request = server.takeRequest();     
         
        assertNotNull(console);
        assertTrue(console.length() > 0);
        
        String requestBody = request.getBody().readUtf8();
        assertTrue(requestBody.contains("\"os-getConsoleOutput\" : {"));
        assertTrue(requestBody.contains("\"length\" : " + length));
          
        // Get full console output
        respondWith(JSON_SERVER_CONSOLE_OUTPUT);
        console = osv3().compute().servers().getConsoleOutput("existing-uuid", 0);
        
        // Check that the request is the one we expect
        request = takeRequest();     
         
        assertNotNull(console);
        assertTrue(console.length() > 0);
        
        requestBody = request.getBody().readUtf8();
        assertFalse(requestBody.contains("\"length\""));
    }
    
    @Test
    public void getServerConsoleOutputNonExistingServer() throws Exception {
        respondWith(404);
        
        String console = osv3().compute().servers().getConsoleOutput("non-existing-uuid", 0);
        assertNull(console);
        
        takeRequest();
    }

}
