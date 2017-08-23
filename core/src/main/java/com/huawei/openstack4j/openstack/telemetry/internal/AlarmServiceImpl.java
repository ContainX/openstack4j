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
package com.huawei.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.telemetry.AlarmService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.telemetry.Alarm;
import com.huawei.openstack4j.openstack.telemetry.domain.CeilometerAlarm;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 * 
 * @author Massimiliano Romano
 */
public class AlarmServiceImpl extends BaseTelemetryServices implements AlarmService {
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Alarm> list() {
		CeilometerAlarm[] alarms = get(CeilometerAlarm[].class, uri("/alarms")).execute();
		return wrapList(alarms);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alarm getById(String alarmId){
		checkNotNull(alarmId);
        return get(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).execute();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(String alarmId, Alarm alarm){
		checkNotNull(alarmId);
		checkNotNull(alarm);
		
		put(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).entity(alarm).execute();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alarm create(Alarm alarm) {
		checkNotNull(alarm);
		return post(CeilometerAlarm.class, uri("/alarms")).entity((alarm)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String alarmId) {
		checkNotNull(alarmId);
		return deleteWithResponse(uri("/alarms/%s", alarmId)).execute();
	}
}
