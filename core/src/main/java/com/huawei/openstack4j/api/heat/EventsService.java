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

import com.huawei.openstack4j.model.heat.Event;

/**
 * This interface defines all methods for the manipulation of events
 * 
 * @author Octopus Zhang
 * 
 */
public interface EventsService {
	
	/**
	 * Gets a list of currently existing {@link Event}s  for a specified stack.
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @return the list of {@link Event}s
	 */
	List<? extends Event> list(String stackName, String stackId);
	
	/**
	 * Gets a list of currently existing {@link Event}s  for a specified stack resource.
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @param resourceName The name of a resource in the stack
	 * @return the list of {@link Event}s
	 */
	List<? extends Event> list(String stackName, String stackId, String resourceName);
	
	
	/**
	 * Shows details for a specified event. 
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @param resourceName The name of a resource in the stack
	 * @param eventId      The unique identifier of an event related to the resource in the stack 
	 * @return event details
	 */
	Event show(String stackName, String stackId, String resourceName, String eventId);
}
