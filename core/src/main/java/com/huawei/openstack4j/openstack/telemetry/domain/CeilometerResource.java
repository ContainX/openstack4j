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
package com.huawei.openstack4j.openstack.telemetry.domain;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.telemetry.Resource;

import com.google.common.base.MoreObjects;

/**
 * A single measurement for resource.
 *
 * @author Shital Patil
 */

public class CeilometerResource implements Resource {

    private static final long serialVersionUID = 1L;

    @JsonProperty("resource_id")
    String id;

    @JsonProperty("user_id")
    private String userId;

    private String source;

    @JsonProperty("project_id")
    private String projectId;

    @JsonProperty("first_sample_timestamp")
    private Date firstSampleTimestamp;

    @JsonProperty("last_sample_timestamp")
    private Date lastSampleTimeStamp;

    @JsonProperty("metadata")
    Map<String, Object> metaData;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getFirstSampleTimestamp() {
        return firstSampleTimestamp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getLastSampleTimestamp() {
        return lastSampleTimeStamp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProjectId() {
        return projectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getMeataData() {
        return metaData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("userId", userId).add("projectId", projectId).add("source", source).add("firstSanpleTimestamp", firstSampleTimestamp)
                .add("lastSanpleTimestamp", lastSampleTimeStamp).toString();
    }

}
