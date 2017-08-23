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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.VolumeAttachment;

import com.google.common.base.MoreObjects;

/**
 * return a description for this volume attachment job
 *
 * @author Octopus Zhang
 */
@JsonRootName("volumeAttachment")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NovaVolumeAttachment implements VolumeAttachment {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String device;

	@JsonProperty
	private String id;

	@JsonProperty
	private String serverId;

	@JsonProperty
	private String volumeId;

	public NovaVolumeAttachment() {
	}

    private NovaVolumeAttachment(String volumeId, String device) {
        this.volumeId = volumeId;
        this.device = device;
    }

	public static NovaVolumeAttachment create(String volumeId, String device) {
	    return new NovaVolumeAttachment(volumeId, device);
	}

	@Override
	public String getDevice() {
		return device;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getServerId() {
		return serverId;
	}

	@Override
	public String getVolumeId() {
		return volumeId;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("device", device).add("id", id).add("serverId", serverId)
				.add("volumeId", volumeId).toString();

	}

}
