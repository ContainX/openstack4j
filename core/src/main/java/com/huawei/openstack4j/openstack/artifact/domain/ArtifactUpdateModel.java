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
import com.huawei.openstack4j.model.artifact.ArtifactUpdate;
import com.huawei.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import com.huawei.openstack4j.model.common.builder.BasicResourceBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Created by vadavi on 20-01-2017.
 */
public class ArtifactUpdateModel implements ArtifactUpdate {

    @JsonProperty("op")
    private String op;
    @JsonProperty("path")
    private String path;
    @JsonProperty("value")
    private String value;

    @Override
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public ArtifactUpdateBuilder toBuilder() {
        return new ArtifactUpdateConcreteBuilder(this);
    }

    public static ArtifactUpdateBuilder builder() {
        return new ArtifactUpdateConcreteBuilder();
    }

    public static class ArtifactUpdates extends ListResult<ArtifactUpdateModel> {

        @Override
        protected List<ArtifactUpdateModel> value() {
            return null;
        }
    }

    public static class ArtifactUpdateConcreteBuilder implements ArtifactUpdateBuilder {

        private ArtifactUpdateModel artifactUpdateModel;

        ArtifactUpdateConcreteBuilder() {
            this(new ArtifactUpdateModel());
        }

        ArtifactUpdateConcreteBuilder(ArtifactUpdateModel artifactUpdateModel) {
            this.artifactUpdateModel = artifactUpdateModel;
        }

        @Override
        public ArtifactUpdateBuilder op(String op) {
            artifactUpdateModel.op = op;
            return this;
        }

        @Override
        public ArtifactUpdateBuilder path(String path) {
            artifactUpdateModel.path = path;
            return this;
        }

        @Override
        public ArtifactUpdateBuilder value(String value) {
            artifactUpdateModel.value = value;
            return this;
        }

        @Override
        public ArtifactUpdate build() {
            return artifactUpdateModel;
        }

        @Override
        public ArtifactUpdateBuilder from(ArtifactUpdate in) {
            this.artifactUpdateModel = (ArtifactUpdateModel) in;
            return this;
        }

    }
}
