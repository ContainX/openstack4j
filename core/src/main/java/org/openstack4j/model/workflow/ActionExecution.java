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
import org.openstack4j.model.workflow.builder.ActionExecutionBuilder;

import java.util.List;
import java.util.Map;


/**
 * An action execution.
 *
 * @author Renat Akhmerov
 */
public interface ActionExecution extends Execution, Buildable<ActionExecutionBuilder> {
    /**
     * @return The name of the corresponding task.
     */
    String getTaskName();

    /**
     * @return The id of the corresponding task execution.
     */
    String getTaskExecutionId();

    /**
     * @return The name of the action.
     */
    String getName();

    /**
     * @return {@code True} if the result of this action execution is accepted.
     */
    Boolean isAccepted();

    /**
     * @return The input parameters of this action execution.
     */
    Map<String, ?> getInput();

    /**
     * @return The output of this action execution.
     */
    Map<String, ?> getOutput();
}
