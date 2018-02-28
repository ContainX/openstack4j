package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.CollectorInfos;
import org.openstack4j.model.cloudkitty.ServiceToCollectorMapping;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/collector", enabled = true)
public class CollectorServiceTests extends AbstractTest {

    private final String JSON_COLLECTORINFOS = "/cloudkitty/collector-collectorinfos.json";
    private final String JSON_STCMAPPINGS = "/cloudkitty/collector-servicetocollectionmappings.json";
    private final String JSON_STCMAPPING = "/cloudkitty/collector-servicetocollectionmapping.json";


    private final String collector = "ceilometer";
    private final String service = "compute";

    public void testListMappings() throws IOException {
        respondWith(JSON_STCMAPPINGS);

        List<? extends ServiceToCollectorMapping> mappingList = osv3().rating().collector().list();

        assertEquals(mappingList.size(), 1);
        performAssertions(mappingList.get(0));
    }

    public void testGetMapping() throws IOException {
        respondWith(JSON_STCMAPPING);

        ServiceToCollectorMapping mapping = osv3().rating().collector().get(service);

        performAssertions(mapping);
    }

    public void testCreateMapping() throws IOException {
        respondWith(JSON_STCMAPPING);

        ServiceToCollectorMapping mapping = osv3().rating().collector().create(collector, service);

        performAssertions(mapping);
    }

    public void testDeleteMapping() throws IOException {
        respondWith(204);

        ActionResponse response = osv3().rating().collector().delete(service);

        assertTrue(response.isSuccess());
    }

    public void testSetState() throws IOException {
        respondWith(JSON_COLLECTORINFOS);

        CollectorInfos infos = osv3().rating().collector().setState(collector, Builders.cloudkitty()
                .collectorInfos()
                .enabled(true)
                .name(collector)
                .build()
        );

        assertEquals(infos.getName(), collector);
        assertTrue(infos.isEnabled());
    }

    private void performAssertions(ServiceToCollectorMapping mapping) {
        assertEquals(mapping.getCollector(), collector);
        assertEquals(mapping.getService(), service);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
