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
package com.huawei.openstack4j.api.identity.v2;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v2.Service;
import com.huawei.openstack4j.model.identity.v2.ServiceEndpoint;

/**
 * Manages OpenStack service(s), such as Compute (Nova), Object Storage (Swift), or Image Service (Glance).
 * 
 * @author Jeremy Unruh
 */
public interface ServiceManagerService extends RestService {

	/**
	 * List current Services on the OpenStack System
	 *
	 * @return the list<? extends service>
	 */
	List<? extends Service> list();
	
	/**
	 * Gets the specified Service by it's identifier
	 *
	 * @param serviceId the service id
	 * @return the service
	 */
	Service get(String serviceId);
	
	/**
	 * Creates a new Service
	 *
	 * @param name the name of the service
	 * @param type the type of service
	 * @param description the description for the service
	 * @return the service created
	 */
	Service create(String name, String type, String description);
	
	/**
	 * Deletes a Service based on it's id
	 *
	 * @param serviceId the service id
	 * @return the action response
	 */
	ActionResponse delete(String serviceId);
	
	/**
	 * Queries for service related Endpoints (endpoints mapped against services)
	 * 
	 * @return List of ServiceEndpoint(s)
	 */
	List<? extends ServiceEndpoint> listEndpoints();
	
	/**
	 * Creates a new Endpoint associated to a service identifier
	 * 
	 * @param region the endpoint region
	 * @param serviceId the service identifier the endpoint is associated with
	 * @param publicURL the public URL
	 * @param adminURL the admin URL
	 * @param internalURL the internal URL
	 * @return the create service endpoint
	 */
	ServiceEndpoint createEndpoint(String region, String serviceId, String publicURL, String adminURL, String internalURL);
	
	/**
	 * Deletes an Endpoint
	 * 
	 * @param endpointId the endpoint identifier
	 * @return the action response
	 */
	ActionResponse deleteEndpoint(String endpointId);
	
}
