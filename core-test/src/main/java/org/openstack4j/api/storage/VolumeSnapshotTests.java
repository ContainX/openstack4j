package org.openstack4j.api.storage;

import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.util.HashMap;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeSnapshot;

import org.testng.annotations.Test;


@Test(suiteName="Block Storage Tests")
public class VolumeSnapshotTests extends AbstractTest {

    private static final String JSON_VOLUME_SNAPSHOTS = "/storage/volumesnapshots.json";
    private static final String JSON_VOLUME_SNAPSHOTS_FILTERED = "/storage/volumesnapshots_filtered.json";
    
    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }
    
    public void listVolumeSnaphots() throws Exception {
        // Check list volumes
        respondWith(JSON_VOLUME_SNAPSHOTS);
        List<? extends VolumeSnapshot> volumes = os().blockStorage().snapshots().list();
        assertEquals(volumes.size(), 2);
        
        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v[12]/\\p{XDigit}*/snapshots"));
        
        // Check list volumes with filters
        respondWith(JSON_VOLUME_SNAPSHOTS_FILTERED);
        final String volName = "snap-vol-test-1";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("display_name", volName);
        List<? extends VolumeSnapshot> filteredVolumes = os().blockStorage().snapshots().list(filters);
        assertEquals(filteredVolumes.size(), 1);
        
        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v[12]/\\p{XDigit}*/snapshots\\?display_name=" + volName));
    }
    
}
