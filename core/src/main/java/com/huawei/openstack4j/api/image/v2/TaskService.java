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
package com.huawei.openstack4j.api.image.v2;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.image.v2.Task;

/**
 * Image (Glance) V2 Tasks Api
 * @author emjburns
 */
public interface TaskService extends RestService {
    /**
     * List tasks
     * @return a list of tasks.
     */
    List<? extends Task> list();

    /**
     * List tasks filtering by parameters.
     * Use these parameters to do pagination as described in
     * http://developer.openstack.org/api-ref/image/v2/index.html#list-tasks
     * @param filteringParams
     * @return a list of tasks
     */
    List<? extends Task> list(Map<String, String> filteringParams);

    /**
     * Show details for a task.
     * @param taskId
     * @return a specific task
     */
    Task get(String taskId);

    /**
     * Create a task
     * @return the task
     */
    Task create(Task task);
}
