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
package org.openstack4j.openstack.workflow.internal;

import org.openstack4j.api.workflow.WorkflowDefinitionService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.payloads.InputStreamPayload;
import org.openstack4j.model.workflow.Scope;
import org.openstack4j.model.workflow.WorkflowDefinition;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowDefinition;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowDefinition.MistralWorkflowDefinitions;

import java.io.InputStream;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Workflow definition service implementation.
 *
 * @author Renat Akhmerov
 */
public class WorkflowDefinitionServiceImpl extends BaseMistralService implements WorkflowDefinitionService {

    @Override
    public List<? extends WorkflowDefinition> list() {
        return get(MistralWorkflowDefinitions.class, uri("/workflows")).execute().getList();
    }

    @Override
    public List<? extends WorkflowDefinition> create(InputStream wfText, Scope scope) {
        checkNotNull(wfText);
        checkNotNull(scope);

        Invocation<MistralWorkflowDefinitions> invocation = post(
                MistralWorkflowDefinitions.class,
                uri("/workflows?scope=%s", scope.toString().toLowerCase())
        );

        return invocation.entity(new InputStreamPayload(wfText)).execute().getList();
    }

    @Override
    public WorkflowDefinition get(String identifier) {
        return get(MistralWorkflowDefinition.class, uri("/workflows/%s", identifier)).execute();
    }

    @Override
    public ActionResponse delete(String identifier) {
        return deleteWithResponse(uri("/workflows/%s", identifier)).execute();
    }

}
