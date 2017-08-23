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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.gbp.builder.PolicyTargetBuilder;

/**
 * Policy Target Model Entity
 * 
 * @author vinod borole
 */
public interface PolicyTarget extends Resource, Buildable<PolicyTargetBuilder> {

    /**
     * Gets the Port Id
     *
     * @return the Port Id
     */
    String getPortId();

    /**
     * Gets the Policy Target Group Id
     *
     * @return the Policy Target Group Id
     */
    String getPolicyTargetGroupId();

    /**
     * Gets the cluster Id
     *
     * @return the cluster Id
     */
    String getClusterId();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

}
 