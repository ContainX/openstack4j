package org.openstack4j.api.telemetry;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.MeterSample;
import org.openstack4j.model.telemetry.SampleCriteria;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Meter Sample Tests")
public class MeterSampleTests extends AbstractTest {

    private static final String JSON_METER_SAMPLES = "/telemetry/metersamples.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listMeterSampleTest() throws IOException, InterruptedException {
        respondWith(JSON_METER_SAMPLES);

        List<? extends MeterSample> samples = osv3().telemetry().meters().samples("cpu");
        assertEquals(samples.size(), 3);

        RecordedRequest listRequest = server.takeRequest();
        MeterSample sample = samples.get(0);
        assertEquals(sample.getCounterName(), "cpu");
        assertNotNull(sample.getMetadata());
        assertEquals(sample.getMetadata().size(), 26);
    }
    
    @Test
    public void listMeterSampleWithLimitTest() throws IOException, InterruptedException {
        respondWith(JSON_METER_SAMPLES);

        SampleCriteria sampleCriteria = SampleCriteria.create().limit(3);
        
        osv3().telemetry().meters().samples("cpu",sampleCriteria);
        
        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getRequestLine().contains("limit=3"));
    }
    
    @Test
    public void listMeterSampleWithLargerLimitTest() throws IOException, InterruptedException {
        respondWith(JSON_METER_SAMPLES);

        SampleCriteria sampleCriteria = SampleCriteria.create().limit(100);

        osv3().telemetry().meters().samples("cpu", sampleCriteria);

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getRequestLine().contains("limit=100"));
    }

}
