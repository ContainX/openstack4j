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
package com.huawei.openstack4j.model.identity.v2;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v2.builder.UserBuilder;

/**
 * An Identity User within OpenStack
 * 
 * @author Jeremy Unruh
 */
public interface User extends ModelEntity, Buildable<UserBuilder> {

	/**
	 * @return the user identifier
	 */
	String getId();
	
	/**
	 * @return the username/sign-on name
	 */
	String getUsername();
	
	/**
	 * @return the tenant identifier (default tenant)
	 */
	String getTenantId();
	
	/**
	 * @return the name of the user
	 */
	String getName();
	
	/**
	 * @return the email address of the user
	 */
	String getEmail();
	
	/**
	 * @return true, if the user is enabled (active)
	 */
	Boolean isEnabled();

}
