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
import com.huawei.openstack4j.model.telemetry.Event;
import com.huawei.openstack4j.model.telemetry.EventCriteria;
import com.huawei.openstack4j.model.telemetry.Trait;
import com.huawei.openstack4j.model.telemetry.TraitDescription;

/**
 * OpenStack (Ceilometer) Event based Operations
 * 
 * @author Miroslav Lacina
 */
public interface EventService extends RestService {

    /**
     * Return all Events matching the query filters
     *
     * @param eventCriteria the event query criteria for filtering results
     * @return list of Events
     */
    List<? extends Event> list(EventCriteria eventCriteria);

    /**
     * Return all Events matching the query filters
     *
     * @param eventCriteria the event query criteria for filtering results
     * @param limit maximum number of samples to be returned
     * @return list of Events
     */
    List<? extends Event> list(EventCriteria eventCriteria, int limit);

    /**
     * Return a single Event with the given message id
     *
     * @param messageId message ID of the Event to be returned
     * @return single Event
     */
    Event get(String messageId);

    /**
     * Get all event types
     *
     * @return list of event types
     */
    List<String> listEventTypes();

    /**
     * Return all trait names for an event type
     *
     * @param eventType event type to filter traits by
     * @return list of TraitDescriptions
     */
    List<? extends TraitDescription> listTraitDescriptions(String eventType);

    /**
     * Return all instances of a trait for an event type
     *
     * @param eventType event type to filter traits by
     * @param traitName trait to return values for
     * @return list of Traits
     */
    List<? extends Trait> listTraits(String eventType, String traitName);

}
