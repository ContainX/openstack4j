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
package org.openstack4j.openstack.sahara.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.sahara.ServiceConfig;
import org.openstack4j.model.sahara.builder.ServiceConfigBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * For mapping JSON response to java objects
 *
 * @author Ekasit Kijsipongse
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class SaharaServiceConfig extends HashMap<String,String> implements ServiceConfig {

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

                private SaharaServiceConfig m;

                ConcreteServiceConfigBuilder() {
                        this(new SaharaServiceConfig());
                }

                ConcreteServiceConfigBuilder(SaharaServiceConfig m) {
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
                    m = (SaharaServiceConfig)in;
                    return this;
                }

        }

}
