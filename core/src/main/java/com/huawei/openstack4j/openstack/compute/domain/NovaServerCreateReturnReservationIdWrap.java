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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.NetworkCreate;
import com.huawei.openstack4j.model.compute.Personality;
import com.huawei.openstack4j.model.compute.SecurityGroup;
import com.huawei.openstack4j.model.compute.Server.DiskConfig;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.builder.ServerCreateBuilder;

/**
 * :) 
 *
 * @author QianBiao.NG
 * @date   2018-02-06 17:59:57
 */
@JsonRootName("server")
public class NovaServerCreateReturnReservationIdWrap extends NovaServerCreate {
	private static final long serialVersionUID = -6031802352008446788L;
	
	private ServerCreate create;
	
	/**
	 * Qianbiao.NG:: add Create Multiple Servers support.
	 * <br/>
	 * Document:: https://developer.openstack.org/api-ref/compute/#create-multiple-servers
	 * 
	 * @return
	 */
	@JsonProperty("return_reservation_id")
	private boolean returnReservationId;
	
	public NovaServerCreateReturnReservationIdWrap() {
	}
	
	public NovaServerCreateReturnReservationIdWrap(ServerCreate create) {
		this.create = create;
	}
	
	public NovaServerCreateReturnReservationIdWrap(ServerCreate create, boolean returnReservationId) {
		this.create = create;
		this.returnReservationId = returnReservationId;
	}
	

	public ServerCreateBuilder toBuilder() {
		return create.toBuilder();
	}

	public String getName() {
		return create.getName();
	}

	public String getAdminPass() {
		return create.getAdminPass();
	}

	public String getImageRef() {
		return create.getImageRef();
	}

	public String getFlavorRef() {
		return create.getFlavorRef();
	}

	public String getAccessIPv4() {
		return create.getAccessIPv4();
	}

	public String getAccessIPv6() {
		return create.getAccessIPv6();
	}

	public Integer getMin() {
		return create.getMin();
	}

	public Integer getMax() {
		return create.getMax();
	}

	public DiskConfig getDiskConfig() {
		return create.getDiskConfig();
	}

	public String getKeyName() {
		return create.getKeyName();
	}

	public String getUserData() {
		return create.getUserData();
	}

	public boolean isConfigDrive() {
		return create.isConfigDrive();
	}

	public Map<String, String> getMetaData() {
		return create.getMetaData();
	}

	public List<Personality> getPersonality() {
		return create.getPersonality();
	}

	public List<? extends SecurityGroup> getSecurityGroups() {
		return create.getSecurityGroups();
	}

	public String getAvailabilityZone() {
		return create.getAvailabilityZone();
	}

	public List<? extends NetworkCreate> getNetworks() {
		return create.getNetworks();
	}

	public Map<String, Object> getSchedulerHints() {
		return create.getSchedulerHints();
	}

	public void addPersonality(String path, String contents) {
		create.addPersonality(path, contents);
	}

	public void addSecurityGroup(String name) {
		create.addSecurityGroup(name);
	}

	public void addNetwork(String id, String fixedIP) {
		create.addNetwork(id, fixedIP);
	}

	public void addNetworkPort(String id) {
		create.addNetworkPort(id);
	}

	public boolean getReturnReservationId() {
		return this.returnReservationId;
	};
	

}
