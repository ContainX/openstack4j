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
package com.huawei.openstack4j.core.transport;

import com.google.common.base.Function;

/**
 * HTTP Execution Options
 * 
 * @author Jeremy Unruh
 * @param <R>
 */
public class ExecutionOptions<R> {

    private Function<HttpResponse, R> parser;
    private PropagateResponse propagateResponse;
    
    private ExecutionOptions(Function<HttpResponse, R> parser, PropagateResponse propagateResponse) {
        this.parser = parser;
        this.propagateResponse = propagateResponse;
    }
    
    public static <R> ExecutionOptions<R> create(Function<HttpResponse, R> parser) {
        return new ExecutionOptions<R>(parser, null);
    }
    
    public static <R> ExecutionOptions<R> create(PropagateResponse propagateResponse) {
        return new ExecutionOptions<R>(null, propagateResponse);
    }
    
    public static <R> ExecutionOptions<R> create(Function<HttpResponse, R> parser, PropagateResponse propagateResponse) {
        return new ExecutionOptions<R>(parser, propagateResponse);
    }
    
    /**
     * @return the result function parser or null
     */
    public Function<HttpResponse, R> getParser() {
        return parser;
    }
    
    /**
     * @return true if a result parser is associated with these options
     */
    public boolean hasParser() {
        return parser != null;
    }
    
    /**
     * If a PropagateResponse is associated with these options then it will be invoked otherwise a no-op
     * @param response the HttpResponse
     */
    public void propagate(HttpResponse response) {
        if (propagateResponse != null)
            propagateResponse.propagate(response);
    }
} 

