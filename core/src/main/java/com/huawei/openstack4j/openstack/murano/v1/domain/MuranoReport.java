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
package com.huawei.openstack4j.openstack.murano.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.murano.v1.domain.Report;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Nikolay Mahotkin.
 */
public class MuranoReport implements Report {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String entity;

    @JsonProperty("entity_id")
    private String entityId;

    @JsonProperty
    private String level;

    @JsonProperty
    private String created;

    @JsonProperty
    private String updated;

    @JsonProperty
    private String text;

    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty
    private String id;

    @JsonProperty
    private String details;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEntity() {
        return this.entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEntityId() {
        return this.entityId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreated() {
        return this.created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUpdated() {
        return this.updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDetails() {
        return this.details;
    }

    public static class MuranoReports extends ListResult<MuranoReport> {
        private static final long serialVersionUID = 1L;

        @JsonProperty
        protected List<MuranoReport> reports;

        @Override
        protected List<MuranoReport> value() {
            return this.reports;
        }
    }
}
