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
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.gbp.builder.L3PolicyBuilder;

/**
 * L3 Policy Model Entity
 * 
 * @author vinod borole
 */
public interface L3Policy extends Resource, Buildable<L3PolicyBuilder> {

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets the external segment
     *
     * @return the external segment
     */
    Map<String, List<String>> getExternalSegments();

    /**
     * Gets the Ip Pool
     *
     * @return the Ip Pool
     */
    String getIpPool();

    /**
     * Gets the Ip version
     *
     * @return the Ip version
     */
    int getIpVersion();

    /**
     * Gets the list of L2 Policies
     *
     * @return the list of L2 Policies
     */
    List<String> getL2Policies();

    /**
     * Gets the list of routers
     *
     * @return the list of routers
     */
    List<String> getRouters();

    /**
     * Is L3 Policy shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the subnet prefix length
     *
     * @return the subnet prefix length
     */
    String getSubnetPrefixLength();

}
