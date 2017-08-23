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
package com.huawei.openstack4j.openstack.magnum;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.magnum.Environment;
import com.huawei.openstack4j.model.magnum.EnvironmentBuilder;

import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MagnumEnvironment implements Environment {
    private static final long serialVersionUID = 1L;
    @JsonProperty("PATH")
    private String path;
    @JsonProperty("LD_LIBRARY_PATH")
    private String ldLibraryPath;

    public static EnvironmentBuilder builder() {
        return new EnvironmentConcreteBuilder();
    }

    @Override
    public EnvironmentBuilder toBuilder() {
        return new EnvironmentConcreteBuilder(this);
    }

    public String getPath() {
        return path;
    }

    public String getLdLibraryPath() {
        return ldLibraryPath;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("path", path).add("ldLibraryPath", ldLibraryPath)
                .toString();
    }

    /**
     * Concrete builder containing MagnumEnvironment as model
     *
     */
    public static class EnvironmentConcreteBuilder implements EnvironmentBuilder {
        MagnumEnvironment model;

        public EnvironmentConcreteBuilder() {
            this(new MagnumEnvironment());
        }

        public EnvironmentConcreteBuilder(MagnumEnvironment model) {
            this.model = model;
        }

        @Override
        public Environment build() {
            return model;
        }

        @Override
        public EnvironmentBuilder from(Environment in) {
            if (in != null)
                this.model = (MagnumEnvironment) in;
            return this;
        }

        @Override
        public EnvironmentBuilder path(String path) {
            model.path = path;
            return this;
        }

        @Override
        public EnvironmentBuilder ldLibraryPath(String ldLibraryPath) {
            model.ldLibraryPath = ldLibraryPath;
            return this;
        }
    }

}
