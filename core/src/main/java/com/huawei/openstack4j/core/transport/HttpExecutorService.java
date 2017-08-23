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
 * A Service which is responsible of execution an HttpRequest
 * 
 * @author Jeremy Unruh
 */
public interface HttpExecutorService {

	/**
	 * Executes the given request and returns the {@code HttpResponse} result from the server
	 *
	 * @param <R> the underlying return entity type
	 * @param request the request to execute
	 * @return HttpResponse from the server
	 */
	<R> HttpResponse execute(HttpRequest<R> request);
	
	/**
	 * @return the executors display friendly name.  useful for logging or tests
	 */
	String getExecutorDisplayName();
}
