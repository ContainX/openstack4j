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
package com.huawei.openstack4j.openstack.image.v2.internal;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.image.v2.TaskService;
import com.huawei.openstack4j.model.image.v2.Task;
import com.huawei.openstack4j.openstack.image.v2.domain.GlanceTask;

/**
 * Glance V2 task service implementation
 * @author emjburns
 */
public class TaskServiceImpl extends BaseImageServices implements TaskService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Task> list() {
        return get(GlanceTask.Tasks.class, uri("/tasks")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Task> list(Map<String, String> filteringParams) {
        return get(GlanceTask.Tasks.class, uri("/tasks")).params(filteringParams).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task get(String taskId) {
        return get(GlanceTask.class, uri("/tasks/%s",taskId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task create(Task task) {
        return post(GlanceTask.class, uri("/tasks")).entity(task).execute();
    }
}
