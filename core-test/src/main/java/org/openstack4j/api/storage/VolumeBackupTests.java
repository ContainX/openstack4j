package org.openstack4j.api.storage;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okio.Buffer;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.VolumeBackup;
import org.openstack4j.model.storage.block.VolumeBackupCreate;
import org.testng.Reporter;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Block Storage Tests")
public class VolumeBackupTests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.BLOCK_STORAGE;
	}

	
	@Test
	public void createVolumeBackupV1() throws Exception {
		respondWith("/storage/v1/volumebackup_create_response.json");
		final String name = "backup1122";
		
		VolumeBackupCreate create = Builders.volumeBackupCreate().volumeId("999b49ff-a813-45cc-aef3-3ec82f089490").container("container123")
				.description("description123").name(name).incremental(false).build();
		VolumeBackup backup = osv3().blockStorage().backups().create(create);
		
		RecordedRequest request = server.takeRequest();
		assertNotNull(request.getHeader("X-Auth-Token"));	
		assertTrue(request.getPath().matches("/v[123]/\\p{XDigit}*/backups" ));
		assertEquals( request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		assertTrue(requestBody.contains("\"volume_id\" : \"999b49ff-a813-45cc-aef3-3ec82f089490\""));

		assertEquals(backup.getName(), name);
		assertNotNull(backup.getId());
		assertEquals(backup.getId(), "7069c687-c85c-45ca-befa-aa78a971fdfe");	 	 
	}

	@Test
	public void createVolumeBackupFromSnapshotV1() throws Exception {
		respondWith("/storage/v1/volumebackup_create_response.json");
		final String name = "backup1122";

		VolumeBackupCreate create = Builders.volumeBackupCreate().volumeId("999b49ff-a813-45cc-aef3-3ec82f089490").container("container123")
				.description("description123").name(name).incremental(false).snapshotId("b4b3258d-555a-4fce-8f53-69cc2ae96d3c").build();
		VolumeBackup backup = osv3().blockStorage().backups().create(create);

		RecordedRequest request = server.takeRequest();
		assertNotNull(request.getHeader("X-Auth-Token"));
		assertTrue(request.getPath().matches("/v[123]/\\p{XDigit}*/backups" ));
		assertEquals( request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		assertTrue(requestBody.contains("\"volume_id\" : \"999b49ff-a813-45cc-aef3-3ec82f089490\""));
		assertTrue(requestBody.contains("\"snapshot_id\" : \"b4b3258d-555a-4fce-8f53-69cc2ae96d3c\""));

		assertEquals(backup.getName(), name);
		assertNotNull(backup.getId());
		assertEquals(backup.getId(), "7069c687-c85c-45ca-befa-aa78a971fdfe");
	}

	
	@Test
	public void deleteVolumeBackupV1() throws Exception {
		respondWith(202);
		String backupId = "1edd8704-a15a-4f44-8a70-49ffc3b1ec3a";
		ActionResponse response = osv3().blockStorage().backups().delete(backupId);
		
		RecordedRequest request = server.takeRequest();
		assertNotNull(request.getHeader("X-Auth-Token"));
		assertTrue(request.getPath().matches("/v[123]/\\p{XDigit}*/backups/" + backupId ));
		assertEquals( request.getMethod(), "DELETE");
		//Reporter.log( request.getPath() , true );
		
		assertTrue(response.isSuccess());
	}

	
	@Test
	public void listVolumeBackupsV1() throws Exception {
		// Check list volumes
		respondWith("/storage/v1/volumebackups.json");
		List<? extends VolumeBackup> backups = osv3().blockStorage().backups().list();
		assertEquals(backups.size(), 3);

		// Check that the list request is the one we expect
		RecordedRequest listRequest = server.takeRequest();
		assertNotNull(listRequest.getHeader("X-Auth-Token"));
		
		Reporter.log( listRequest.getPath() , true );
		assertTrue(listRequest.getPath().matches("/v[123]/\\p{XDigit}*/backups/detail"));

		assertEquals(backups.get(0).getContainer(), "container1122");
		assertEquals(backups.get(0).getVolumeId(), "999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backups.get(0).getName(), "backup1122");
		assertEquals(backups.get(0).getStatus(), VolumeBackup.Status.AVAILABLE);
		assertEquals(backups.get(0).getSize(), 1);
		assertEquals(backups.get(0).getObjectCount(), 22);
		assertEquals(backups.get(0).getZone(), "nova");
		assertNotNull(backups.get(0).getCreated());
		assertFalse(backups.get(0).hasDependent());
		assertFalse(backups.get(0).isIncremental());

	}
	
	@Test
	public void listVolumeBackupsV1WithFilter() throws Exception {
		// Check list volumes
		respondWith("/storage/v1/volumebackups_filtered.json");
		
		final String backupName = "backup1122";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("name", backupName);
		
		List<? extends VolumeBackup> backups = osv3().blockStorage().backups().list(filters);
		assertEquals(backups.size(), 1);

		// Check that the list request is the one we expect
		RecordedRequest listRequest = server.takeRequest();	 
		assertNotNull(listRequest.getHeader("X-Auth-Token")); 
		assertTrue(listRequest.getPath().matches("/v[123]/\\p{XDigit}*/backups/detail\\?name=" + backupName));
		
		assertEquals(backups.get(0).getContainer(), "container1122");
		assertEquals(backups.get(0).getVolumeId(), "999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backups.get(0).getName(), backupName);
		assertEquals(backups.get(0).getStatus(), VolumeBackup.Status.AVAILABLE);
		assertEquals(backups.get(0).getSize(), 1);
		assertEquals(backups.get(0).getObjectCount(), 22);
		assertEquals(backups.get(0).getZone(), "nova");
		assertNotNull(backups.get(0).getCreated());
		assertFalse(backups.get(0).hasDependent());
		assertFalse(backups.get(0).isIncremental());

	}

	@Test
	public void getVolumeBackupV1() throws Exception {
		// Check get volume
		respondWith("/storage/v1/volumebackup.json");
		String id="735359d5-9584-4046-94d3-5ffc47be84f5";
		VolumeBackup backup = osv3().blockStorage().backups().get(id);

		RecordedRequest getRequest = server.takeRequest();
		assertNotNull(getRequest.getHeader("X-Auth-Token"));
		assertTrue(getRequest.getPath().matches("/v[123]/\\p{XDigit}*/backups/"+id));

		assertEquals(backup.getId(), "735359d5-9584-4046-94d3-5ffc47be84f5");
		assertEquals(backup.getContainer(), "test999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getVolumeId(), "999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getName(), "backup999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getStatus(), VolumeBackup.Status.AVAILABLE);
		assertEquals(backup.getSize(), 1);
		assertEquals(backup.getObjectCount(), 22);
		assertEquals(backup.getZone(), "nova");
		assertNotNull(backup.getCreated());
		assertEquals(backup.getDescription(), "by API999b49ff-a813-45cc-aef3-3ec82f089490");
		assertFalse(backup.hasDependent());
		assertFalse(backup.isIncremental());

	}


	@Test
	public void getVolumeBackupFromSnapshotV1() throws Exception {
		// Check get volume
		respondWith("/storage/v1/volumebackup_from_snapshot.json");
		String id="735359d5-9584-4046-94d3-5ffc47be84f5";
		VolumeBackup backup = osv3().blockStorage().backups().get(id);

		RecordedRequest getRequest = server.takeRequest();
		assertNotNull(getRequest.getHeader("X-Auth-Token"));
		assertTrue(getRequest.getPath().matches("/v[123]/\\p{XDigit}*/backups/"+id));

		assertEquals(backup.getId(), "735359d5-9584-4046-94d3-5ffc47be84f5");
		assertEquals(backup.getContainer(), "test999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getVolumeId(), "999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getName(), "backup999b49ff-a813-45cc-aef3-3ec82f089490");
		assertEquals(backup.getSnapshotId(), "b4b3258d-555a-4fce-8f53-69cc2ae96d3c");
		assertEquals(backup.getStatus(), VolumeBackup.Status.AVAILABLE);
		assertEquals(backup.getSize(), 1);
		assertEquals(backup.getObjectCount(), 22);
		assertEquals(backup.getZone(), "nova");
		assertNotNull(backup.getCreated());
		assertEquals(backup.getDescription(), "by API999b49ff-a813-45cc-aef3-3ec82f089490");
		assertFalse(backup.hasDependent());
		assertFalse(backup.isIncremental());

	}

}
