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
package com.huawei.openstack4j.model.identity.v2.builder;

import java.net.URI;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v2.Endpoint;

/**
 * A Builder which creates an Endpoint
 *  
 * @author jeremy
 */
public interface EndpointBuilder extends Builder<EndpointBuilder, Endpoint>{

	/**
	 * @see Endpoint#getRegion()
	 */
	EndpointBuilder region(String region);
	
	/**
	 * @see Endpoint#getPublicURL()
	 */
	EndpointBuilder publicURL(URI publicURL);
	
	/**
	 * @see Endpoint#getInternalURL()
	 */
	EndpointBuilder internalURL(URI internalURL);
	
	/**
	 * @see Endpoint#getTenantId()
	 */
	EndpointBuilder tenantId(String tenantId);
	
	/**
	 * @see Endpoint#getType()
	 */
	EndpointBuilder type(String type);
	
	/**
	 * @see Endpoint#getId()
	 */
	EndpointBuilder id(String id);
	
	/**
	 * @see Endpoint#getName()
	 */
	EndpointBuilder name(String name);
	
	/**
	 * @see Endpoint#getAdminURL()
	 */
	EndpointBuilder adminURL(URI adminURL);
	
	/**
	 * @see Endpoint#getVersionInfo()
	 */
	EndpointBuilder versionInfo(URI versionInfo);
	
	/**
	 * @see Endpoint#getVersionList()
	 */
	EndpointBuilder versionList(URI versionList);
	
}
