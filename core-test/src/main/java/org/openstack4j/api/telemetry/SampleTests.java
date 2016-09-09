package org.openstack4j.api.telemetry;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Sample;
import org.testng.annotations.Test;

@Test(suiteName = "Sample Tests")
public class SampleTests extends AbstractTest {
    private static final String JSON_SAMPLES = "/telemetry/samples.json";
    private static final String JSON_SAMPLE = "/telemetry/sample.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listSampleTest() throws IOException {
        respondWith(JSON_SAMPLES);

        List<? extends Sample> samples = osv2().telemetry().samples().list();
        assertEquals(samples.size(), 2);

        Sample sample = samples.get(0);
        assertEquals(sample.getMeter(), "image.size");
        assertNotNull(sample.getMetadata());
    }

    @Test
    public void getSampleTest() throws IOException {
        respondWith(JSON_SAMPLE);
        Sample sample = osv2().telemetry().samples().get("1e93a890-3732-11e6-a491-005056ac9b87");
        assertEquals(sample.getMeter(), "image.size");
        assertNotNull(sample.getMetadata());
    }

}
