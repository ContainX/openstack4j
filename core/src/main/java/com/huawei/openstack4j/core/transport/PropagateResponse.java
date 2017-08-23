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

/**
 * Allows for propagation depending on the state of a response.  If applied to an HttpExecution the execution will
 * call the {@link #propagate(HttpResponse)} method.  The method will either throw a ClientResponseException variant
 * or do nothing letting the execution code handle like normal
 * 
 * @author Jeremy Unruh
 */
public interface PropagateResponse {

    /**
     * Called to allow for optional exception propagation depending on the HttpResponse state
     * @param response the http response
     * @throws ClientResponseException variant if execution deemed a failure
     */
    void propagate(HttpResponse response);
    
}
