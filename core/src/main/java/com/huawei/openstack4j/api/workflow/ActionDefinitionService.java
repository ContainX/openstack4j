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

import java.io.InputStream;
import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.workflow.ActionDefinition;
import com.huawei.openstack4j.model.workflow.Scope;

/**
 * Service that provides CRUD operations for action definitions.
 * 
 * @author Renat Akhmerov
 */
public interface ActionDefinitionService extends RestService {

    /**
     * List all action definitions with details.
     *
     * @return List of action definitions.
     */
    List<? extends ActionDefinition> list();

    /**
     * Create a new action definition.
     *
     * @param actionText Text in YAML format (Mistral language) with one or more action definitions.
     * @param scope Scope of newly created workflows.
     * @return Created action definition.
     */
    List<? extends ActionDefinition> create(InputStream actionText, Scope scope);

    /**
     * Get action definition by its identifier.
     *
     * @param identifier Action definition identifier (either ID or name).
     * @return Action definition.
     */
    ActionDefinition get(String identifier);

    /**
     * Delete action definition by its identifier.
     *
     * @param identifier Action definition identifier (either ID or name).
     * @return Action response from the server.
     */
    ActionResponse delete(String identifier);
}


