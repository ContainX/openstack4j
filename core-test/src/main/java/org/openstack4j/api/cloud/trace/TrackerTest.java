/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.api.cloud.trace;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloud.trace.constants.TrackerStatus;
import org.openstack4j.openstack.cloud.trace.v1.domain.Tracker;
import org.openstack4j.openstack.cloud.trace.v1.domain.TrackerUpdate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "CTSV1/Tracker", enabled = true)
public class TrackerTest extends AbstractTest {

	@Test
	public void testGetTracker() throws IOException, InterruptedException {
		respondWith("/cloud-trace/get_tracker_response.json");

		Tracker tracker = osv3().cloudTraceV1().trackers().get("tracker-name");
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker?tracker_name=tracker-name");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(tracker.getName(), "system");
		Assert.assertEquals(tracker.getBucketName(), "my_created_bucket");
		Assert.assertEquals(tracker.getDetail(), "noBucket");
		Assert.assertEquals(tracker.getFilePrefixName(), "some_folder");
		Assert.assertEquals(tracker.getStatus(), TrackerStatus.Disabled);
	}

	@Test
	public void testUpdateTracker() throws IOException, InterruptedException {
		respondWith("/cloud-trace/update_tracker_response.json");

		TrackerUpdate update = TrackerUpdate.builder().trackerName("tracker-name").bucketName("bucket-name")
				.filePrefixName("file-prefix").status(TrackerStatus.Disabled).build();
		
		Tracker tracker = osv3().cloudTraceV1().trackers().update(update);
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker/tracker-name");
		Assert.assertEquals(request.getMethod(), "PUT");


		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("bucket-name", node.get("bucket_name").asText());
		Assert.assertEquals("file-prefix", node.get("file_prefix_name").asText());
		Assert.assertEquals("disabled", node.get("status").asText());

		Assert.assertEquals(tracker.getName(), "system");
		Assert.assertEquals(tracker.getBucketName(), "my_created_bucket");
		Assert.assertEquals(tracker.getDetail(), null);
		Assert.assertEquals(tracker.getFilePrefixName(), "some_folder");
		Assert.assertEquals(tracker.getStatus(), TrackerStatus.Disabled);
	}
	
	@Test
	public void testCreateTracker() throws IOException, InterruptedException {
		respondWith("/cloud-trace/create_tracker_response.json");

		Tracker tracker = osv3().cloudTraceV1().trackers().create("bucket-name", "file-prefix-name");
		
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker");
		Assert.assertEquals(request.getMethod(), "POST");
		
		String requestBody = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("bucket-name", node.get("bucket_name").asText());
		Assert.assertEquals("file-prefix-name", node.get("file_prefix_name").asText());

		Assert.assertEquals(tracker.getName(), "system");
		Assert.assertEquals(tracker.getBucketName(), "defaultbucket");
		Assert.assertEquals(tracker.getDetail(), null);
		Assert.assertEquals(tracker.getFilePrefixName(), "mytracker1");
		Assert.assertEquals(tracker.getStatus(), TrackerStatus.Enabled);
	}
	
	@Test
	public void testDeleteTracker() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().cloudTraceV1().trackers().delete("tracker-name");
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker?tracker_name=tracker-name");
		Assert.assertEquals(request.getMethod(), "DELETE");
		
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testDeleteAllTracker() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse response = osv3().cloudTraceV1().trackers().deleteAll();
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker");
		Assert.assertEquals(request.getMethod(), "DELETE");
		
		Assert.assertTrue(response.isSuccess());
	}

	
	@Test
	public void testListTracker() throws IOException, InterruptedException {
		respondWith("/cloud-trace/list_tracker_response.json");

		List<Tracker> trackers = osv3().cloudTraceV1().trackers().list();
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/tracker");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertTrue(trackers.size() == 1);
		
		Tracker tracker = trackers.get(0);
		Assert.assertEquals(tracker.getName(), "system");
		Assert.assertEquals(tracker.getBucketName(), "my_created_bucket");
		Assert.assertEquals(tracker.getDetail(), "noBucket");
		Assert.assertEquals(tracker.getFilePrefixName(), "some_folder");
		Assert.assertEquals(tracker.getStatus(), TrackerStatus.Disabled);
	}

	@Override
	protected Service service() {
		return Service.CLOUD_TRACE;
	}

}
