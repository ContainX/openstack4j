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
import org.openstack4j.model.workflow.builder.WorkflowExecutionBuilder;

import javax.annotation.Nullable;
import java.util.Map;


/**
 * A workflow execution.
 *
 * @author Renat Akhmerov
 */
public interface WorkflowExecution extends Execution, Buildable<WorkflowExecutionBuilder> {
    /**
     * @return The meta parameters of workflow execution specific to workflow type.
     *      Example: a reverse workflow requires the parameter 'task_name' which
     *      specifies the target task in the workflow graph.
     */
    Map<String, ?> getParameters();

    /**
     * @return The id of the parent task execution.
     */
    @Nullable
    String getTaskExecutionId();

    /**
     * @return The input parameters of this workflow execution.
     */
    Map<String, ?> getInput();

    /**
     * @return The output of this workflow execution.
     */
    Map<String, ?> getOutput();
}
