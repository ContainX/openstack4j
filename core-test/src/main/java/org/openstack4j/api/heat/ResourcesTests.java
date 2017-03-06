package org.openstack4j.api.heat;

import org.openstack4j.api.*;
import org.openstack4j.model.common.*;
import org.openstack4j.model.heat.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static org.testng.Assert.*;

@Test(suiteName="heat/resources", enabled = true)
public class ResourcesTests extends AbstractTest {

    private static final String METADATA="/heat/metadata.json";

    @SuppressWarnings("unchecked")
    public void testGetResourceMetadata() throws IOException {
        respondWith(METADATA);
        Map<String, Object> metadata = osv3().heat().resources().getMetadata("name", "id", "resource");
        assertEquals(metadata.size(), 1);
        Map<String, Object> inner = (Map)metadata.get("metadata");
        assertEquals(inner.size(), 2);
        assertEquals(inner.get("scaling_in_progress"), false);
        Map<String, String> capacity = (Map)inner.get("cooldown");
        assertEquals(capacity.get("2016-10-05T21:13:29.736841"), "ExactCapacity : 4");
    }


    public void testSignal() throws IOException {
        respondWith(200);
        ActionResponse result = osv3().heat().resources().signal("name", "id", "resource");
        assertTrue(result.isSuccess());
    }

    public void testMarkUnhealthy() throws IOException {
        respondWith(200);
        ResourceHealth health = Builders.resourceHealth().
                markUnhealthy(true)
                .resourceStatusReason("it is inevitable, Mr. Anderson")
                .build();
        ActionResponse result = osv3().heat().resources().markUnhealthy("name", "id", "resource", health);
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.ORCHESTRATION;
    }
}
