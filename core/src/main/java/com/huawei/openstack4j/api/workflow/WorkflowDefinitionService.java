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
import com.huawei.openstack4j.model.workflow.Scope;
import com.huawei.openstack4j.model.workflow.WorkflowDefinition;

/**
 * Service that provides CRUD operations for workflow definitions.
 * 
 * @author Renat Akhmerov
 */
public interface WorkflowDefinitionService extends RestService {

    /**
     * List all workflow definitions with details.
     *
     * @return List of workflow definitions.
     */
    List<? extends WorkflowDefinition> list();

    /**
     * Create one or more workflow definitions.
     *
     * @param wfText Text in YAML format (Mistral language) with one or more workflow definitions.
     * @param scope Scope of newly created workflows.
     * @return Created workflow definitions.
     */
    List<? extends WorkflowDefinition> create(InputStream wfText, Scope scope);

    /**
     * Get workflow definition by its identifier.
     *
     * @param identifier Workflow definition identifier (either ID or name).
     * @return Workflow definition.
     */
    WorkflowDefinition get(String identifier);

    /**
     * Delete workflow definition by its identifier.
     *
     * @param identifier Workflow definition identifier (either ID or name).
     * @return Action response from the server.
     */
    ActionResponse delete(String identifier);
}
