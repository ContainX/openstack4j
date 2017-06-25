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

import java.util.Map;

import com.google.common.collect.Maps;

public class ELBLoadBalancerListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();
	
	private ELBLoadBalancerListOptions() { }
	
	public static ELBLoadBalancerListOptions create() {
		return new ELBLoadBalancerListOptions();
	}
	
	public ELBLoadBalancerListOptions vipAddress(String vipAddress) {
		return add("vip_address", vipAddress);
	}
	
	public ELBLoadBalancerListOptions id(String id) {
		return add("id", id);
	}
	
	public ELBLoadBalancerListOptions status(String status) {
		return add("status", status);
	}
	
	public ELBLoadBalancerListOptions bandwidth(Integer bandwidth) {
		return add("bandwidth", bandwidth);
	}
	
	public ELBLoadBalancerListOptions vpcId(String vpcId) {
		return add("vpc_id", vpcId);
	}
	
	public ELBLoadBalancerListOptions adminStateUp(Integer adminStateUp) {
		return add("admin_state_up", adminStateUp);
	}
	
	public ELBLoadBalancerListOptions vipSubnetId(String vipSubnetId) {
		return add("vip_subnet_id", vipSubnetId);
	}
	
	public ELBLoadBalancerListOptions type(String type) {
		return add("type", type);
	}

	public ELBLoadBalancerListOptions name(String name) {
		return add("name", name);
	}
	
	public ELBLoadBalancerListOptions description(String description) {
		return add("description", description);
	}
	
	public ELBLoadBalancerListOptions securityGroupId(String securityGroupId) {
		return add("securityGroupId", securityGroupId);
	}
	
	private ELBLoadBalancerListOptions add(String key, Object value) {
		if(value != null)	
			this.queryParam.put(key, value);
		return this;
	}
	
	public Map<String, Object> getOptions() {
		return this.queryParam; 
	}
}
