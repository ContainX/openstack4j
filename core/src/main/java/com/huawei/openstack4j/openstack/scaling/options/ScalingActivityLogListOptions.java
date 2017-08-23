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
package com.huawei.openstack4j.openstack.scaling.options;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.common.DateTimeUtils;

public class ScalingActivityLogListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();

	private ScalingActivityLogListOptions() {
	}

	public static ScalingActivityLogListOptions create() {
		return new ScalingActivityLogListOptions();
	}

	public ScalingActivityLogListOptions startTime(Date startTime) {
		return add("start_time", DateTimeUtils.format(startTime, DateTimeUtils.FORMAT_YMDTHMSZ));
	}

	public ScalingActivityLogListOptions endTime(Date endTime) {
		return add("end_time", DateTimeUtils.format(endTime, DateTimeUtils.FORMAT_YMDTHMSZ));
	}

	public ScalingActivityLogListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}

	public ScalingActivityLogListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	private ScalingActivityLogListOptions add(String key, Object value) {
		if (value != null)
			this.queryParam.put(key, value);

		return this;
	}

	public Map<String, Object> getOptions() {
		return this.queryParam;
	}
}
