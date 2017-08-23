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
import com.huawei.openstack4j.model.network.ext.builder.FirewallPolicyBuilder;

/**
 * <p>Networking (Neutron) FwaaS Firewall Policy Extension API</p>
 * 
 * <p>Represents an ordered collection of firewall rules. A firewall policy can be shared across tenants. 
 * 		Thus it can also be made part of an audit workflow wherein the firewall_policy can be audited by the 
 * 		relevant entity that is authorized (and can be different from the tenants which create or use the firewall policy).
 * </p>
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallPolicy extends ModelEntity, Buildable<FirewallPolicyBuilder> {
	
	/**
	 * @return id : Unique identifier for the firewall policy.
	 */
	public String getId();
	
	/**
	 * @return name : Human readable name for the FirewallPolicy (255 characters limit). Does not have to be unique.
	 */
	public String getName();

	/**
	 * @return tenantId : Owner of the Firewall Policy. Only an administrative user can
	 *         specify a tenant ID other than its own.
	 */
	public String getTenantId();

	/**
	 * @return description : Human readable description for the FirewallPolicy (1024 characters limit).
	 */
	public String getDescription();

	/**
	 * @return shared :  When set to True makes this FirewallPolicy visible to tenants other 
	 * 					 than its owner, and can be used in FirewallPolicy not owned by its tenant.
	 */
	public Boolean isShared();
	
	/**
	 * @return audited : When set to True by the policy owner indicates that the firewall policy has been audited. 
			 * This attribute is meant to aid in the firewall policy audit workflows. 
			 * Each time the firewall policy or the associated firewall rules are changed, 
			 * this attribute will be set to False and will have to be explicitly set 
			 * to True through an update operation.
	 */
	public Boolean isAudited();
	
	/**
	 * @return firewallRules(UUID)List : This is an ordered list of firewall rule uuids. 
	 	* The firewall applies the rules in the order in which they appear in this list.
	 */
	public List<String> getFirewallRuleIds();
	
	/**
	 * @return firewallList(UUID)List : This is a list of Firewalls associated with Firewall Policy.
	 * 		This is returned when a firewall rule is added or removed from a firewall policy.
	 */
	public List<String> getFirewallList();

	/**
	 * @see FirewallRule
	 * @return neutronFirewallRulesList : This is an ordered list of firewall rules (by uuid). 
	 	* The firewall applies the rules in the order in which they appear in this list.
	 */
	public List<? extends FirewallRule> getNeutronFirewallRules();
}
