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

import java.util.List;

import com.google.common.collect.SortedSetMultimap;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Link;
import com.huawei.openstack4j.model.identity.AuthVersion;

/**
 * Access is the entity returned when Authenticated by the Identity service
 * 
 * @author Jeremy Unruh
 */
public interface Access extends ModelEntity {

	/**
	 * @return the authorized token
	 */
	Token getToken();
	
	/**
	 * @return the service catalog
	 */
	List<? extends Service> getServiceCatalog();
	
	/**
     * A Lazy loading Aggregated Service Catalog Mapping.  The key is a stripped version service type or name with a collection
     * of Services sorted by version
     * 
     * @return sorted aggregate service catalog
     */
	SortedSetMultimap<String, ? extends Service> getAggregatedCatalog();
	
	/**
	 * @return the original endpoint used to authenticate
	 */
	String getEndpoint();
	
	/**
	 * @return details about the current user
	 */
	UserDetails getUser();
	
	/**
	 * If Access is being wrapped such as in V3 then this will return the underlying wrapped instance.  Otherwise it returns itself
	 * 
	 * @return the unwrapped underlying data source
	 */
	<T> T unwrap();
	
	
	/**
	 * @return the internal UUID used for cache lookups of this access
	 */
	String getCacheIdentifier();
	
	/**
	 * @return the version of the authentication method
	 */
	AuthVersion getVersion();
	
	public interface UserDetails
	{
		String getId();

		String getName();

		String getUsername();
		
		boolean isEnabled();

		List<? extends Role> getRoles();

		List<? extends Link> getRolesLinks();
	}
	
	public interface Service
	{
		String getType();

		String getName();

		ServiceType getServiceType();

		List<? extends Endpoint> getEndpoints();

		List<? extends Link> getEndpointsLinks();
		
		Integer getVersion();
	}
}
