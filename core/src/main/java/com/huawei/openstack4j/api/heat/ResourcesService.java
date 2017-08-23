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
package com.huawei.openstack4j.api.heat;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.common.*;
import com.huawei.openstack4j.model.heat.Resource;
import com.huawei.openstack4j.model.heat.ResourceHealth;


/**
 * This interface defines all methods for the manipulation of resources
 * 
 * @author Octopus Zhang
 * 
 */
public interface ResourcesService {
	
	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack.
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackName, String stackId);
	
	
	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack.
	 * 
	 * @param stackNameOrId    Stack name or stack id
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackNameOrId);

	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack with filtern parameters.
	 *
	 * @param stackNameOrId  	Stack name or stack id
	 * @param depth   			The recursion level for which resources will be listed.
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackNameOrId, int depth);
	
	/**
	 * Gets the detail of the specified resource
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @param resourceName The name of a resource
	 * @return the detail of the specified resource
	 */
	Resource show(String stackName, String stackId, String resourceName);

	/**
	 * Gets the stack resource metadata
	 *
	 * @param stackName     The name of a stack
	 * @param stackId       The unique identifier for a stack
	 * @param resourceName  The name of a resource
	 * @return the metadata of the specified resource
	 */
	Map<String, Object> getMetadata(String stackName, String stackId, String resourceName);

	/**
	 * Signals a resource
	 * @param stackName
	 * @param stackId
	 * @param resourceName
	 */
	ActionResponse signal(String stackName, String stackId, String resourceName);

	/**
	 * Marks a resource unhealthy
	 * @param stackName
	 * @param stackId
	 * @param resourceName
	 * @param resourceHealth
	 */
	ActionResponse markUnhealthy(String stackName, String stackId, String resourceName, ResourceHealth resourceHealth);

}
