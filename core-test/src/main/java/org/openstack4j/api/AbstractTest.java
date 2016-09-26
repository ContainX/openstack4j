package org.openstack4j.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bouncycastle.util.io.Streams;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.identity.v2.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.v3.domain.KeystoneToken;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.io.ByteStreams;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Base Test class which handles Mocking a Webserver to fullfill and test
 * against JSON response objects from an OpenStack deployment
 *
 * @author Jeremy Unruh
 */
public abstract class AbstractTest {

    protected enum Service {
        IDENTITY(5000), 
        NETWORK(9696), 
        COMPUTE(8774), 
        BLOCK_STORAGE(8776), 
        METERING(8087), 
        TELEMETRY(8087), 
        SHARE(8786), 
        OBJECT_STORAGE(8800),
        BARBICAN(9311),
        DATABASE(8779),
    	TACKER(9890),
        IMAGE(9292),
        CLUSTERING(8778),
        APP_CATALOG(8082);

        private final int port;

        private Service(int port) {
            this.port = port;
        }

    }
    
    private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

    protected static final String JSON_ACCESS = "/identity/v2/access.json";
    protected static final String JSON_TOKEN = "/identity/v3/authv3_project.json";
    protected static final String TOKEN_ID = "123456789";

    protected OSClientV2 osv2;
    protected OSClientV3 osv3;
    private String host;
    protected MockWebServer server = new MockWebServer();

    /**
     * @return the service the API is using
     */
    protected abstract Service service();

    @BeforeClass
    protected void startServer() throws UnknownHostException {

        InetAddress inetAddress = InetAddress.getByName("localhost");
        LOG.info("localhost inet address: " + inetAddress.toString());
        LOG.info("Tests using connector: " + HttpExecutor.create().getExecutorName() + " on " + getHost());

        try {
            LOG.info("Starting server on port " + service().port);
            server.start(service().port);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Responds with success status code and body from json resource file
     *
     * @param resource the json resource file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void respondWith(String resource) throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        InputStream is = getClass().getResourceAsStream(resource);
        respondWith(headers, 200, new String(ByteStreams.toByteArray(is)));
    }

    /**
     * Responds with specified status code and no body
     * 
     * @param statusCode the status code to respond with
     */
    protected void respondWith(int statusCode) {
        respondWith(null, statusCode, "");
    }

    /**
     * Responds with specified status code, no body and optional headers
     * 
     * @param headers optional headers
     * @param statusCode the status code to respond with
     */
    protected void respondWith(Map<String, String> headers, int statusCode) {
        respondWith(headers, statusCode, "");
    }

    /**
     * Responds with specified status code and json body
     * 
     * @param statusCode the status code to respond with
     * @param body the json body
     */
    protected void respondWith(int statusCode, String jsonBody) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        respondWith(headers, statusCode, jsonBody);
    }

    /**
     * Responds with specified status code, body and optional headers
     * 
     * @param headers optional headers
     * @param statusCode the status code to respond with
     * @param body the response body
     */
    protected void respondWith(Map<String, String> headers, int statusCode, String body) {
        MockResponse r = new MockResponse();
        if (headers != null) {
            for (String name : headers.keySet()) {
                r.addHeader(name, headers.get(name));
            }
        }
        r.setBody(body);
        r.setResponseCode(statusCode);
        server.enqueue(r);
    }

    /**
     * Responds with given header, status code, body from json resource file.
     *
     * @param headers the specified header
     * @param statusCode the status code to respond with
     * @param resource the json resource file
     * @throws IOException Signals that an I/O exception has occurred
     */
    protected void respondWithHeaderAndResource(Map<String, String> headers, int statusCode, String resource)
            throws IOException {
        InputStream is = getClass().getResourceAsStream(resource);
        respondWith(headers, statusCode, new String(ByteStreams.toByteArray(is)));
    }

    protected void respondWithCodeAndResource(int statusCode, String resource) throws IOException {
        InputStream is = getClass().getResourceAsStream(resource);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        respondWith(headers, statusCode, new String(ByteStreams.toByteArray(is)));
    }

    protected String authURL(String path) {
        return String.format("http://%s:5000%s", getHost(), path);
    }

    @AfterClass(alwaysRun = true)
    protected void afterTest() {
        try {
            server.shutdown();
            LOG.info("Stopped server on port " + service().port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void associateClientV2(OSClientV2 osv2) {
        this.osv2 = osv2;
    }
    
    protected void associateClientV3(OSClientV3 osv3) {
        this.osv3 = osv3;
    }

    protected OSClientV2 osv2() {
        if (osv2 == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            try {
                String json = new String(Streams.readAll(getClass().getResourceAsStream(JSON_ACCESS)));
                LOG.info(getClass().getName());
                //LOG.info(getClass().getName() + ", JSON Access = " + json);
                json = json.replaceAll("127.0.0.1", getHost());
                //LOG.info("JSON Access = " + json);
                KeystoneAccess a = mapper.readValue(json, KeystoneAccess.class);
                a.applyContext(authURL("/v2.0"),
                        new org.openstack4j.openstack.identity.v2.domain.Credentials("test", "test"));
                osv2 = OSFactory.clientFromAccess(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return osv2;
    }

    protected OSClientV3 osv3() {
        if (osv3 == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            try {
                String json = new String(Streams.readAll(getClass().getResourceAsStream(JSON_TOKEN)));
                LOG.info(getClass().getName());
                json = json.replaceAll("devstack.openstack.stack", getHost());
                KeystoneToken token = mapper.readValue(json, KeystoneToken.class);
                token.setId(TOKEN_ID);
                token.applyContext(authURL("/v3"),
                        new org.openstack4j.openstack.identity.v3.domain.Credentials("admin", "test"));

                osv3 = OSFactory.clientFromToken(token);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return osv3;
    }

    private String getHost() {
        /*
         * try { if (host == null) host =
         * InetAddress.getLocalHost().getHostAddress(); } catch (Exception e) {
         * e.printStackTrace(); }
         */
        if (host == null)
            return "127.0.0.1";

        return host;
    }
}
