/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package com.huawei.openstack4j.openstack.cloud.trace.v1.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.Tracker;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.Tracker.Trackers;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.TrackerUpdate;

/**
 * 
 *
 * @author QianBiao.NG
 * @date   2017-07-13 09:31:18
 */
public class TrackerService extends BaseCloudTraceServices implements RestService {

	/**
	 * List trackers
	 * 
	 * @return
	 */
	public List<Tracker> list() {
		return get(Trackers.class, "/tracker").execute().getList();
	}

	/**
	 * 
	 * get tracer details
	 * 
	 * @param trackerName	the name of the tracer to be fetched
	 * @return
	 */
	public Tracker get(String trackerName) {
		checkNotNull(trackerName, "parameter `trackerName` should not be empty");
		return get(Tracker.class, "/tracker").param("tracker_name", trackerName).execute();
	}

	/**
	 * Create a new Tracer which trace all operations
	 * 
	 * @see TrackerService#create(String, String)
	 * @param bucketName bucket(name) used to persist the trace log files
	 * @return
	 */
	public Tracker create(String bucketName) {
		return create(bucketName, null);
	}

	/**
	 * 
	 * Create a new Tracer
	 * 
	 * @param bucketName		bucket(name) used to persist the trace log files
	 * @param filePrefixName	(optional) if set, only log files whose name start with `file_prefix_name` will be persisted
	 * @return
	 */
	public Tracker create(String bucketName, String filePrefixName) {
		checkNotNull(bucketName, "parameter `bucketName` should not be empty");

		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("bucket_name", bucketName);
		if (!Strings.isNullOrEmpty(filePrefixName)) {
			entity.put("file_prefix_name", filePrefixName);
		}
		return post(Tracker.class, "/tracker").entity(entity)
				.execute(ExecutionOptions.<Tracker> create(PropagateOnStatus.on(404)));
	}

	/**
	 * update a tracker
	 * 
	 * @param update a model represent to be updated attributes of the tracker
	 * @return
	 */
	public Tracker update(TrackerUpdate update) {
		checkNotNull(update, "parameter `update` should not be empty");
		checkNotNull(update.getTrackerName(), "parameter `update.tracerName` should not be empty");
		checkNotNull(update.getBucketName(), "parameter `update.bucketName` should not be empty");
		String trackerName = update.getTrackerName();
		update = update.toBuilder().trackerName(null).build();
		return put(Tracker.class, "/tracker/", trackerName).entity(update)
				.execute(ExecutionOptions.<Tracker> create(PropagateOnStatus.on(404)));
	}

	/**
	 * delete a tracker
	 * 
	 * @param trackerName
	 * @return
	 */
	public ActionResponse delete(String trackerName) {
		checkNotNull(trackerName, "parameter `trackerName` should not be empty");
		return deleteWithResponse("/tracker").param("tracker_name", trackerName).execute();
	}

	/**
	 * 
	 * delete all tracker
	 * 
	 * @param trackerName
	 * @return
	 */
	public ActionResponse deleteAll() {
		return deleteWithResponse("/tracker").execute();
	}

}
