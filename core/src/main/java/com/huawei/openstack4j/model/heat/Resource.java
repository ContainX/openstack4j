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
 * This interface describes the getter-methods (and thus components) of a heat source.
 * All getters map to the possible return values of
 * <code> Get /v1/{tenant_id}/stacks/{stack_name}/{stack_id}/resources/{resource_name}</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author Octopus Zhang
 * 
 */
public interface Resource extends ModelEntity {
	
	/**
	 * Returns the link of the resource
	 * 
	 * @return the link of the resource
	 */
	List<? extends Link> getLinks();
	
	/**
	 * Returns the update time of the resource
	 * 
	 * @return the update time of the resource
	 */
	Date getTime();
	
	/**
	 * Returns the type of the resource
	 * 
	 * @return the type of the resource
	 */
	String getType();
	
	/**
	 * Returns the type of the resource
	 * 
	 * @return the type of the resource
	 */
	String getReason();
	
	/**
	 * Returns the name of the resource
	 * 
	 * @return the name of the resource
	 */
	String getResourceName();
	
	/**
	 * Returns the local resource id of the resource
	 * 
	 * @return the local resource id of the resource
	 */
	String getLocalReourceId();
	
	/**
	 * Returns the local resource id of the resource
	 * 
	 * @return the local resource id of the resource
	 */
	String getResourceStatus();
	
	/**
	 * Returns the local physical resource id of the resource
	 * 
	 * @return the local physical resource id of the resource
	 */
	String getPhysicalResourceId();

	/**
	 * Returns the resource name which need this resource
	 * 
	 * @return the resource name which need this resource
	 */
	List<String> getRequiredBy();

}
