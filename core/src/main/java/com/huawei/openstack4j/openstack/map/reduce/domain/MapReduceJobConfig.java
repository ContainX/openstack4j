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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.map.reduce.JobConfig;
import com.huawei.openstack4j.model.map.reduce.builder.JobConfigBuilder;

/**
 * Model represent attributes of JobConfig
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class MapReduceJobConfig implements JobConfig {

    private static final long serialVersionUID = 1L;

    @JsonProperty("configs")
    private Map<String, Object> configs;
    @JsonProperty("args")
    private List<Object> args;
    @JsonProperty("params")
    private Map<String, Object> params;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getConfigs() {
        return configs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getArgs() {
        return args;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                   .add("configs", configs)
                   .add("args", args)
                   .add("params", params)
                   .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobConfigBuilder toBuilder() {
        return new concreteJobConfigBuilder(this);
    }

    /**
     * @return the job config builder
     */
    public static JobConfigBuilder builder() {
        return new concreteJobConfigBuilder();
    }

    public static class concreteJobConfigBuilder implements JobConfigBuilder {

        MapReduceJobConfig m;

        concreteJobConfigBuilder() {
            this(new MapReduceJobConfig());
        }

        concreteJobConfigBuilder(MapReduceJobConfig m) {
            this.m = m;
        }

        @Override
        public JobConfig build() {
            return m;
        }

        @Override
        public JobConfigBuilder from(JobConfig in) {
            m = (MapReduceJobConfig) in;
            return this;
        }

        @Override
        public JobConfigBuilder addConfig(String name, Object value) {
            if (m.configs == null)
                m.configs = new HashMap<String, Object>();
            m.configs.put(name, value);
            return this;
        }

        @Override
        public JobConfigBuilder addArg(Object arg) {
            if (m.args == null)
                m.args = Lists.newArrayList();
            m.args.add(arg);
            return this;
        }

        @Override
        public JobConfigBuilder addParam(String param, Object value) {
            if (m.params == null)
                m.params = new HashMap<String, Object>();
            m.params.put(param, value);
            return this;
        }

    }
}
