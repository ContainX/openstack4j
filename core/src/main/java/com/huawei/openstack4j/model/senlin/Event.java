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
package com.huawei.openstack4j.model.senlin;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Event.
 * All getters map to the possible return values of
 * <code> GET /v1/events/​{event_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Event extends ModelEntity {

	/**
	 * Returns the id of the Event
	 * 
	 * @return the id of the Event
	 */
	String getId();

	/**
	 * Returns the action of the Event
	 *
	 * @return the action of the Event
	 */
	String getAction();

	/**
	 * Returns the cluster id of the Event
	 *
	 * @return the cluster id of the Event
	 */
	String getClusterID();

	/**
	 * Returns the level of the Event
	 *
	 * @return the level of the Event
	 */
	Integer getLevel();

	/**
	 * Returns the obj id of the Event
	 *
	 * @return the obj id of the Event
	 */
	String getObjID();

	/**
	 * Returns the obj name of the Event
	 *
	 * @return the obj name of the Event
	 */
	String getObjName();

	/**
	 * Returns the obj type of the Event
	 *
	 * @return the obj type of the Event
	 */
	String getObjType();

	/**
	 * Returns the project of the Event
	 *
	 * @return the project of the Event
	 */
	String getProject();

	/**
	 * Returns the status of the Event
	 *
	 * @return the status of the Event
	 */
	String getStatus();

	/**
	 * Returns the status reason of the Event
	 *
	 * @return the status reason of the Event
	 */
	String getStatusReason();

	/**
	 * Returns the timestamp of the Event
	 *
	 * @return the timestamp of the Event
	 */
	Date getTimestamp();

	/**
	 * Returns the user of the Event
	 *
	 * @return the user of the Event
	 */
	String getUser();
}
