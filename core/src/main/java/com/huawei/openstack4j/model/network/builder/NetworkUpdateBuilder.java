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
import com.huawei.openstack4j.model.network.NetworkUpdate;

/**
 * Builds a NetworkUpdate entity
 * 
 * @author Jeremy Unruh
 */
public interface NetworkUpdateBuilder extends Builder<NetworkUpdateBuilder, NetworkUpdate> {

    /**
     * Sets the network name
     * 
     * @param name the name of the network
     * @return the builder
     */
    NetworkUpdateBuilder name(String name);

    /**
     * The administrative state of the network, which is up (true) or down (false).
     * 
     * @param enabled if true indicated the admin state is up
     * @return the builder
     */
    NetworkUpdateBuilder adminStateUp(boolean enabled);
    
    /**
     * Admin-only. Indicates whether this network is shared across all tenants.
     * 
     * @param shared if true the network is shared
     * @return the builder
     */
    NetworkUpdateBuilder shared(boolean shared);
}
