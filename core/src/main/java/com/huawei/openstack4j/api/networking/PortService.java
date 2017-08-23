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
package com.huawei.openstack4j.api.networking;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.Port;
import com.huawei.openstack4j.model.network.options.PortListOptions;

/**
 * OpenStack (Neutron) Port based Operations
 * 
 * @author Jeremy Unruh
 */
public interface PortService extends RestService {

	/**
	 * Lists all Ports authorized by the current Tenant
	 *
	 * @return the list of ports
	 */
	List<? extends Port> list();

	/**
     * Lists all Ports authorized by the current Tenant
     *
     * @param options filtering options
     * @return the list of ports
     */
    List<? extends Port> list(PortListOptions options);
	
	/**
	 * Gets the Port by ID
	 *
	 * @param portId the port identifier
	 * @return the port or null if not found
	 */
	Port get(String portId);
	
	/**
	 * Delete a Port by ID
	 * 
	 * @param portId the port identifier to delete
	 * @return the action response
	 */
	ActionResponse delete(String portId);
	
	/**
	 * Creates a new Port
	 * @param port the port to create
	 * @return the newly create Port
	 */
	Port create(Port port);

	/**
	 * Creates new Ports
	 * @param ports the ports to create
	 * @return the newly created Ports
	 */
	List<? extends Port> create(List<? extends Port> ports);
	
	/**
	 * Updates an existing Port.  The Port identifier must be set on the port object to be successful
	 * @param port the port to update
	 * @return the updated port
	 */
	Port update(Port port);
}
