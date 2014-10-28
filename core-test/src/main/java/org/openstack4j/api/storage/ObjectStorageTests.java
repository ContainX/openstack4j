package org.openstack4j.api.storage;

import static org.openstack4j.model.storage.object.SwiftHeaders.CONTAINER_METADATA_PREFIX;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

@Test(suiteName="Object Storage Tests")
public class ObjectStorageTests extends AbstractTest {

    private static final String JSON_CONTAINERS = "/storage/containers.json";
    private static final String NAME_BOOK = "Book";
    private static final String NAME_YEAR = "Year";
    
    @Override
    protected Service service() {
        return Service.OBJECT_STORAGE;
    }

    public void containerListingTest() throws Exception {
        respondWith(JSON_CONTAINERS);
        
        List<? extends SwiftContainer> containers = os().objectStorage().containers().list();
        assertEquals(2, containers.size());
        assertEquals(containers.get(0).getTotalSize(), 100);
        assertEquals(containers.get(0).getName(), "Test");
        assertEquals(containers.get(1).getName(), "marktwain");
    }
    
    public void containerMetadataTest() throws Exception {
        respondWith(generateContainerMetadataMap(), 204);
        
        Map<String, String> metadata = os().objectStorage().containers().getMetadata("Test");
        assertNotNull(metadata);
        assertEquals(metadata.get(NAME_YEAR), "2000");
        assertEquals(metadata.get(NAME_BOOK), "TestBook");
    }
    
    private Map<String, String> generateContainerMetadataMap() {
        Map<String, String> metadata = Maps.newHashMap();
        metadata.put(CONTAINER_METADATA_PREFIX+NAME_BOOK, "TestBook");
        metadata.put(CONTAINER_METADATA_PREFIX+NAME_YEAR, "2000");
        return metadata;
    }
}
