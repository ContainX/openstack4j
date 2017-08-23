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
package com.huawei.openstack4j.model.network.ext;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.ext.builder.FirewallBuilder;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewall;

/**
 * <p>Networking (Neutron) FwaaS Firewall Extension API.</p>
 * 
 * <p>Represents a logical firewall resource that a tenant can instantiate and manage. A firewall is associated with one firewall policy.</p>
 * 
 * @author Vishvesh Deshmukh
 */
public interface Firewall extends ModelEntity, Buildable<FirewallBuilder> {
	/**
	 * 
	 * @return id : Unique identifier for the firewall.
	 */
	public String getId();
	
	/**
	 * 
	 * @return name : Human readable name for the firewall (255 characters limit). Does not have to be unique.
	 */
	public String getName();

	/**
	 * 
	 * @return tenantId : Owner of the Firewall. Only an administrative user can
	 *         specify a tenant ID other than its own.
	 */
	public String getTenantId();

	/**
	 * 
	 * @return description : Human readable description for the firewall (1024 characters limit).
	 */
	public String getDescription();
	
	/**
	 * 
	 * @return adminstateup :  The administrative state of the firewall,
	 *         which is up (true) or down (false).
	 */
	public Boolean isAdminStateUp();

	/**
	 * 
	 * @return shared :  When set to True makes this firewall rule visible to tenants other 
	 * 					 than its owner, and can be used in firewall policies not owned by its tenant.
	 */
	public Boolean isShared();

	/**
	 * @see NeutronFirewall.FirewallStatus
	 * 
	 * @return status The status of the health monitor. Indicates whether the
	 *         health monitor is operational.
	 */
	public NeutronFirewall.FirewallStatus getStatus();
	
	/**
	 * 
	 * @return policyid : The firewall policy uuid that this firewall is associated with. 
	 * 				This firewall will implement the rules contained in the firewall policy represented by this uuid.
	 */
	public String getPolicy();

	/**
	 * 
	 * @return routerIds : A list of UUIDs for routers that are associated with the firewall.
	 */
	public List<String> getRouterIds();
}
