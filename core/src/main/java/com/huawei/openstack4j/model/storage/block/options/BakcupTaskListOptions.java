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
package com.huawei.openstack4j.model.storage.block.options;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask.BackupTaskStatus;

/**
 * filter options for list volume backup policy backup task
 *
 * @author QianBiao.NG
 * @date   2017-06-30 10:30:53
 */
public class BakcupTaskListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private BakcupTaskListOptions() {
	}

	public static BakcupTaskListOptions create() {
		return new BakcupTaskListOptions();
	}

	
	public BakcupTaskListOptions desc() {
		return add("sort_dir", "desc");
	}
	
	public BakcupTaskListOptions asc() {
		return add("sort_dir", "asc");
	}

	public BakcupTaskListOptions id(String taskId) {
		return add("job_id", taskId);
	}

	public BakcupTaskListOptions status(BackupTaskStatus status) {
		return add("status", status);
	}
	
	
	public BakcupTaskListOptions offset(String offset) {
		return add("offset", offset);
	}

	public BakcupTaskListOptions marker(String marker) {
		return add("marker", marker);
	}

	public BakcupTaskListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	private BakcupTaskListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
