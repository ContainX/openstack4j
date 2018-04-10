package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.cloudkitty.ServiceInfo;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/info", enabled = true)
public class InfoServiceTests extends AbstractTest {

    private final String JSON_CONFIG = "/cloudkitty/info-config.json";
    private final String JSON_SERVICES = "/cloudkitty/info-services.json";
    private final String JSON_SERVICE = "/cloudkitty/info-service.json";

    private final String serviceId = "compute";
    private final String unit = "instance";

    public void testConfig() throws IOException {
        respondWith(JSON_CONFIG);

        Map<String, String> config = osv3().rating().info().config();

        assertEquals(config.get("foo"), "bar");
    }

    public void testListServices() throws IOException {
        respondWith(JSON_SERVICES);

        List<? extends ServiceInfo> list = osv3().rating().info().services();

        assertEquals(list.size(), 1);

        performAssertions(list.get(0));

    }

    public void testGetService() throws IOException {
        respondWith(JSON_SERVICE);

        ServiceInfo info = osv3().rating().info().service(serviceId);

        performAssertions(info);
    }

    private void performAssertions(ServiceInfo serviceInfo) {
        assertEquals(serviceInfo.getServiceId(), serviceId);
        assertEquals(serviceInfo.getUnit(), unit);
        assertEquals(serviceInfo.getMetadata().size(), 3);
        assertEquals(serviceInfo.getMetadata().get(0), "resource_id");
        assertEquals(serviceInfo.getMetadata().get(1), "flavor");
        assertEquals(serviceInfo.getMetadata().get(2), "availability_zone");
    }
    @Override
    protected Service service() {
        return Service.RATING;
    }
}
