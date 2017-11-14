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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.openstack4j.model.map.reduce.ServiceConfig;
import com.huawei.openstack4j.model.map.reduce.builder.ServiceConfigBuilder;

/**
 * Model represent attributes of ServiceConfig
 *
 * @author Ekasit Kijsipongse
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReduceServiceConfig extends HashMap<String,String> implements ServiceConfig {

	public static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
	@Override
	public String get(String name) {
		return super.get(name);
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public Map<String,String> getConfigs() {
		return this;
	}

        /**
         * {@inheritDoc}
         */
        @Override
        public ServiceConfigBuilder toBuilder() {
                return new ConcreteServiceConfigBuilder(this);
        }

        /**
         * {@inheritDoc}
         */
        public static ServiceConfigBuilder builder() {
                return new ConcreteServiceConfigBuilder();
        }


        public static class ConcreteServiceConfigBuilder implements ServiceConfigBuilder {

                private MapReduceServiceConfig m;

                ConcreteServiceConfigBuilder() {
                        this(new MapReduceServiceConfig());
                }

                ConcreteServiceConfigBuilder(MapReduceServiceConfig m) {
                        this.m = m;
                }

                @Override
                public ServiceConfigBuilder set(String name, String value) {
                        m.put(name,value);
                        return this;
                }

                @Override
                public ServiceConfig build() {
                    return m;
                }

                @Override
                public ConcreteServiceConfigBuilder from(ServiceConfig in) {
                    m = (MapReduceServiceConfig)in;
                    return this;
                }

        }

}
