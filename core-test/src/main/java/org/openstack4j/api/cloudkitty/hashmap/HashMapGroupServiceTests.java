package org.openstack4j.api.cloudkitty.hashmap;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.hashmap.Group;
import org.openstack4j.model.cloudkitty.hashmap.Mapping;
import org.openstack4j.model.cloudkitty.hashmap.Threshold;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/hashMapGroup", enabled = true)
public class HashMapGroupServiceTests extends AbstractTest {

    private static final String JSON_GROUP = "/cloudkitty/hashmap-group.json";
    private static final String JSON_GROUPS = "/cloudkitty/hashmap-groups.json";
    private static final String JSON_THRESHOLDS = "/cloudkitty/hashmap-thresholds.json";
    private static final String JSON_MAPPINGS = "/cloudkitty/hashmap-mappings.json";

    private static final String groupId = "afe898cb-86d8-4557-ad67-f4f01891bbee";
    private static final String name = "instance_rating";

    public void testGroupList() throws IOException {
        respondWith(JSON_GROUPS);

        Group group = osv3().rating().hashmap().group().list().get(0);

        performAssertions(group);
    }

    public void testGroupGet() throws IOException {
        respondWith(JSON_GROUP);

        Group group = osv3().rating().hashmap().group().get(groupId);

        performAssertions(group);
    }

    public void testGroupCreate() throws IOException {
        respondWith(JSON_GROUP);

        Group payload = Builders.cloudkitty()
                .hashmap().group()
                .id(groupId)
                .name(name)
                .build();
        Group group = osv3().rating().hashmap().group().create(payload);

        performAssertions(payload);
        performAssertions(group);
    }

    public void testGroupDelete() throws IOException {
        respondWith(204);
        ActionResponse response = osv3().rating().hashmap().group().delete(groupId, true);
        assertTrue(response.isSuccess());
    }

    public void testGroupGetMappings() throws IOException {
        respondWith(JSON_MAPPINGS);
        List<? extends Mapping> mappings = osv3().rating().hashmap().group().mappings(groupId);

        assertEquals(mappings.size(), 1);

    }

    public void testGroupGetThresholds() throws IOException {
        respondWith(JSON_THRESHOLDS);
        List<? extends Threshold> thresholds = osv3().rating().hashmap().group().thresholds(groupId);

        assertEquals(thresholds.size(), 1);
    }

    public void performAssertions(Group group) {
        assertEquals(group.getId(), groupId);
        assertEquals(group.getName(), name);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
