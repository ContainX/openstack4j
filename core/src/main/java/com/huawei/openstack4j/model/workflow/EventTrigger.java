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
package com.huawei.openstack4j.model.workflow;

import java.util.Date;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.workflow.builder.CronTriggerBuilder;


/**
 * A cron trigger.
 *
 * @author Renat Akhmerov
 */
public interface EventTrigger extends ModelEntity, Buildable<CronTriggerBuilder> {

    /**
     * @return The id of this definition.
     */
    String getId();

    /**
     * @return The name of this definition.
     */
    String getName();
    /**
     * @return The name of workflow that this trigger needs to run.
     */
    String getWorkflowName();

    /**
     * @return The Id of workflow that this trigger needs to run.
     */
    String getWorkflowId();

    /**
     * @return The input values with which the workflow needs to run.
     */
    Map<String, ?> getWorkflowInput();

    /**
     * @return The workflow type specific parameters with which the workflow needs to run.
     */
    Map<String, ?> getWorkflowParameters();

    /**
     * @return The name of the exchange for this triger.
     */
    String getExchange();

    /**
     * @return The name of the topic for this triger.
     */
    String getTopic();

    /**
     * @return The name of the event for this triger.
     */
    String getEvent();

    /**
     * @return The id of the project (tenant) this trigger belongs to.
     */
    String getProjectId();

    /**
     * @return The scope of this trigger.
     */
    Scope getScope();

    /**
     * @return The time that this trigger was createdAt at.
     */
    Date getCreated();

    /**
     * @return The time that this trigger was last updatedAt at.
     */
    Date getUpdated();
}
