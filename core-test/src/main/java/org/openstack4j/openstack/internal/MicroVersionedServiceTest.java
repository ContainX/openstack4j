package org.openstack4j.openstack.internal;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.openstack.internal.MicroVersion;
import org.openstack4j.openstack.internal.MicroVersionedOpenStackService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MicroVersionedServiceTest extends AbstractTest {
    private TestService service;
    private static final MicroVersion MICRO_VERSION = new MicroVersion(1, 0);
    private static final String API_VERSION_HEADER = "X-Openstack-Test-Api-Version";

    @Override
    protected Service service() {
        // The service type defined here is only required to get a valid session.
        // Because we never send a request, but only build it and check the headers,
        // the service type defined here does not matter for the test results.
        return Service.COMPUTE;
    }

    @BeforeMethod
    public void setUp() {
        service = new TestService(ServiceType.UNKNOWN, MICRO_VERSION, API_VERSION_HEADER);
    }

    @Test
    public void get() {
        Map<String, Object> headers = service.get(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void post() {
        Map<String, Object> headers = service.post(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void put() {
        Map<String, Object> headers = service.put(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void patch() {
        Map<String, Object> headers = service.patch(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void delete() {
        Map<String, Object> headers = service.delete(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void deleteWithResponse() {
        Map<String, Object> headers = service.deleteWithResponse("path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void head() {
        Map<String, Object> headers = service.head(Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    @Test
    public void request() {
        Map<String, Object> headers = service.request(HttpMethod.GET, Object.class, "path").req.build().getHeaders();
        assertEquals(headers.get(API_VERSION_HEADER), MICRO_VERSION.toString());
    }

    private static class TestService extends MicroVersionedOpenStackService {
        private String apiVersionHeader;

        private TestService(ServiceType serviceType, MicroVersion microVersion, String apiVersionHeader) {
            super(serviceType, microVersion);
            this.apiVersionHeader = apiVersionHeader;
        }

        @Override
        protected String getApiVersionHeader() {
            return apiVersionHeader;
        }
    }
}
