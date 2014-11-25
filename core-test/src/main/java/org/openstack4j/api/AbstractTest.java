package org.openstack4j.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

import org.bouncycastle.util.io.Streams;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.identity.domain.Credentials;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

/**
 * Base Test class which handles Mocking a Webserver to fullfill and test against JSON response objects
 * from an OpenStack deployment
 * 
 * @author Jeremy Unruh
 */
public abstract class AbstractTest {

    protected enum Service {
        IDENTITY(5000),
        NETWORK(9696),
        COMPUTE(8774),
        OBJECT_STORAGE(8080);
        ;
        private final int port;

        private Service(int port) { this.port = port; }

    }

    protected static final String JSON_ACCESS = "/identity/access.json";

    private OSClient os;
    protected MockWebServer server = new MockWebServer();

    /**
     * @return the service the API is using
     */
    protected abstract Service service();

    @BeforeClass
    protected void startServer() {
        Logger.getLogger(getClass().getName()).info("Tests using connector: " + HttpExecutor.create().getExecutorName());
        try {
            server.play(service().port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * The path to the expected JSON results
     *
     * @param resource the resource
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void respondWith(String resource) throws IOException {
        MockResponse r = new MockResponse();
        InputStream is = getClass().getResourceAsStream(resource);
        r.setBody(is, is.available());
        r.setHeader("Content-Type", "application/json");
        server.enqueue(r);
    }
    
    /**
     * Responds with negative based response code and no body
     * @param statusCode the status code to respond with
     */
    protected void respondWith(int statusCode) {
        respondWith(null, statusCode);
    }
    
    /**
     * Responds with negative based response code and no body and optional headers
     * @param headers optional headers
     * @param statusCode the status code to respond with
     */
    protected void respondWith(Map<String,String> headers, int statusCode) {
        MockResponse r = new MockResponse();
        if (headers != null) {
            for (String name : headers.keySet()) {
                r.addHeader(name, headers.get(name));
            }
        }
        r.setBody("");
        r.setResponseCode(statusCode);
        server.enqueue(r);
    }

    protected String authURL(String path) {
        return String.format("%s%s", server.getUrl("/"), path);
    }

    @AfterClass
    protected void afterTest()
    {
        try {
            server.shutdown();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void associateClient(OSClient os)
    {
        this.os = os;
    }

    protected OSClient os() {
        if (os == null) {
            ObjectMapper mapper = new ObjectMapper();	
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            try {
                String json = new String(Streams.readAll(getClass().getResourceAsStream(JSON_ACCESS)));
                json = json.replaceAll("127.0.0.1", server.getUrl("/").getHost());
                KeystoneAccess a = mapper.readValue(json, KeystoneAccess.class);
                a.applyContext(authURL("/v2.0"), new Credentials("test", "test"));
                os = OSFactory.clientFromAccess(a);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        return os;
    }
}
