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
package com.huawei.openstack4j.api.telemetry;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.telemetry.Alarm;

/**
 * Provides alarms within an OpenStack deployment
 * 
 * @author Massimiliano Romano
 */
public interface AlarmService extends RestService {

	/**
	 * Return all alarms.
	 * 
	 * @return list of all alarms
	 */
	List<? extends Alarm> list();

	/**
	 * Return a specified alarm
	 * 
	 * @return the alarm
	 */
	Alarm getById(String id);
	
	/**
	 * Update a specified alarm
	 * 
	 */
	void update(String id, Alarm a);
	
	/**
	 * Create an alarm
	 * 
	 */
	Alarm create(Alarm alarm);

	/**
	 * Delete a specified alarm
	 * 
	 */
	ActionResponse delete(String id);
}
