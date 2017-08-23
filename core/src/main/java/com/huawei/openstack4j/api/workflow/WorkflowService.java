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

import com.huawei.openstack4j.common.RestService;

/**
 * Workflow Service (Mistral) API
 *
 * TODO: add 'services' and 'resources'.
 *
 * @author Renat Akhmerov
 */
public interface WorkflowService extends RestService {

	/**
	 * Workflow definition Service API.
	 *
	 * @return The workflow definition service.
	 */
	WorkflowDefinitionService workflowDefinitions();

	/**
	 * Action definition Service API.
	 *
	 * @return The action definition service.
	 */
	ActionDefinitionService actionDefinitions();

    /**
     * Workbook definition Service API
     *
     * @return The workbook definition service.
     */
    WorkbookDefinitionService workbookDefinitions();

    /**
     * Workflow execution Service API
     *
     * @return The workflow execution service.
     */
    WorkflowExecutionService workflowExecutions();

    /**
     * Task execution Service API
     *
     * @return The task execution service.
     */
    TaskExecutionService taskExecutions();

    /**
     * Action execution Service API
     *
     * @return The workflow execution service.
     */
    ActionExecutionService actionExecutions();

    /**
     * Validation Service API
     *
     * @return The validation service.
     */
    ValidationService validation();

    /**
     * Workflow environment Service API
     *
     * @return The workflow environment service.
     */
    WorkflowEnvironmentService environments();

    /**
     * Cron trigger Service API
     *
     * @return The cron trigger service.
     */
    CronTriggerService cronTriggers();

    /**
     * Event trigger Service API
     *
     * @return The event trigger service.
     */
    EventTriggerService eventTriggers();
}
