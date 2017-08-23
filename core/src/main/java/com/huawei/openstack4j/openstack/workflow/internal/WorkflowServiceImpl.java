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
package com.huawei.openstack4j.openstack.workflow.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.workflow.ActionDefinitionService;
import com.huawei.openstack4j.api.workflow.ActionExecutionService;
import com.huawei.openstack4j.api.workflow.CronTriggerService;
import com.huawei.openstack4j.api.workflow.EventTriggerService;
import com.huawei.openstack4j.api.workflow.TaskExecutionService;
import com.huawei.openstack4j.api.workflow.ValidationService;
import com.huawei.openstack4j.api.workflow.WorkbookDefinitionService;
import com.huawei.openstack4j.api.workflow.WorkflowDefinitionService;
import com.huawei.openstack4j.api.workflow.WorkflowEnvironmentService;
import com.huawei.openstack4j.api.workflow.WorkflowExecutionService;
import com.huawei.openstack4j.api.workflow.WorkflowService;

/**
 * Trove API Implementation
 *
 * @author sumit gandhi
 */
public class WorkflowServiceImpl extends BaseMistralService implements WorkflowService {
    @Override
    public WorkflowDefinitionService workflowDefinitions() {
        return Apis.get(WorkflowDefinitionService.class);
    }

    @Override
    public ActionDefinitionService actionDefinitions() {
        return Apis.get(ActionDefinitionService.class);
    }

    @Override
    public WorkbookDefinitionService workbookDefinitions() {
        return Apis.get(WorkbookDefinitionService.class);
    }

    @Override
    public WorkflowExecutionService workflowExecutions() {
        return Apis.get(WorkflowExecutionService.class);
    }

    @Override
    public TaskExecutionService taskExecutions() {
        return Apis.get(TaskExecutionService.class);
    }

    @Override
    public ActionExecutionService actionExecutions() {
        return Apis.get(ActionExecutionService.class);
    }

    @Override
    public ValidationService validation() {
        return Apis.get(ValidationService.class);
    }

    @Override
    public WorkflowEnvironmentService environments() {
        return Apis.get(WorkflowEnvironmentService.class);
    }

    @Override
    public CronTriggerService cronTriggers() {
        return Apis.get(CronTriggerService.class);
    }

    @Override
    public EventTriggerService eventTriggers() {
        return Apis.get(EventTriggerService.class);
    }
}
