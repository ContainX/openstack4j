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
package com.huawei.openstack4j.connectors.resteasy;

import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.plugins.providers.InputStreamProvider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;

/**
 * Rest Easy provider setup to play nice with Jackson
 * 
 * @author Jeremy Unruh
 */
public class ResteasyClientFactory extends ResteasyProviderFactory {

    private static final ResteasyClientFactory INSTANCE = new ResteasyClientFactory();

    private JacksonJsonProvider jsonProvider;
    private InputStreamProvider streamProvider;

    public ResteasyClientFactory() {
        super();
        
        addContextResolver(new CustomContextResolver());
        
        jsonProvider = new JacksonJsonProvider();
        addMessageBodyReader(jsonProvider);
        addMessageBodyWriter(jsonProvider);

        streamProvider = new InputStreamProvider();
        addMessageBodyReader(streamProvider);
        addMessageBodyWriter(streamProvider);
    }

    public static ResteasyClientFactory getInstance() {
        return INSTANCE;
    }
    

    private static final class CustomContextResolver implements ContextResolver<ObjectMapper> {

        /**
         * {@inheritDoc}
         */
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return ObjectMapperSingleton.getContext(type);
        }
    }

}
