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
package com.huawei.openstack4j.model.network;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.builder.NetworkUpdateBuilder;

/**
 * An entity used to update a network
 * 
 * @author Jeremy Unruh
 */
public interface NetworkUpdate extends ModelEntity, Buildable<NetworkUpdateBuilder> {

    /**
     * The name of the network
     * 
     * @return the network name
     */
    String getName();
    
    /**
     * The administrative state of the network, which is up (true) or down (false).
     * 
     * @return the admin state up state
     */
    boolean isAdminStateUp();
    
    /**
     * Admin-only. Indicates whether this network is shared across all tenants.
     * 
     * @return true if this network is shared
     */
    boolean isShared();
    
}
