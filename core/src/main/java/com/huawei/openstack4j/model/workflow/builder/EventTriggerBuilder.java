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
package com.huawei.openstack4j.model.workflow.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.workflow.EventTrigger;

/**
 * Builder for a {@link EventTrigger} model class
 * 
 * @author Renat Akhmerov
 */
public interface EventTriggerBuilder extends Builder<EventTriggerBuilder, EventTrigger> {

	/**
	 * @see EventTrigger#getId()
	 */
	EventTriggerBuilder id(String id);

	/**
	 * @see EventTrigger#getName()
	 */
	EventTriggerBuilder name(String name);

	// TODO(rakhmerov): add all methods
}
