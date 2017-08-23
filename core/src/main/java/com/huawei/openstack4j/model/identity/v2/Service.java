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
import com.huawei.openstack4j.model.identity.v2.builder.ServiceBuilder;

/**
 * OpenStack service, such as Compute (Nova), Object Storage (Swift), or Image Service (Glance).
 * A service provides one or more endpoints through which users can access resources and perform 
 *  
 * @author Jeremy Unruh
 */
public interface Service extends ModelEntity, Buildable<ServiceBuilder> {

	/**
	 * @return the id for the service
	 */
	String getId();
	
	/**
	 * The type of service (compute, identity, image, etc)
	 *
	 * @return the type of the service
	 */
	String getType();
	
	/**
	 * @return the name of the service (nova, neutron, glance ...)
	 */
	String getName();
	
	/**
	 * @return the description of the service
	 */
	String getDescription();
	
}
