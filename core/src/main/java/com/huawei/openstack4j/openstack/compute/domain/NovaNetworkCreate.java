/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.compute.NetworkCreate;

public class NovaNetworkCreate implements NetworkCreate {

	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	private String id;
	@JsonProperty("fixed_ip")
	private String fixedIp;
	
	private String port;
	
	public NovaNetworkCreate() { }
	
	public void setId(String id) {
		this.id = id;
	}

	public void setFixedIp(String fixedIp) {
		this.fixedIp = fixedIp;
	}

	public void setPort(String port) {
		this.port = port;
	}		

	public NovaNetworkCreate(String id, String fixedIp) {
		this.id = id;
		this.fixedIp = fixedIp;
	}

	public NovaNetworkCreate(String id, String fixedIp, String port) {
		this.id = id;
		this.fixedIp = fixedIp;
		this.port = port;
	}		
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getFixedIp() {
		return fixedIp;
	}

	public String getPort() {
		return port;
	}		

}
