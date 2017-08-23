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
package com.huawei.openstack4j.model.gbp;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.gbp.builder.PolicyRuleSetBuilder;

/**
 * Policy rule set Model Entity
 * 
 * @author vinod borole
 */
public interface PolicyRuleSet extends Resource, Buildable<PolicyRuleSetBuilder> {

    /**
     * Gets the list of Policy rules
     *
     * @return the list of Policy rules
     */
    List<String> getPolicyRules();

    /**
     * Gets the list of child policy rule sets
     *
     * @return the list of child policy rule sets
     */
    List<String> getChildPolicyRuleSets();

    /**
     * Gets the parent Id
     *
     * @return the parent Id
     */
    String getParentId();

    /**
     * Is Policy rule set shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

}
  