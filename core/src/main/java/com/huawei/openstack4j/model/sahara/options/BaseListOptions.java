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
package com.huawei.openstack4j.model.sahara.options;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * filter options for list sahara resources
 *
 * @author QianBiao.NG
 * @date   2017-06-30 10:30:53
 */
public abstract class BaseListOptions<T> {
	
	private Map<String, Object> queryParams = Maps.newHashMap();
	
	

	protected BaseListOptions() {
		
	}
	

	/**
	 * add sort by attribute DESC filter condition
	 * 
	 * @param attribute
	 * @return
	 */
	public T desc(String attribute) {
		return add("sort_by", Strings.isNullOrEmpty(attribute) ? null : "-" + attribute);
	}

	/**
	 * add sort by attribute ASC filter condition
	 * @param attribute
	 * @return
	 */
	public T asc(String attribute) {
		return add("sort_by", attribute);
	}

	public T marker(String marker) {
		return add("marker", marker);
	}

	public T limit(Integer limit) {
		return add("limit", limit);
	}

	@SuppressWarnings("unchecked")
	private T add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return (T) this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
