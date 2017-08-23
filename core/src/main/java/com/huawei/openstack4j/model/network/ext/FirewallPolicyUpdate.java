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
import com.huawei.openstack4j.model.network.ext.builder.FirewallPolicyUpdateBuilder;

/**
 * <p>A Builder to Update Firewall Policy of FwaaS</p>
 * 
 * <p>Represents an ordered collection of firewall rules. A firewall policy can be shared across tenants. 
 * 		Thus it can also be made part of an audit workflow wherein the firewall_policy can be audited by the 
 * 		relevant entity that is authorized (and can be different from the tenants which create or use the firewall policy).
 * </p>
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallPolicyUpdate extends ModelEntity, Buildable<FirewallPolicyUpdateBuilder> {
	
	/**
	 * @return name : Human readable name for the FirewallPolicyUpdate (255 characters limit). Does not have to be unique.
	 */
	public String getName();

	/**
	 * @return description : Human readable description for the FirewallPolicyUpdate (1024 characters limit).
	 */
	public String getDescription();

	/**
	 * @return shared :  When set to True makes this FirewallPolicyUpdate visible to tenants other 
	 * 					 than its owner, and can be used in FirewallPolicyUpdate not owned by its tenant.
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
}
