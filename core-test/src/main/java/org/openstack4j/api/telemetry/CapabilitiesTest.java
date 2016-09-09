package org.openstack4j.api.telemetry;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Capabilities;
import org.testng.annotations.Test;

@Test(suiteName = "Capabilities Tests")
public class CapabilitiesTest extends AbstractTest {
    private static final String JSON_CAPABILITIES = "/telemetry/capabilities.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void getSampleTest() throws IOException {
        respondWith(JSON_CAPABILITIES);
        Capabilities capabilities = osv2().telemetry().capabilities().get();
        assertNotNull(capabilities);
    }

}
