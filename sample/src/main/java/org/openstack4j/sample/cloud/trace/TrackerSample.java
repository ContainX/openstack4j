/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample.cloud.trace;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloud.trace.constants.TrackerStatus;
import org.openstack4j.openstack.cloud.trace.v1.domain.Tracker;
import org.openstack4j.openstack.cloud.trace.v1.domain.TrackerUpdate;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(suiteName = "CloudTrace/Tracker/Sample")
public class TrackerSample extends AbstractSample {

	String bucketName = randomName();
	boolean newCreated = false;
	Tracker tracker = null;

	@BeforeClass
	public void prepare() {
		// due to only one tracker is support for now, we try list first
		List<Tracker> trackers = osclient.cloudTraceV1().trakers().list();
		if (trackers.size() == 0) {
			// if no tracker has been created, try create one
			osclient.objectStorage().containers().create(bucketName);
			tracker = osclient.cloudTraceV1().trakers().create(bucketName);
			newCreated = true;
		} else {
			tracker = trackers.get(0);
		}
	}

	@AfterClass
	public void cleanup() {
		if (newCreated) {
			ActionResponse delete = osclient.cloudTraceV1().trakers().delete(tracker.getName());
			Assert.assertTrue(delete.isSuccess());
		}
	}

	@Test(priority = 1)
	public void testGetTraker() {
		Tracker get = osclient.cloudTraceV1().trakers().get(tracker.getName());
		Assert.assertEquals(get.getName(), tracker.getName());
		Assert.assertEquals(get.getBucketName(), tracker.getBucketName());
		Assert.assertEquals(get.getFilePrefixName(), tracker.getFilePrefixName());
		Assert.assertEquals(get.getStatus(), tracker.getStatus());
	}

	@Test(priority = 2)
	public void testUpdateTraker() {
		// update tracker
		TrackerStatus newStatus = tracker.getStatus().equals(TrackerStatus.Enabled) ? TrackerStatus.Disabled
				: TrackerStatus.Enabled;
		TrackerUpdate update = TrackerUpdate.builder().trackerName(tracker.getName())
				.bucketName(tracker.getBucketName()).filePrefixName("SDK-unittest").status(newStatus).build();
		ActionResponse response = osclient.cloudTraceV1().trakers().update(update);
		Assert.assertTrue(response.isSuccess());

		// assert
		Tracker get = osclient.cloudTraceV1().trakers().get(tracker.getName());
		Assert.assertEquals(get.getName(), tracker.getName());
		Assert.assertEquals(get.getBucketName(), tracker.getBucketName());
		Assert.assertEquals(get.getFilePrefixName(), "SDK-unittest");
		Assert.assertEquals(get.getStatus(), newStatus);

		// update back to the original one
		TrackerUpdate update2 = TrackerUpdate.builder().trackerName(tracker.getName())
				.bucketName(tracker.getBucketName()).filePrefixName(tracker.getFilePrefixName())
				.status(tracker.getStatus()).build();
		ActionResponse response2 = osclient.cloudTraceV1().trakers().update(update2);
		Assert.assertTrue(response2.isSuccess());

		// assert restored tracker
		Tracker get2 = osclient.cloudTraceV1().trakers().get(tracker.getName());
		Assert.assertEquals(get2.getName(), tracker.getName());
		Assert.assertEquals(get2.getBucketName(), tracker.getBucketName());
		Assert.assertEquals(get2.getFilePrefixName(), tracker.getFilePrefixName());
		Assert.assertEquals(get2.getStatus(), tracker.getStatus());
	}

	@Test(priority = 3)
	public void testDeleteTraker() {
		ActionResponse delete = osclient.cloudTraceV1().trakers().delete(tracker.getName());
		Assert.assertTrue(delete.isSuccess());

		// assert delete success
		List<Tracker> trackers = osclient.cloudTraceV1().trakers().list();
		Assert.assertTrue(trackers.size() == 0);
	}

	@Test(priority = 4)
	public void testCreateTraker() {
		// create it again
		Tracker create = osclient.cloudTraceV1().trakers().create(tracker.getBucketName(), tracker.getFilePrefixName());

		// restore status
		if (!create.getStatus().equals(tracker.getStatus())) {
			TrackerUpdate update = TrackerUpdate.builder().trackerName(tracker.getName())
					.bucketName(tracker.getBucketName()).status(tracker.getStatus()).build();
			ActionResponse response = osclient.cloudTraceV1().trakers().update(update);
			Assert.assertTrue(response.isSuccess());
		}
	}

}
