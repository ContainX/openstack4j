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
package org.openstack4j.api.networking;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.Builders;
import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkUpdate;

/**
 * OpenStack (Neutron) Network based Operations
 * 
 * @author Jeremy Unruh
 */
public interface NetworkService extends RestService {
	
	/**
	 * @author HY(huangyi813 @ qq.com)
	 * Lists the networks to which the current authorized tenant has access
	 * @param filteringParams map (name, value) of filtering parameters
	 * 
	 * @return List of Network
	 */
	List<? extends Network> list(Map<String, String> filteringParams);
	

	/**
	 * Lists the networks to which the current authorized tenant has access
	 * 
	 * @return List of Network
	 */
	List<? extends Network> list();
	
	/**
	 * Gets the network by ID
	 * 
	 * @param networkId the network identifier
	 * @return the Network or null if not found
	 */
	Network get(String networkId);
	
	/**
	 * Updates a network associated by the specified {@code networkId}
	 * 
	 * @param networkId the network identifier
	 * @param network the network options to update (see {@link Builders#networkUpdate()}
	 * @return the updated network
	 */
	Network update(String networkId, NetworkUpdate network);
	
	/**
	 * Deletes a specified network and its associated resources
	 *  
	 * @param networkId the network identifier
	 * @return the action response
	 */
	ActionResponse delete(String networkId);

	/**
	 * Creates a new Network
	 * 
	 * @param network the network to create
	 * @return the newly created network
	 */
	Network create(Network network);
	
}
