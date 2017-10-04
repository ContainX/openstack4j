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
package com.huawei.openstack4j.functional.cloud.trace;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloud.trace.constants.TrackerStatus;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.Tracker;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.TrackerUpdate;

@Test(suiteName = "CloudTrace/Tracker/Test")
public class TrackerTest extends AbstractTest {
	
	// 改成存储的 bucket name
	String bucketName = "sunny-tracer";
	boolean newCreated = false;
	Tracker tracker = null;

	@BeforeClass
	public void prepare() {
		// due to only one tracker is support for now, we try list first
		List<Tracker> trackers = osclient.cloudTraceV1().trackers().list();
		if (trackers.size() == 0) {
			// if no tracker has been created, try create one
			tracker = osclient.cloudTraceV1().trackers().create(bucketName, "C2Iv");
			newCreated = true;
		} else {
			tracker = trackers.get(0);
		}
	}

	@AfterClass
	public void cleanup() {
		if (newCreated) {
			ActionResponse delete = osclient.cloudTraceV1().trackers().delete(tracker.getName());
			Assert.assertTrue(delete.isSuccess());
		}
	}
	
	@Test(priority = 1)
	public void testGetTraker() {
		Tracker get = osclient.cloudTraceV1().trackers().get(tracker.getName());
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
		Tracker updated = osclient.cloudTraceV1().trackers().update(update);

		// assert
		Assert.assertEquals(updated.getName(), tracker.getName());
		Assert.assertEquals(updated.getBucketName(), tracker.getBucketName());
		Assert.assertEquals(updated.getFilePrefixName(), "SDK-unittest");
		Assert.assertEquals(updated.getStatus(), newStatus);

		// update back to the original one
		TrackerUpdate update2 = TrackerUpdate.builder().trackerName(tracker.getName())
				.bucketName(tracker.getBucketName()).filePrefixName(tracker.getFilePrefixName())
				.status(tracker.getStatus()).build();
		Tracker updated2 = osclient.cloudTraceV1().trackers().update(update2);

		// assert restored tracker
		Assert.assertEquals(updated2.getName(), tracker.getName());
		Assert.assertEquals(updated2.getBucketName(), tracker.getBucketName());
		Assert.assertEquals(updated2.getFilePrefixName(), tracker.getFilePrefixName());
		Assert.assertEquals(updated2.getStatus(), tracker.getStatus());
	}

	@Test(priority = 3)
	public void testDeleteTraker() {
		ActionResponse delete = osclient.cloudTraceV1().trackers().delete(tracker.getName());
		Assert.assertTrue(delete.isSuccess());

		// assert delete success
		List<Tracker> trackers = osclient.cloudTraceV1().trackers().list();
		Assert.assertTrue(trackers.size() == 0);
	}

	@Test(priority = 4)
	public void testCreateTraker() {
		// create it again
		Tracker create = osclient.cloudTraceV1().trackers().create(tracker.getBucketName(), tracker.getFilePrefixName());

		// restore status
		if (!create.getStatus().equals(tracker.getStatus())) {
			TrackerUpdate update = TrackerUpdate.builder().trackerName(tracker.getName())
					.bucketName(tracker.getBucketName()).status(tracker.getStatus()).build();
			Tracker updated = osclient.cloudTraceV1().trackers().update(update);
			Assert.assertEquals(updated.getStatus(), tracker.getStatus());
		}
	}

}
