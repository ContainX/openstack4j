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
package com.huawei.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * os attach
 * @author Wang Ting/王婷
 *
 */
@JsonRootName("os-attach")
public class AttachAction implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The UUID of the attaching instance.
	 */
	@JsonProperty("instance_uuid")
	private String instanceId;
	
	/**
	 * The attaching mount point.
	 */
	@JsonProperty
	private String mountpoint;

	/**
	 * The name of the attaching host.
	 */
	@JsonProperty("host_name")
	private String hostName ;
	
	/**
	 * <br/>Description:
	 * <p>Author:Wang Ting/王婷</p>
	 * @param instanceId
	 * @param mountpoint
	 * @param hostName
	 * @param serverId
	 */
	
	public AttachAction(String instanceId, String mountpoint, String hostName) {
		super();
		this.instanceId = instanceId;
		this.mountpoint = mountpoint;
		this.hostName = hostName;
	}

	public String getMountpoint() {
		return mountpoint;
	}

	public void setMountpoint(String mountpoint) {
		this.mountpoint = mountpoint;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getInstanceId() {
		return instanceId;
	}
}
