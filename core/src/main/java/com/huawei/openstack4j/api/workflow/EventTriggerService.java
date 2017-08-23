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
package com.huawei.openstack4j.api.workflow;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.workflow.EventTrigger;

/**
 * Service that provides CRUD operations for event triggers.
 *
 * @author Renat Akhmerov
 */
public interface EventTriggerService extends RestService {
    /**
     * List all event triggers with details.
     *
     * @return List of event triggers.
     */
    List<? extends EventTrigger> list();

    /**
     * Create a new event trigger.
     *
     * @param eventTrigger Event trigger to create.
     * @return Created event trigger.
     */
    EventTrigger create(EventTrigger eventTrigger);

    /**
     * Get event trigger by its ID.
     *
     * @param id Event trigger ID.
     * @return Event trigger.
     */
    EventTrigger get(String id);

    /**
     * Delete event trigger by its ID.
     *
     * @param id Event trigger ID.
     * @return HTTP response from the server.
     */
    HttpResponse delete(String id);

}
