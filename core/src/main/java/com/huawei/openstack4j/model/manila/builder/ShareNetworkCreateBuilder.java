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
package com.huawei.openstack4j.model.manila.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.manila.ShareNetworkCreate;

/**
 * Builds a {@link com.huawei.openstack4j.model.manila.ShareNetworkCreate}.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareNetworkCreateBuilder extends Buildable.Builder<ShareNetworkCreateBuilder, ShareNetworkCreate> {
    /**
     * Set the neutron network and subnet.
     *
     * @param neutronNetId the neutron network ID
     * @param neutronSubnetId the neutron subnet ID
     * @return ShareNetworkCreateBuilder
     */
    ShareNetworkCreateBuilder neutronNet(String neutronNetId, String neutronSubnetId);

    /**
     * Set the nova network.
     *
     * @param novaNetId the nova network ID
     * @return ShareNetworkCreateBuilder
     */
    ShareNetworkCreateBuilder novaNet(String novaNetId);

    /**
     * Set the share network name.
     *
     * @param name the share network name
     * @return ShareNetworkCreateBuilder
     */
    ShareNetworkCreateBuilder name(String name);

    /**
     * Set the share network description.
     *
     * @param description the share network description
     * @return ShareNetworkCreateBuilder
     */
    ShareNetworkCreateBuilder description(String description);
}
