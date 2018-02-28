package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;


@Test(suiteName = "Rating/hashMapMapping", enabled = true)
public class HashMapMappingServiceTests extends AbstractTest {


    private static final String JSON_MAPPING = "/cloudkitty/hashmap-mapping.json";
    private static final String JSON_MAPPINGS = "/cloudkitty/hashmap-mappings.json";
    private static final String JSON_GROUP = "/cloudkitty/hashmap-group.json";

    private static final String cost = "4.2";
    private static final String fieldId = "ac55b000-a05b-4832-b2ff-265a034886ab";
    private static final String mappingId = "39dbd39d-f663-4444-a795-fb19d81af136";
    private static final String tenantId = "7977999e-2e25-11e6-a8b2-df30b233ffcb";
    private static final String type = "flat";
    private static final String value  = "m1.micro";

    private static final String groupId = "afe898cb-86d8-4557-ad67-f4f01891bbee";
    private static final String groupName = "instance_rating";

    public void testListMappings() throws IOException {
        respondWith(JSON_MAPPINGS);

        List<? extends Mapping> list = osv3().rating().hashmap().mapping().list();

        assertEquals(list.size(), 1);
        performAssertions(list.get(0));
    }

    public void testGetMapping() throws IOException {
        respondWith(JSON_MAPPING);

        Mapping mapping = osv3().rating().hashmap().mapping().get(mappingId);

        performAssertions(mapping);
    }

    public void testCreateMapping() throws IOException {
        respondWith(JSON_MAPPING);

        Mapping mapping = osv3().rating().hashmap().mapping().create(
                Builders.cloudkitty().hashmap().mapping()
                .cost(Float.valueOf(cost))
                .fieldId(fieldId)
                .id(mappingId)
                .tenantId(tenantId)
                .type(Mapping.Type.value(type))
                .value(value)
                .build()
        );

        performAssertions(mapping);
    }

    public void testUpdateMapping() throws IOException {
        respondWith(302);

        ActionResponse response = osv3().rating().hashmap().mapping().update(
                mappingId,
                Builders.cloudkitty().hashmap().mapping()
                        .cost(Float.valueOf(cost))
                        .fieldId(fieldId)
                        .id(mappingId)
                        .tenantId(tenantId)
                        .type(Mapping.Type.value(type))
                        .value(value)
                        .build()
        );

        assertTrue(response.isSuccess());
    }

    public void testDeleteMapping() throws IOException {
        respondWith(204);

        ActionResponse response = osv3().rating().hashmap().mapping().delete(mappingId);

        assertTrue(response.isSuccess());
    }

    public void testGetMappingGroup() throws IOException {
        respondWith(JSON_GROUP);

        Group group = osv3().rating().hashmap().mapping().group(mappingId);

        assertEquals(group.getName(), groupName);
        assertEquals(group.getId(), groupId);
    }



    private void performAssertions(Mapping mapping) {
        assertEquals(mapping.getCost(), Float.valueOf(cost));
        assertEquals(mapping.getFieldId(), fieldId);
        assertEquals(mapping.getId(), mappingId);
        assertEquals(mapping.getTenantId(), tenantId);
        assertEquals(mapping.getType(), Mapping.Type.value(type));
        assertEquals(mapping.getValue(), value);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
