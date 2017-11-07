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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.List;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.map.reduce.ConfigInfo;

/**
 * Model represent attributes of ConfigInfo
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReduceConfigInfo implements ConfigInfo {

    private static final long serialVersionUID = 1L;

    @JsonProperty("default_value")
    private String defaultValue;
    private String name;
    private Integer priority;
    @JsonProperty("config_type")
    private String type;
    @JsonProperty("applicable_target")
    private String applicableTarget;
    @JsonProperty("is_optional")
    private Boolean isOptional;
    private String scope;
    private String description;
    @JsonProperty("config_values")
    private List<String> configValues;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultValue() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApplicableTarget() {
        return applicableTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isOptional() {
        return isOptional;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScope() {
        return scope;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getConfigValues() {
        return configValues;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("name", name)
                .add("description", description)
                .add("priority", priority)
                .add("config_type",type)
                .add("default_value", defaultValue)
                .add("config_values",configValues)
                .add("is_optional",isOptional)
                .add("scope",scope)
                .add("applicable_target",applicableTarget)
                .toString();
    }

}
