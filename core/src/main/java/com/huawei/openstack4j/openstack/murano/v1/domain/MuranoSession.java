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
import com.huawei.openstack4j.model.murano.v1.domain.AppCatalogSession;

import com.google.common.base.MoreObjects;

/**
 * @author Nikolay Mahotkin.
 */
public class MuranoSession implements AppCatalogSession {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;

    @JsonProperty
    private String state;

    @JsonProperty
    private String updated;

    @JsonProperty
    private String created;

    @JsonProperty
    private String version;

    @JsonProperty("environment_id")
    private String envId;

    @JsonProperty("user_id")
    private String userId;

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
    public String getState() {
        return this.state;
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
    public String getCreated() {
        return this.created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEnvId() {
        return this.envId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", this.id)
                .add("state", this.state)
                .add("created", this.created)
                .add("updated", this.updated)
                .add("environment_id", this.envId)
                .add("user_id", this.userId)
                .add("version", this.version)
                .toString();
    }
}
