package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/hashMapField", enabled = true)
public class HashMapServiceTests extends AbstractTest {

    private static final String JSON_TYPES = "/cloudkitty/hashmap-types.json";
    private static final String JSON_SERVICE = "/cloudkitty/hashmap-service.json";
    private static final String JSON_SERVICES = "/cloudkitty/hashmap-services.json";

    private static final String serviceId = "a733d0e1-1ec9-4800-8df8-671e4affd017";
    private static final String serviceName = "compute";

    public void testListTypes() throws IOException {
        respondWith(JSON_TYPES);

        List<Mapping.Type> list = osv3().rating().hashmap().types();

        assertEquals(list.size(), 2);
        assertEquals(list.get(0), Mapping.Type.FLAT);
        assertEquals(list.get(1), Mapping.Type.RATE);
    }

    public void testList() throws IOException {
        respondWith(JSON_SERVICES);

        List<? extends org.openstack4j.model.cloudkitty.hashmap.Service> list = osv3().rating().hashmap().service().list();

        assertEquals(list.size(), 1);
        performAssertions(list.get(0));
    }

    public void testGet() throws IOException {
        respondWith(JSON_SERVICE);

        performAssertions(osv3().rating().hashmap().service().get(serviceId));
    }

    public void testCreate() throws IOException {
        respondWith(JSON_SERVICE);

        performAssertions(
                osv3().rating().hashmap().service().create(
                        Builders.cloudkitty().hashmap().service().name(serviceName).build()
                )
        );
    }

    public void testDelete() throws IOException {
        respondWith(204 );
        ActionResponse response = osv3().rating().hashmap().service().delete(serviceId);
        assertTrue(response.isSuccess());
    }

    private void performAssertions(org.openstack4j.model.cloudkitty.hashmap.Service service) {
        assertEquals(service.getId(), serviceId);
        assertEquals(service.getName(), serviceName);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
