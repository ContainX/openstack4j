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
package com.huawei.openstack4j.model.image;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Represents a system tenant who has access to another tenants Image
 * 
 * @author Jeremy Unruh
 */
public interface ImageMember extends ModelEntity {

	/**
	 * The Member/Tenant
	 *
	 * @return the member identifier
	 */
	String getMemberId();
	
	/**
	 * If true the current member can share the image with another tenant
	 *
	 * @return true, if the current member/tenant can share the image
	 */
	boolean isCanShare();
	
}

