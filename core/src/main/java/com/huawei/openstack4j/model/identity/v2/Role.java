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
import com.huawei.openstack4j.model.identity.v2.builder.RoleBuilder;

/**
 * A permission scheme a user is assigned when performing specific operations.  A role includes a set of rights and previleges. Any user who is assigned
 * the role inherits these traits.
 * 
 * @author Jeremy Unruh
 */
public interface Role extends ModelEntity, Buildable<RoleBuilder> {

	/**
	 * @return the id of the role
	 */
	String getId();
	
	/**
	 * @return the name of the role
	 */
	String getName();
	
	 /**
   * @return the service id of the role or null, if not present
   */
   String getServiceId();
   
   /**
    * @return the tenant id of the role or null, if not present
    */
   String getTenantId();
	
	/**
	 * @return the description of the role
	 */
	String getDescription();
	
	/**
	 * @return true if the role is enabled
	 */
	boolean isEnabled();
	
}
