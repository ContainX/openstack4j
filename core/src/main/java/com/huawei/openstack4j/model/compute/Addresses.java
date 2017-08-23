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
package com.huawei.openstack4j.model.compute;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A container which holds networks with 1 or more addresses
 * 
 * @author Jeremy Unruh
 */
public interface Addresses extends ModelEntity {

	/**
	 * Adds an address to the given network type
	 *
	 * @param key the type of address classification
	 * @param value of the address
	 */
	void add(String key, Address value);
	
	/**
	 * @return the a Map<String, List<Address> 
	 */
	Map<String, List<? extends Address>> getAddresses();
	
	/**
	 * Gets the addresses associated with the given network type
	 *
	 * @param type the type of network
	 * @return the List of Addresses
	 */
	List<? extends Address> getAddresses(String type);
	
}
