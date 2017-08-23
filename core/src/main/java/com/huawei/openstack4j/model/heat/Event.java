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
package com.huawei.openstack4j.model.heat;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Link;

/**
 * This interface describes the getter-methods (and thus components) of a event.
 * All getters map to the possible return values of
 * <code> Get /v1/{tenant_id}/stacks/{stack_name}/{stack_id}/resources/{resource_name}/events/{event_id}</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author Octopus Zhang
 * 
 */
public interface Event extends ModelEntity {

	/**
	 * Returns the id of the event
	 * 
	 * @return the id of the event
	 */
	String getId();
	
	/**
	 * Returns the resource_name of the event
	 * 
	 * @return the resource_name of the event
	 */
	String getResourceName();
	
	/**
	 * Returns the event_time of the event
	 * 
	 * @return the event_time of the event
	 */
	Date getTime();
	
	/**
	 * Returns the logical_resource_id of the event
	 * 
	 * @return the logical_resource_id of the event
	 */
	String getLocalReourceId();
	
	/**
	 * Returns the resource_status_reason of the event
	 * 
	 * @return the resource_status_reason of the event
	 */
	String getReason();
	
	/**
	 * Returns the resource_status of the event
	 * 
	 * @return the resource_status of the event
	 */
	String getResourceStatus();
	
	/**
	 * Returns the physical_resource_id of the event
	 * 
	 * @return the physical_resource_id of the event
	 */
	String getPhysicalResourceId();
	
	/**
	 * Returns the links of the event
	 * 
	 * @return the links of the event
	 */
	List<? extends Link> getLinks();
	
	/**
	 * Returns the resource type of the event
	 * 
	 * @return the resource type of the event
	 */
	String getResourceType();

	/**
	 * Returns the resource properties of the event
	 * 
	 * @return the resource properties of the event
	 */
	Object getResourceProperties();

}
