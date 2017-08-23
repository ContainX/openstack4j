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
package com.huawei.openstack4j.api.networking;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.network.SecurityGroupRule;


/**
 * Provides Neutron-based Security Group Rule services.
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupRuleService extends RestService {
  
  /**
   * List security group rules accessible by current Tenant.
   *
   * @return the list<? extends security group rules>
   */
  List<? extends SecurityGroupRule> list();
  
  /**
   * Gets the Security Group rule by id
   *
   * @param id the id
   * @return the security group rule
   */
  SecurityGroupRule get(String id);
  
  /**
   * Delete security group rule by id.
   *
   * @param id the id
   */
  void delete(String id);
  
  /**
   * Creates a security group rule.
   *
   * @param rule the rule
   * @return the security group rule
   */
  SecurityGroupRule create(SecurityGroupRule rule);

  /**
   * List security group rules accessible by current Tenant.
   * @param filteringParams map (name, value) of filtering parameters
   *
   * @return the list<? extends security group rules>
   */
  List<? extends SecurityGroupRule> list(Map<String, String> filteringParams);
  
}
