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
package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Neutron Firewall (FwaaS) : Firewall Policy Entity.
 * 
 * <p>This is the child class of {@link AbstractNeutronFirewallPolicy} which is used by `rule_insert/rule_remove` calls 
 * - which doesn't require JsonRootName <code>firewall_policy</code>.</p> 
 * 
 * @see AbstractNeutronFirewallPolicy
 * 
 * @author Vishvesh Deshmukh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronFirewallPolicyRule extends AbstractNeutronFirewallPolicy {
	private static final long serialVersionUID = 1L;
}