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
/*
 * 
 */
package org.openstack4j.model.workflow;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.workflow.builder.TaskExecutionBuilder;

import java.util.Map;


/**
 * A task execution.
 *
 * @author Renat Akhmerov
 */
public interface TaskExecution extends Execution, Buildable<TaskExecutionBuilder> {
    /**
     * @return The task name.
     */
    String getName();

    /**
     * TODO: do we need a enum for task types?
     *
     * @return The task type.
     */
    String getType();

    /**
     * @return The ID of the workflow definition that this task belongs to.
     */
    String getWorkflowDefinitionId();

    /**
     * @return The ID of the workflow execution that this task belongs to.
     */
    String getWorkflowExecutionId();

    /**
     * @return The result of this task.
     */
    Map<?, ?> getResult();

    /**
     * @return The variables published into workflow context by this task.
     */
    Map<?, ?> getPublished();

    /**
     * @return {@code True} if this task is fully processed (all decisions made based on its result).
     */
    Boolean isProcessed();
}
