package org.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.SkipTest;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeAttachment;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.testng.annotations.Test;



@Test(suiteName="Block Storage Tests")
public class VolumeTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }
    
    @Test
    public void listVolumesV1() throws Exception {
        // Check list volumes
        respondWith("/storage/v1/volumes.json");
        List<? extends Volume> volumes = osv3().blockStorage().volumes().list();
        assertEquals(volumes.size(), 3);
        assertEquals(volumes.get(0).getTenantId(), "b0b5ed7ae06049688349fe43737796d4");
        
        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/detail"));
        
        // Check list volumes with filters
        respondWith("/storage/v1/volumes_filtered.json");
        final String volName = "vol-test-1";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("display_name", volName);
        List<? extends Volume> filteredVolumes = osv3().blockStorage().volumes().list(filters);
        assertEquals(filteredVolumes.size(), 2);
        
        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/detail\\?display_name=" + volName));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void getVolumeV1() throws Exception {
        // Check get volume
        respondWith("/storage/v1/volume.json");
        Volume volume = osv3().blockStorage().volumes().get("8a9287b7-4f4d-4213-8d75-63470f19f27c");
        
        RecordedRequest getRequest = server.takeRequest();
        assertTrue(getRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/8a9287b7-4f4d-4213-8d75-63470f19f27c"));
        
        assertEquals(volume.getId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");
        assertEquals(volume.getDisplayName(), "vol-test");
        assertEquals(volume.getDisplayDescription(), "a description");
        assertNotNull(volume.getCreated());
        assertEquals(volume.getZone(), "nova");
        assertEquals(volume.getSize(), 100);
        assertEquals(volume.getStatus(), Volume.Status.IN_USE);
        assertEquals(volume.getSnapshotId(), "22222222-2222-2222-2222-222222222222");
        assertEquals(volume.getSourceVolid(), "11111111-1111-1111-1111-111111111111");
        assertEquals(volume.getVolumeType(), "Gold");
        
        assertNotNull(volume.getMetaData());
        Map<String, String> metadata = volume.getMetaData();
        assertEquals(metadata.get("readonly"), "False");
        assertEquals(metadata.get("attached_mode"), "rw");
        
        assertNotNull(volume.getAttachments());
        List<VolumeAttachment> attachments = (List<VolumeAttachment>) volume.getAttachments();
        assertEquals(attachments.get(0).getDevice(), "/dev/vdd");
        assertEquals(attachments.get(0).getHostname(), "myhost");
        assertEquals(attachments.get(0).getId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");
        assertEquals(attachments.get(0).getServerId(), "eaa6a54d-35c1-40ce-831d-bb61f991e1a9");
        assertEquals(attachments.get(0).getVolumeId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");

        assertEquals(volume.getTenantId(), "b0b5ed7ae06049688349fe43737796d4");
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void getVolumeV2() throws Exception {
        // Check get volume
        respondWith("/storage/v2/volume.json");
        Volume volume = osv3().blockStorage().volumes().get("8a9287b7-4f4d-4213-8d75-63470f19f27c");
        
        RecordedRequest getRequest = server.takeRequest();
        assertTrue(getRequest.getPath().matches("/v[12]/\\p{XDigit}*/volumes/8a9287b7-4f4d-4213-8d75-63470f19f27c"));
        
        assertEquals(volume.getId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");
        assertEquals(volume.getName(), "test-volume");
        assertEquals(volume.getDescription(), "a description");
        assertNotNull(volume.getCreated());
        assertEquals(volume.getZone(), "nova");
        assertEquals(volume.getSize(), 100);
        assertEquals(volume.getStatus(), Volume.Status.IN_USE);
        assertEquals(volume.getSnapshotId(), "22222222-2222-2222-2222-222222222222");
        assertEquals(volume.getSourceVolid(), "11111111-1111-1111-1111-111111111111");
        assertEquals(volume.getVolumeType(), "Gold");
        
        assertNotNull(volume.getMetaData());
        Map<String, String> metadata = volume.getMetaData();
        assertEquals(metadata.get("readonly"), "False");
        assertEquals(metadata.get("attached_mode"), "rw");
        
        assertNotNull(volume.getAttachments());
        List<VolumeAttachment> attachments = (List<VolumeAttachment>) volume.getAttachments();
        assertEquals(attachments.get(0).getDevice(), "/dev/vdd");
        assertEquals(attachments.get(0).getHostname(), "myhost");
        assertEquals(attachments.get(0).getId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");
        assertEquals(attachments.get(0).getServerId(), "eaa6a54d-35c1-40ce-831d-bb61f991e1a9");
        assertEquals(attachments.get(0).getVolumeId(), "8a9287b7-4f4d-4213-8d75-63470f19f27c");
    }
    
    
    @Test
    public void testVolumesWithBootableAndEncyrpted() throws Exception {
        // Check list volumes
        respondWith("/storage/v1/volumes-bootable.json");
        List<? extends Volume> volumes = osv3().blockStorage().volumes().list();
        assertEquals(volumes.size(), 2);
        assertEquals(volumes.get(0).getTenantId(), "b0b5ed7ae06049688349fe43737796d4");
        assertEquals(volumes.get(0).bootable(), false);
        assertEquals(volumes.get(0).encrypted(), false);
        
        assertEquals(volumes.get(1).getTenantId(), "b0b5ed7ae06049688349fe43737796d4");
        assertEquals(volumes.get(1).bootable(), true);
        assertEquals(volumes.get(1).encrypted(), true);
        
        
    }
    
    
    @Test
    public void CreateVolumeV2WithMultiattach() throws Exception {

        respondWith("/storage/v2/createVolume-muitiattach.json");
        
        VolumeBuilder volumeBuilder = Builders.volume();
		volumeBuilder.size(10);
		volumeBuilder.name("test_openstack4j");
		volumeBuilder.description("test");
		volumeBuilder.multiattach(true);	
		Volume volume = osv2().blockStorage().volumes().create(volumeBuilder.build());
		        
		server.takeRequest();	 
		
        assertEquals(volume.getSize(), 10);               
        assertEquals(volume.multiattach(), Boolean.TRUE);                        
    }
}
