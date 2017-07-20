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
package org.openstack4j.openstack.antiddos.options;

import java.util.Map;

import org.openstack4j.openstack.antiddos.constants.Sort;

import com.google.common.collect.Maps;

public class AntiDDoSLogListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private AntiDDoSLogListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static AntiDDoSLogListOptions create() {
		return new AntiDDoSLogListOptions();
	}
	
	public AntiDDoSLogListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	public AntiDDoSLogListOptions offset(Integer offset) {
		return add("offset", offset);
	}
	
	public AntiDDoSLogListOptions sort(Sort sort) {
		return add("sort_dir", sort.name().toLowerCase());
	}
}
