package org.openstack4j.api.storage;

import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.util.HashMap;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.Volume;

import org.testng.annotations.Test;


@Test(suiteName="Block Storage Tests")
public class VolumeTests extends AbstractTest {

    private static final String JSON_VOLUMES = "/storage/volumes.json";
    private static final String JSON_VOLUMES_FILTERED = "/storage/volumes_filtered.json";
    
    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }
    
    public void listVolumes() throws Exception {
        // Check list volumes
        respondWith(JSON_VOLUMES);
        List<? extends Volume> volumes = os().blockStorage().volumes().list();
        assertEquals(volumes.size(), 3);
        
        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/detail"));
        
        // Check list volumes with filters
        respondWith(JSON_VOLUMES_FILTERED);
        final String volName = "vol-test-1";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("display_name", volName);
        List<? extends Volume> filteredVolumes = os().blockStorage().volumes().list(filters);
        assertEquals(filteredVolumes.size(), 2);
        
        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/detail\\?display_name=" + volName));
    }
    
}
