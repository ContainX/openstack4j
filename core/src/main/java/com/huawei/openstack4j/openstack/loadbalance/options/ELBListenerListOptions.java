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
package com.huawei.openstack4j.openstack.loadbalance.options;

import java.util.Map;

import com.google.common.collect.Maps;

public class ELBListenerListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();
	
	private ELBListenerListOptions() { }
	
	public static ELBListenerListOptions create() {
		return new ELBListenerListOptions();
	}
	
	public ELBListenerListOptions loadBalancerId(String loadBalancerId) {
		return add("loadbalancer_id", loadBalancerId);
	}
	
	public ELBListenerListOptions backendPort(Integer backendPort) {
		return add("backend_port", backendPort);
	}
	
	public ELBListenerListOptions id(String id) {
		return add("id", id);
	}
	
	public ELBListenerListOptions backendProtocol(String backendProtocol) {
		return add("backend_protocol",  backendProtocol);
	}
	
	public ELBListenerListOptions stickySessionType(String stickySessionType) {
		return add("sticky_session_type", stickySessionType);
	}
	
	public ELBListenerListOptions description(String description) {
		return add("description", description);
	}
	
	public ELBListenerListOptions status(String status) {
		return add("status", status);
	}
	
	public ELBListenerListOptions protocol(String protocol) {
		return add("protocol", protocol);
	}
	
	public ELBListenerListOptions lbAlgorithm(String lbAlgorithm) {
		return add("lbAlgorithm", lbAlgorithm);
	}
	
	public ELBListenerListOptions cookieTimeout(Integer timeout) {
		return add("cookie_timeout", timeout);
	}
	
	public ELBListenerListOptions healthCheckId(String healthCheckId) {
		return add("healthcheck_id", healthCheckId);
	}
	
	public ELBListenerListOptions port(Integer port) {
		return add("port", port);
	}
	
	public ELBListenerListOptions name(String name) {
		return add("name", name);
	}
	
	public ELBListenerListOptions certificateId(String certificateId) {
		return add("certificate_id", certificateId);
	}
	
	public ELBListenerListOptions tcpTimeout(Integer timeout) {
		return add("tcp_timeout", timeout);
	}
	
	public ELBListenerListOptions udpTimeout(Integer timeout) {
		return add("udp_timeout", timeout);
	}
	
	public ELBListenerListOptions sslProtocols(String sslProtocols) {
		return add("ssl_protocols", sslProtocols);
	}
	
	public ELBListenerListOptions sslCiphers(String sslCiphers) {
		return add("ssl_ciphers", sslCiphers);
	}
	
	private ELBListenerListOptions add(String key, Object value) {
		if(value != null)	
			this.queryParam.put(key, value);
		return this;
	}
	
	public Map<String, Object> getOptions() {
		return this.queryParam; 
	}
}
