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
package com.huawei.openstack4j.api.compute;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.FloatingIP;
import com.huawei.openstack4j.model.compute.Server;

/**
 * OpenStack Compute Floating-IP Operation API.
 *
 * @author Nathan Anderson
 */
public interface ComputeFloatingIPService extends RestService {

	/**
	 * List floating ips associated with current tenant.
	 *
	 * @return the list<? extends floating i p>
	 */
	List<? extends FloatingIP> list();

	/**
	 * Lists the current Floating IP Pool Names
	 * @return List of floating IP pool names
	 */
	List<String> getPoolNames();

	/**
	 * Allocate a floating ip address to tenant.
	 *
	 * @param pool the pool
	 * @return the floating ip
	 */
	FloatingIP allocateIP(String pool);

	/**
	 * Deallocate ip address from tenant.
	 *
	 * @param id the id of floating ip address
	 * @return the action response
	 */
	ActionResponse deallocateIP(String id);


	/**
	 * Adds floating-ip to server.
	 *
	 * @param server the server
	 * @param fixedIpAddress the fixed ip address
	 * @param ipAddress the ip address
	 * @return the action response
	 */
	ActionResponse addFloatingIP(Server server, String fixedIpAddress, String ipAddress);

	/**
	 * Adds floating-ip to server.
	 *
	 * @param server the server
	 * @param ipAddress the ip address
	 * @return the action response
	 */
	ActionResponse addFloatingIP(Server server, String ipAddress);

	/**
	 * Remove floating-ip from server
	 *
	 * @param server the server
	 * @param ipAddress the ip address
	 */
	ActionResponse removeFloatingIP(Server server, String ipAddress);

	/**
	 * Adds floating-ip to server.
	 * @param serverId the id of the server
	 * @param fixedIpAddress the fixed ip address
	 * @param ipAddress the ip address
	 * @return the action response
	 */
	ActionResponse addFloatingIP(String serverId, String fixedIpAddress, String ipAddress);

	/**
	 * Adds floating-ip to server.
	 * @param serverId the id of the server
	 * @param ipAddress the ip address
	 * @return the action response
	 */
	ActionResponse addFloatingIP(String serverId, String ipAddress);

	/**
	 * Remove floating-ip from server
	 * @param serverId  the id of the server
	 * @param ipAddress the ip address
	 * @return
	 */
  ActionResponse removeFloatingIP(String serverId, String ipAddress) ;

}
