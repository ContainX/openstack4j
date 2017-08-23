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
package com.huawei.openstack4j.model.tacker;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.tacker.builder.VimBuilder;
import com.huawei.openstack4j.openstack.tacker.domain.AuthCredentials;
import com.huawei.openstack4j.openstack.tacker.domain.TackerVimStatus;
import com.huawei.openstack4j.openstack.tacker.domain.VimPlacementAttribute;
import com.huawei.openstack4j.openstack.tacker.domain.VimProject;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 18, 2016
 */
public interface Vim extends ModelEntity, Buildable<VimBuilder> {
	
	/**
	 * 
	 * @return id : Unique identifier for the Vim.
	 */
	String getId();
	
	/**
	 * 
	 * @return name : Human readable name for the Vim (255 characters limit). Does not have to be unique.
	 */
	String getName();

	/**
	 * 
	 * @return tenantId : Owner of the Vim. Only an administrative user can specify a tenant ID other than its own.
	 */
	String getTenantId();

	/**
	 * 
	 * @return description : Human readable description for the Vim (1024 characters limit).
	 */
	String getDescription();
	
	/**
	 * 
	 * @return type
	 */
	String getType();
	
	/**
	 * 
	 * @return isDefault
	 */
	Boolean isDefault();
	
	/**
	 * 
	 * @return authUrl
	 */
	String getAuthUrl();
	
	/**
	 * 
	 * @return authCredentials
	 */
	AuthCredentials getAuthCredentials();
	
	/**
	 * 
	 * @return vimProject
	 */
	VimProject getVimProject();
	
	/**
	 * 
	 * @return the status
	 */
	TackerVimStatus getStatus();

	/**
	 * 
	 * @return the placementAttribute
	 */
	VimPlacementAttribute getPlacementAttribute();

}
