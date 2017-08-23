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
package com.huawei.openstack4j.model.network.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.SecurityGroupRule;

/**
 * Builder for a SecurityGroup model class
 * 
 * @author Nathan Anderson
 */
public interface NetSecurityGroupRuleBuilder extends Builder<NetSecurityGroupRuleBuilder, SecurityGroupRule> {

  /**
   * @see SecurityGroupRule#getId()
   */
  NetSecurityGroupRuleBuilder id(String id);
  
  /**
   * @see SecurityGroupRule#getTenantId()
   */
  NetSecurityGroupRuleBuilder tenantId(String tenantId);
  
  /**
   * @see SecurityGroupRule#getSecurityGroupId()
   */
  NetSecurityGroupRuleBuilder securityGroupId(String groupId);
  
  /**
   * @see SecurityGroupRule#getDirection()
   */
  NetSecurityGroupRuleBuilder direction(String direction);
  
  /**
   * @see SecurityGroupRule#getEthertype()
   */
  NetSecurityGroupRuleBuilder ethertype(String ethertype);
  
  /**
   * @see SecurityGroupRule#getPortRangeMax()
   */
  NetSecurityGroupRuleBuilder portRangeMax(int max);
  
  /**
   * @see SecurityGroupRule#getPortRangeMin()
   */
  NetSecurityGroupRuleBuilder portRangeMin(int min);
  
  /**
   * @see SecurityGroupRule#getProtocol()
   */
  NetSecurityGroupRuleBuilder protocol(String protocol);
  
  /**
   * @see SecurityGroupRule#getRemoteGroupId()
   */
  NetSecurityGroupRuleBuilder remoteGroupId(String remoteGroupId);
  
  /**
   * @see SecurityGroupRule#getRemoteIpPrefix()
   */
  NetSecurityGroupRuleBuilder remoteIpPrefix(String prefix);
  
}
