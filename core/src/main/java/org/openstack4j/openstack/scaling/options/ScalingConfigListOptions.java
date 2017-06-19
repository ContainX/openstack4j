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

import com.google.common.collect.Maps;

public class ScalingConfigListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private ScalingConfigListOptions() {
	}

	public static ScalingConfigListOptions create() {
		return new ScalingConfigListOptions();
	}
	
	public ScalingConfigListOptions configName(String configName) {
		return add("scaling_configuration_name", configName);
	}
	
	public ScalingConfigListOptions imageId(String imageId) {
		return add("image_id", imageId);
	}
	
	public ScalingConfigListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}
	
	public ScalingConfigListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	private ScalingConfigListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
