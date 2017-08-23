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
package com.huawei.openstack4j.api;

/**
 * To keep our dependencies simple in the current Openstack4J, we utilize ServiceLoader to load a provider who is responsible
 * for loading the implementation for any of the defined API interfaces.  This allows us to avoid pulling in extra 3rd party 
 * dependencies such as Guice, Inject, etc.  It also allows us flexibility on the provider which may be overriden and choose to bind
 * modules and simple delegate out the {@link #get(Class)} calls
 * 
 * @author Jeremy Unruh
 */
public interface APIProvider {

	/**
	 * Called after the APIProvider is constructed.  This allows the provider to pre-initialize or bind any interface implementations if desired
	 */
	void initialize();
	
	/**
	 * Gets the implementation for the specified interface type
	 *
	 * @param <T> the Openstack4j API type
	 * @param api the API interface
	 * @return the implementation for T
	 * @throws ApiNotFoundException if the API cannot be found
	 */
	<T> T get(Class<T> api);
}
