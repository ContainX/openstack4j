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
 * os force detach
 * @author Wang Ting/王婷
 *
 */
@JsonRootName("os-force_detach")
public class ForceDetachAction implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The interface ID
	 */
	@JsonProperty("attachment_id ")
	private String attachmentId ;
	
	/**
	 * The connector object.
	 */
	@JsonProperty
	private ForceDetachConnector connector;
	
	public ForceDetachAction(String attachmentId, ForceDetachConnector connector) {
		super();
		this.attachmentId = attachmentId;
		this.connector = connector;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public ForceDetachConnector getConnector() {
		return connector;
	}

	public void setConnector(ForceDetachConnector connector) {
		this.connector = connector;
	}

	
}
