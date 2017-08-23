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

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.IPVersionType;
import com.huawei.openstack4j.model.network.ext.FirewallRuleUpdate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;

/**
 * A Builder to Update Firewall Rule of FwaaS
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallRuleUpdateBuilder extends Builder<FirewallRuleUpdateBuilder, FirewallRuleUpdate> {

	/**
	 * @param name : Human readable name for the firewall rule (255 characters limit). Does not have to be unique.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder name(String name);

	/**
	 * @param tenantId : Owner of the Firewall. Only an administrative user can
	 *         specify a tenant ID other than its own.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder tenantId(String tenantId);

	/**
	 * @param description : Human readable description for the firewall rule (1024 characters limit).
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder description(String description);
	
	/**
	 * @param shared : When set to True makes this firewall rule visible to tenants other than its owner, 
	 * 					and can be used in firewall policies not owned by its tenant.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder shared(Boolean shared);
	
	/**
	 * @see NeutronFirewallRule.IPProtocol
	 * @param protocol : IP Protocol : Possible values are ICMP/TCP/UDP/NONE(ANY).
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder protocol(NeutronFirewallRule.IPProtocol protocol);
	
	/**
	 * @see IPVersionType
	 * @param ipVersion : IP Protocol Version : Possible values are 4/6.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder ipVersion(IPVersionType ipVersion);
	
	/**
	 * @param sourceIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder sourceIpAddress(String sourceIpAddress);
	
	/**
	 * @param destinationIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder destinationIpAddress(String destinationIpAddress);
	
	/**
	 * @param sourcePort : Valid port number (integer or FirewallRuleUpdateBuilder), or port range in the format of a ':' separated range). 
	 * 						In the case of port range, both ends of the range are included.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder sourcePort(String sourcePort);
	
	/**
	 * @param destinationPort : Valid port number (integer or FirewallRuleUpdateBuilder), or port range in the format of a ':' separated range). 
	 * 							 In the case of port range, both ends of the range are included.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder destinationPort(String destinationPort);
	
	/**
	 * @param action : Action to be performed on the traffic matching the rule (allow, deny).
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder action(NeutronFirewallRule.FirewallRuleAction action);

	/**
	 * @param enabled : When set to False will disable this rule in the firewall policy. Facilitates selectively turning off 
	 * 		rules without having to disassociate the rule from the firewall policy.
	 * @return FirewallRuleUpdateBuilder
	 */
	public FirewallRuleUpdateBuilder enabled(Boolean enabled);
}
