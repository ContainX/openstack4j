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
package com.huawei.openstack4j.openstack.heat.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.heat.EventsService;
import com.huawei.openstack4j.model.heat.Event;
import com.huawei.openstack4j.openstack.heat.domain.HeatEvent;
import com.huawei.openstack4j.openstack.heat.domain.HeatEvent.Events;

/**
 * This class implements some methods for manipulation of {@link HeatEvent} objects. The
 * non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html#stacks
 * 
 * @author Octopus Zhang
 * 
 */
public class EventsServiceImpl extends BaseHeatServices implements EventsService {

	@Override
	public List<? extends Event> list(String stackName, String stackId) {
	    checkNotNull(stackId);
	    checkNotNull(stackName);
		return get(Events.class, uri("/stacks/%s/%s/events", stackName, stackId)).execute().getList();
	}

	@Override
	public List<? extends Event> list(String stackName, String stackId, String resourceName) {
	    checkNotNull(stackId);
        checkNotNull(stackName);
        checkNotNull(resourceName);
		return get(Events.class, uri("/stacks/%s/%s/resources/%s/events", stackName, stackId ,resourceName)).execute().getList();
	}

	@Override
	public Event show(String stackName, String stackId, String resourceName, String eventId) {
	    checkNotNull(stackId);
        checkNotNull(stackName);
        checkNotNull(resourceName);
        checkNotNull(eventId);
		return get(HeatEvent.class, uri("/stacks/%s/%s/resources/%s/events/%s", stackName, stackId, resourceName, eventId)).execute();
	}

}
