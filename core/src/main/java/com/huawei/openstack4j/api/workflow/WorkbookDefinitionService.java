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
import com.huawei.openstack4j.model.workflow.WorkbookDefinition;

/**
 * Service that provides CRUD operations for workbook definitions.
 * 
 * @author Renat Akhmerov
 */
public interface WorkbookDefinitionService extends RestService {
    /**
     * List all workbook definitions with details.
     *
     * @return List of workbook definitions.
     */
    List<? extends WorkbookDefinition> list();

    /**
     * Create a new workbook definition.
     *
     * @param wbText Text in YAML format (Mistral language) with a workbook definition.
     * @param scope Scope of newly created workbook.
     * @return Created workbook definition.
     */
    WorkbookDefinition create(InputStream wbText, Scope scope);

    /**
     * Get workbook definition by its identifier.
     *
     * @param identifier Workbook definition identifier (either ID or name).
     * @return Workbook definition.
     */
    WorkbookDefinition get(String identifier);

    /**
     * Delete workbook definition by its identifier.
     *
     * @param identifier Workbook definition identifier (either ID or name).
     * @return Action response from the server.
     */
    ActionResponse delete(String identifier);
}
