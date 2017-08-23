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
package com.huawei.openstack4j.model.network.ext.builder;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.ext.Firewall;

/**
 * A Builder to Create Firewall of FwaaS
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallBuilder extends Builder<FirewallBuilder, Firewall> {

	/**
	 * @param tenantId : Owner of the Firewall. Only an administrative user can
	 *         specify a tenant ID other than its own.
	 * @return FirewallBuilder
	 */
	public FirewallBuilder tenantId(String tenantId);
	
	/**
	 * @param name : Human readable name for the firewall (255 characters limit). Does not have to be unique.
	 * @return FirewallBuilder
	 */
	public FirewallBuilder name(String name);

	/**
	 * @param description : Human readable description for the firewall (1024 characters limit).
	 * @return FirewallBuilder
	 */
	public FirewallBuilder description(String description);
	
	/**
	 * @param adminstateup :  The administrative state of the firewall,
	 *         which is up (true) or down (false).
	 * @return FirewallBuilder
	 */
	public FirewallBuilder adminStateUp(Boolean adminStateUp);

	/**
	 * shared :  When set to True makes this firewall rule visible to tenants other 
	 * 					 than its owner, and can be used in firewall policies not owned by its tenant.
	 * @return FirewallBuilder
	 */
	public FirewallBuilder shared(Boolean shared);
	
	/**
	 * @param policyid : The firewall policy uuid that this firewall is associated with. 
	 * 				This firewall will implement the rules contained in the firewall policy represented by this uuid.
	 * @return FirewallBuilder
	 */
	public FirewallBuilder policy(String policyId);

	/**
	 * @param routerIds : A list of UUIDs for routers that are associated with the firewall.
	 * @return FirewallBuilder
	 */
	public FirewallBuilder routerIds(List<String> routerIds);
}
