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
package org.openstack4j.openstack.scaling.options;

import java.util.Map;

import org.openstack4j.model.scaling.ScalingGroup.ScalingGroupStatus;

import com.google.common.collect.Maps;

public class ScalingGroupListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private ScalingGroupListOptions() {
	}

	public static ScalingGroupListOptions create() {
		return new ScalingGroupListOptions();
	}

	public ScalingGroupListOptions groupName(String groupName) {
		return add("scaling_group_name", groupName);
	}

	public ScalingGroupListOptions configId(String configId) {
		return add("scaling_configuration_id", configId);
	}

	public ScalingGroupListOptions groupStatus(ScalingGroupStatus groupStatus) {
		return add("scaling_group_status", groupStatus.name());
	}

	public ScalingGroupListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}

	public ScalingGroupListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	private ScalingGroupListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
