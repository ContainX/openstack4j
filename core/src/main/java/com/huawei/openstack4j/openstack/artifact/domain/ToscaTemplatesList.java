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
package com.huawei.openstack4j.openstack.artifact.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.artifact.Artifacts;
import com.huawei.openstack4j.model.artifact.ToscaTemplatesArtifacts;

import java.util.List;

/**
 * Created by vadavi on 18-01-2017.
 */
public class ToscaTemplatesList implements ToscaTemplatesArtifacts {

    @JsonProperty("tosca_templates")
    private List<ToscaTemplates> toscaTemplates = null;
    @JsonProperty("schema")
    private String schema;
    @JsonProperty("first")
    private String first;

    @Override
    public List<ToscaTemplates> getToscaTemplates() {
        return toscaTemplates;
    }

    public void setToscaTemplates(List<ToscaTemplates> toscaTemplates) {
        this.toscaTemplates = toscaTemplates;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
