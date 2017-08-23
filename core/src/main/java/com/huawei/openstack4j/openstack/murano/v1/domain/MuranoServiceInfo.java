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

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.openstack4j.model.murano.v1.domain.ServiceInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Mahotkin.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MuranoServiceInfo implements ServiceInfo {
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    private List<MuranoActionInfo> actions;

    @JsonProperty
    private String status;

    @JsonAnySetter
    public void setAction(String key, Object value) {
        if (key.equals("_actions")) {
            if (this.actions == null) {
                this.actions = new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();

            for (Map.Entry<String, Object> entry : ((Map<String, Object>) value).entrySet()) {
                MuranoActionInfo action = mapper.convertValue(entry.getValue(), MuranoActionInfo.class);
                action.setId(entry.getKey());

                this.actions.add(action);
            }
        }
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
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MuranoActionInfo> getActions() {
        return this.actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return this.status;
    }
}
