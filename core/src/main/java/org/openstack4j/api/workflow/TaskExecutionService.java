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
package org.openstack4j.api.workflow;

import org.openstack4j.common.RestService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.workflow.TaskExecution;

import java.util.List;

/**
 * Service that provides CRUD operations for task executions.
 *
 * @author Renat Akhmerov
 */
public interface TaskExecutionService extends RestService {
    /**
     * List all task executions with details.
     *
     * @return List of task executions.
     */
    List<? extends TaskExecution> list();

    /**
     * Create a new task execution.
     *
     * @param taskExecution Task execution to create.
     * @return Created task execution.
     */
    TaskExecution create(TaskExecution taskExecution);

    /**
     * Get task execution by its ID.
     *
     * @param id Task execution ID.
     * @return Task execution.
     */
    TaskExecution get(String id);

    /**
     * Delete task execution by its ID.
     *
     * @param id Task execution ID.
     * @return HTTP response from the server.
     */
    HttpResponse delete(String id);
}
