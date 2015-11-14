package org.openstack4j.api.telemetry;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Event;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.Trait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(suiteName="Sample Tests")
public class SampleTests extends AbstractTest {

    private static final String JSON_SAMPLES = "/telemetry/samples.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listTest() throws IOException {
        respondWith(JSON_SAMPLES);

        List<? extends Sample> samples = os().telemetry().meters().samples("cpu");
        assertEquals(samples.size(), 3);

        Sample sample = samples.get(0);
        assertEquals(sample.getCounterName(), "cpu");
        assertNotNull(sample.getMetadata());
        assertEquals(sample.getMetadata().size(), 26);

    }

}
