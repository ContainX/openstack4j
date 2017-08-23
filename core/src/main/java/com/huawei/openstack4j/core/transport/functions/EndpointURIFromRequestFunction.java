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
package com.huawei.openstack4j.core.transport.functions;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import com.google.common.base.Function;

import com.huawei.openstack4j.core.transport.HttpRequest;

/**
 * Builds a URI comprising of Endpoint and Path from a HttpRequest object
 * 
 * @author Jeremy Unruh
 */
public class EndpointURIFromRequestFunction implements Function<HttpRequest<?>, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String apply(HttpRequest<?> request) {
        if (request.getEndpoint().endsWith(URI_SEP) || request.getPath().startsWith(URI_SEP))
            return escape(request.getEndpoint() + request.getPath());
        
        return escape(request.getEndpoint() + URI_SEP + request.getPath());
    }
    
    private String escape(String uri) {
        return uri.replaceAll(" ", "%20");
    }

}
