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
package org.openstack4j.openstack.loadbalance.options;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import com.google.common.collect.Maps;

public class ELBServerListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();

	private ELBServerListOptions() {
	}

	public static ELBServerListOptions create() {
		return new ELBServerListOptions();
	}

	public ELBServerListOptions marker(String marker) {
		return add("marker", marker);
	}

	public ELBServerListOptions limit(int limit) {
		return add("limit", String.valueOf(limit));
	}
	
	public ELBServerListOptions serverAddress(String serverAddress) {
		return add("server_address", serverAddress);
	}
	
	public ELBServerListOptions id(String id) {
		return add("id", id);
	}
	
	public ELBServerListOptions address(String address) {
		return add("address", address);
	}
	
	public ELBServerListOptions status(Status status) {
		checkArgument(status != null, "status is required");
		return add("status", status.name());
	}
	
	public ELBServerListOptions healthStatus(HealthStatus healthStatus) {
		checkArgument(healthStatus !=null, "healthStatus is required");
		return add("health_status", healthStatus);
	}
	
	public ELBServerListOptions serverId(String serverId) {
		return add("server_id", serverId);
	}
	
	private ELBServerListOptions add(String key, Object value) {
		if (value != null)
			this.queryParam.put(key, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return this.queryParam;
	}
	
	public enum Status {
		ACTIVE,
		PENDING,
		ERROR,
		;
	}
	
	public enum HealthStatus {
		NORMAL,
		ABNORMAL,
		UNAVAILABLE,
		;
	}
}
