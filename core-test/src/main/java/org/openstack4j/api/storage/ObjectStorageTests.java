package org.openstack4j.api.storage;

import static org.openstack4j.model.storage.object.SwiftHeaders.CONTAINER_METADATA_PREFIX;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_LENGTH;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_TYPE;
import static org.openstack4j.model.storage.object.SwiftHeaders.ETAG;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.model.storage.object.SwiftObject;
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
        
        List<? extends SwiftContainer> containers = osv3().objectStorage().containers().list();
        assertEquals(2, containers.size());
        assertEquals(containers.get(0).getTotalSize(), 100);
        assertEquals(containers.get(0).getName(), "Test");
        assertEquals(containers.get(1).getName(), "marktwain");
    }
    
    public void containerMetadataTest() throws Exception {
        respondWith(generateContainerMetadataMap(), 204);
        
        Map<String, String> metadata = osv3().objectStorage().containers().getMetadata("Test");
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

    public void objectRetrievalTest() throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        headers.put(CONTENT_LENGTH, "15");
        headers.put(CONTENT_TYPE, "application/json");
        headers.put(ETAG, "12345678901234567890");
        respondWith(headers, 200, "[\"hello world\"]");

        SwiftObject object = osv3().objectStorage().objects().get("test-container", "test-file");
        assertEquals(object.getContainerName(), "test-container");
        assertEquals(object.getName(), "test-file");
        assertEquals(object.getSizeInBytes(), 15);
        assertEquals(object.getMimeType(), "application/json");
        assertEquals(object.getETag(), "12345678901234567890");
    }
}
