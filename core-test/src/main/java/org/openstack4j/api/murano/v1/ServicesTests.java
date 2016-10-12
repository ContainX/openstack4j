package org.openstack4j.api.murano.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.murano.v1.domain.ActionInfo;
import org.openstack4j.model.murano.v1.domain.Application;
import org.openstack4j.model.murano.v1.domain.ServiceInfo;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class ServicesTests extends AbstractTest {
    private static final String SERVICE_JSON = "/murano/v1/service.json";
    private static final String SERVICES_JSON = "/murano/v1/services.json";
    private static final String envId = "e1c1b5a0b3284f188c5d91ab18bf0451";
    private static final String sessionId = "c2e2b5a0b3284f188c5d91ab18bf8754";

    private String getResourceAsString(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        return new String(ByteStreams.toByteArray(is));
    }

    public void testListServices() throws IOException {
        respondWith(SERVICES_JSON);
        List<? extends Application> apps = osv3().murano().services().list(envId, sessionId);
        assertNotNull(apps);
        assertEquals(apps.size(), 2);
    }

    public void testListServicesWithoutSession() throws IOException {
        respondWith(SERVICE_JSON);
        List<? extends Application> apps = osv3().murano().services().list(envId);
        assertNotNull(apps);
        assertEquals(apps.size(), 1);
    }

    public void testGetOneService() throws IOException {
        respondWith(SERVICE_JSON);
        List<? extends Application> apps = osv3().murano().services().get(envId, sessionId);
        assertNotNull(apps);
        assertEquals(apps.size(), 1);
    }

    public void testGetServiceCheckActions() throws IOException {
        respondWith(SERVICE_JSON);
        List<? extends Application> apps = osv3().murano().services().get(envId, sessionId);
        assertNotNull(apps);
        assertEquals(apps.size(), 1);

        ServiceInfo service = apps.get(0).getService();

        assertEquals(service.getId(), "2614f193-f13e-42b1-af7e-01729bb5af75");
        assertEquals(service.getType(), "com.mirantis.docker.DockerStandaloneHost");

        List<? extends ActionInfo> actions = service.getActions();

        assertEquals(actions.size(), 2);

        ActionInfo action = actions.get(0);

        assertEquals(action.getName(), "getTest");
        assertEquals(action.getId(), "2614f193-f13e-42b1-af7e-01729bb5af75_getTest");
    }

    public void testGetServicesListResponse() throws IOException {
        respondWith(SERVICES_JSON);
        List<? extends Application> apps = osv3().murano().services().get(envId, sessionId);
        assertNotNull(apps);
        assertEquals(apps.size(), 2);
    }

    public void testGetServicesEmptySession() throws IOException {
        respondWith(200, "[]");
        List<? extends Application> apps = osv3().murano().services().get(envId, sessionId);
        assertNotNull(apps);
        assertEquals(apps.size(), 0);
    }

    public void testCreateOneService() throws IOException {
        respondWith(SERVICE_JSON);
        String json = this.getResourceAsString(SERVICE_JSON);
        List<? extends Application> apps = osv3().murano().services().create(envId, sessionId, json);
        assertNotNull(apps);
        assertEquals(apps.size(), 1);
    }

    public void testCreateOneServiceFromMap() throws IOException {
        respondWith(SERVICE_JSON);
        String json = this.getResourceAsString(SERVICE_JSON);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> serviceMap = mapper.readValue(json, Map.class);

        // Need to remove internal service info from test json string.
        serviceMap.remove("?");

        Application app = osv3().murano().services().create(envId, sessionId, serviceMap);
        assertNotNull(app);
        assertEquals(app.getData(), serviceMap);
    }

    public void testCreateManyServices() throws IOException {
        respondWith(SERVICES_JSON);
        String json = this.getResourceAsString(SERVICES_JSON);
        List<? extends Application> apps = osv3().murano().services().create(envId, sessionId, json);
        assertNotNull(apps);
        assertEquals(apps.size(), 2);
    }

    public void testUpdateOneService() throws IOException {
        respondWith(SERVICE_JSON);
        String json = this.getResourceAsString(SERVICE_JSON);
        List<? extends Application> apps = osv3().murano().services().update(envId, sessionId, json);
        assertNotNull(apps);
        assertEquals(apps.size(), 1);
    }

    public void testUpdateOneServiceFromMap() throws IOException {
        respondWith(SERVICE_JSON);
        String json = this.getResourceAsString(SERVICE_JSON);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> serviceMap = mapper.readValue(json, Map.class);

        // Need to remove internal service info from test json string.
        serviceMap.remove("?");

        Application app = osv3().murano().services().update(envId, sessionId, serviceMap);
        assertNotNull(app);
        assertEquals(app.getData(), serviceMap);
    }

    public void testUpdateManyServices() throws IOException {
        respondWith(SERVICES_JSON);
        String json = this.getResourceAsString(SERVICES_JSON);
        List<? extends Application> apps = osv3().murano().services().update(envId, sessionId, json);
        assertNotNull(apps);
        assertEquals(apps.size(), 2);
    }

    public void testDeleteService() throws IOException {
        respondWith(200);
        ActionResponse delete = osv3().murano().services().delete(envId, "/", sessionId);
        assertTrue(delete.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
