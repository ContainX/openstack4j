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
package com.huawei.openstack4j.model.identity.v2;

import java.net.URI;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v2.builder.ServiceEndpointBuilder;

/**
 * OpenStack ServiceEndpoint is an endpoint which is mapped to a {@link Service}
 * 
 * @author Jeremy Unruh
 */
public interface ServiceEndpoint extends ModelEntity, Buildable<ServiceEndpointBuilder> {

	/**
	 * @return the endpoint identifier
	 */
	String getId();
	
	/**
	 * @return the region for this endpoint
	 */
	String getRegion();
	
	/**
	 * @return the service id this endpoint is asssociated with
	 */
	String getServiceId();
	
	/**
	 * @return the public URL for this endpoint
	 */
	URI getPublicURL();
	
	/**
	 * @return the admin URL for this endpoint
	 */
	URI getAdminURL();
	
	/**
	 * @return the internal URL for this endpoint
	 */
	URI getInternalURL();
	
}
