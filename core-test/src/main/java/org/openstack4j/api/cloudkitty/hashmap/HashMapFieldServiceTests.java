package org.openstack4j.api.cloudkitty.hashmap;


import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.hashmap.Field;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/hashMapField", enabled = true)
public class HashMapFieldServiceTests extends AbstractTest {
    private static String fieldId = "ac55b000-a05b-4832-b2ff-265a034886ab";
    private static String fieldName = "image_id";
    private static String serviceId = "a733d0e1-1ec9-4800-8df8-671e4affd017";

    private static final String FIELDS_JSON = "/cloudkitty/hashmap-fields.json";
    private static final String FIELD_JSON = "/cloudkitty/hashmap-field.json";


    public void testListFields() throws IOException {
        respondWith(FIELDS_JSON);
        List<? extends Field> list = osv3().rating().hashmap().field().list();

        assertEquals(list.size(), 1);
        performAssertions(list.get(0));
    }

    public void testGetField() throws IOException {
        respondWith(FIELD_JSON);

        Field field = osv3().rating().hashmap().field().get(fieldId);
        performAssertions(field);
    }

    public void testCreateField() throws IOException {
        respondWith(FIELD_JSON);

        Field field = osv3().rating().hashmap().field().create(
                Builders.cloudkitty().hashmap().field()
                .name(fieldName)
                .serviceId(serviceId)
                .build()
        );

        performAssertions(field);
    }

    public void testDeleteField() throws IOException {
        respondWith(204);
        ActionResponse resut = osv3().rating().hashmap().field().delete(fieldId);
        assertTrue(resut.isSuccess());
    }

    private void performAssertions(Field field) {
        assertEquals(field.getId(), fieldId);
        assertEquals(field.getName(), fieldName);
        assertEquals(field.getServiceId(), serviceId);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
