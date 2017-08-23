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
package com.huawei.openstack4j.openstack.storage.object.domain;

import java.util.Map;

import com.huawei.openstack4j.core.transport.HttpRequest;

/**
 * Simple Tuple object used to hold onto an in-bound header map and request object
 * 
 * @author Jeremy Unruh
 */
public final class MetaHeaderRequestWrapper<R> {

    private final String prefix;
    private final HttpRequest<R> request;
    private final Map<String, String> metadata;
    
    private MetaHeaderRequestWrapper(String prefix, Map<String, String> metadata, HttpRequest<R> request) {
        this.prefix = prefix;
        this.metadata = metadata;
        this.request = request;
    }
    
    public static <R> MetaHeaderRequestWrapper<R> of(String prefix, Map<String, String> metadata, HttpRequest<R> request) {
        return new MetaHeaderRequestWrapper<R>(prefix, metadata, request);
    }
    
    public Map<String, String> getMetadata() {
        return metadata;
    }
    
    public HttpRequest<R> getRequest() {
        return request;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
}
