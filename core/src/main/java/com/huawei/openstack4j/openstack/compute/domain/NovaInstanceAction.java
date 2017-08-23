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
package com.huawei.openstack4j.openstack.compute.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.InstanceAction;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

@JsonRootName("instanceAction")
public class NovaInstanceAction implements InstanceAction {

    @JsonProperty("action")
    private String action;
    @JsonProperty("instance_uuid")
    private String instanceUuid;
    @JsonProperty("message")
    private String message;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("start_time")
    private Date startTime;
    @JsonProperty("user_id")
    private String userId;

    public NovaInstanceAction() {

    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getInstanceUuid() {
        return instanceUuid;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("action", action).add("instance_uuid", instanceUuid)
                .add("message", message).add("project_id", projectId).add("request_id", requestId)
                .add("start_time", startTime).add("user_id", userId).toString();
    }

    public static class NovaInstanceActions extends ListResult<NovaInstanceAction> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("instanceActions")
        private List<NovaInstanceAction> actions;

        @Override
        protected List<NovaInstanceAction> value() {
            return actions;
        }

    }

}
