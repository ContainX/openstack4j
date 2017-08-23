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
import com.huawei.openstack4j.model.gbp.builder.L2PolicyBuilder;

/**
 * L2 Policy Model Entity
 * 
 * @author vinod borole
 */
public interface L2Policy extends Resource, Buildable<L2PolicyBuilder> {

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets the network Id
     *
     * @return the network Id
     */
    String getNetworkId();

    /**
     * Gets the L3 Policy Id
     *
     * @return the L3 Policy Id
     */
    String getL3PolicyId();

    /**
     * Is L2 Policy shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the list of policy Target groups
     *
     * @return the policy target group list
     */
    List<String> getPolicyTargetGroups();

}
