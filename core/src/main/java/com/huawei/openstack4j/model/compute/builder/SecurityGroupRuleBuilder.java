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
package com.huawei.openstack4j.model.compute.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.compute.IPProtocol;
import com.huawei.openstack4j.model.compute.SecGroupExtension.Rule;

/**
 * Creates a Security Group Extension {@link Rule} entity
 * 
 * @author Jeremy Unruh
 */
public interface SecurityGroupRuleBuilder extends Builder<SecurityGroupRuleBuilder, Rule> {

	/**
	 * IP protocol, one of TCP, UDP or ICMP
	 *
	 * @param protocol the protocol
	 * @return the security group rule builder
	 */
	SecurityGroupRuleBuilder protocol(IPProtocol protocol);
	
	/**
	 * Port range which consists of a starting and destination port
	 *
	 * @param fromPort the source port
	 * @param toPort the destination port
	 * @return the security group rule builder
	 */
	SecurityGroupRuleBuilder range(int fromPort, int toPort);
	
	/**
	 * Destination IP address(es) in CIDR notation
	 *
	 * @param cidr the CIDR notation
	 * @return the security group rule builder
	 */
	SecurityGroupRuleBuilder cidr(String cidr);
	
	/**
	 * Security group id
	 *
	 * @param groupId the group id
	 * @return the security group rule builder
	 */
	SecurityGroupRuleBuilder groupId(String groupId);
	
	/**
	 * Parent security group id
	 *
	 * @param parentGroupId the parent group id
	 * @return the security group rule builder
	 */
	SecurityGroupRuleBuilder parentGroupId(String parentGroupId);
	
}
