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
import java.util.Map;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.ConfigInfo;
import com.huawei.openstack4j.model.map.reduce.Plugin;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of MapReducePlugin
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */

@JsonRootName("plugin")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReducePlugin implements Plugin {

    private static final long serialVersionUID = 1L;

    private String description;
    private List<String> versions;
    private String name;
    private String title;

    @JsonProperty("node_processes")
    private Map<String, List<String>> serviceProcesses;
    @JsonProperty("required_image_tags")
    private List<String> requiredImageTags;
    @JsonProperty("configs")
    private List<MapReduceConfigInfo> configInfos;

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
    public List<String> getVersions() {
        return versions;
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
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String,List<String>> getServiceProcesses() {
        return serviceProcesses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRequiredImageTags() {
        return requiredImageTags;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ConfigInfo> getConfigInfos() {
        return configInfos;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("name", name)
                .add("title", title)
                .add("description", description)
                .add("versions", versions)
                .add("required_image_tags", requiredImageTags)
                .add("node_processes",serviceProcesses)
                .add("configs",configInfos)
                .toString();
    }

    public static class MapReducePlugins extends ListResult<MapReducePlugin> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("plugins")
        private List<MapReducePlugin> plugins;

        public List<MapReducePlugin> value() {
            return plugins;
        }
    }

}
