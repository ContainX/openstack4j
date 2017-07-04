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

import com.google.common.collect.Maps;

import java.util.Map;

import org.openstack4j.model.scaling.ScalingPolicyCreateUpdate.ScalingPolicyType;

public class ScalingPolicyListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();
	
	private ScalingPolicyListOptions() { }
	
	public static ScalingPolicyListOptions create() {
		return new ScalingPolicyListOptions();
	}
	
	public ScalingPolicyListOptions policyName(String policyName) {
		return add("scaling_policy_name", policyName);
	}
	
	public ScalingPolicyListOptions policyType(ScalingPolicyType policyType) {
		return add("scaling_policy_type", policyType.name());
	}
	
	public ScalingPolicyListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}
	
	public ScalingPolicyListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	private ScalingPolicyListOptions add(String key, Object value) {
		if(value != null)
			this.queryParam.put(key, value);
		return this;
	}
	
	public Map<String, Object> getOptions() {
		return this.queryParam;
	}
}
