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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.Firewall;
import com.huawei.openstack4j.model.network.ext.FirewallUpdate;

/**
 * <p>Networking (Neutron) FwaaS Firewall Extension API.</p>
 * 
 * <p>Represents a logical firewall resource that a tenant can instantiate and manage. A firewall is associated with one firewall policy.</p>
 * 
 * <p>
	 * The FWaaS extension provides OpenStack users with the ability to deploy firewalls to protect their networks. The FWaaS extension enables you to:
	 * <ul>
	 * 		<li>Apply firewall rules on traffic entering and leaving tenant networks.</li>
	 * 		<li>Support for applying tcp, udp, icmp, or protocol agnostic rules.</li>
	 * 		<li>Creation and sharing of firewall policies which hold an ordered collection of the firewall rules.</li>
	 * 		<li>Audit firewall rules and policies.</li>
	 * </ul>
 * </p>
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallService extends RestService {
    /**
     * List all Firewall(s) that the current tenant has access to.
     *
     * @return list of all Firewall(s)
     */
    List<? extends Firewall> list();

    /**
     * Returns list of Firewall(s) filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return filtered list of Firewall(s)
     */
    List<? extends Firewall> list(Map<String, String> filteringParams);

    /**
     * Get the specified Firewall by ID
     *
     * @param firewallId the Firewall identifier
     * @return the Firewall or null if not found
     */
    Firewall get(String firewallId);
    
    /**
     * Delete the specified Firewall by ID
     * @param firewallId the Firewall identifier
     * @return the action response
     */
    ActionResponse delete(String firewallId);
    
    /**
     * Create a Firewall
     * @param firewall 
     * @return Firewall
     */
    Firewall create(Firewall firewall);
    
    /**
     * Update a Firewall
     * @param firewallId the Firewall identifier
     * @param firewallUpdate FirewallUpdate
     * @return Firewall
     */
    Firewall update(String firewallId, FirewallUpdate firewallUpdate);
}
