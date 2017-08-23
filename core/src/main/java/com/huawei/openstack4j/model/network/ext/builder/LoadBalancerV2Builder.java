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
package com.huawei.openstack4j.model.network.ext.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;

public interface LoadBalancerV2Builder extends Buildable.Builder<LoadBalancerV2Builder, LoadBalancerV2> {
    /**
     * @param tenantId
     *            Owner of the loadbalancer. Only an admin user can specify a tenant ID
     *            other than its own.
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder tenantId(String tenantId);

    /**
     * Optional
     *
     * @param name
     *            Human-readable name for the loadbalancer. Does not have to be unique.
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Human-readable description for the loadbalancer.
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder description(String description);

    /**
     * Optional
     *
     * @param vipSubnetId
     *            The network on which to allocate the load balancer's vip address.
     *            A tenant can only create load balancer vips on networks authorized by policy (e.g. her own networks or shared/provider networks).
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder subnetId(String vipSubnetId);

    /**
     * Optional
     *
     * @param vipAddress
     *            The IP address of the VIP.
     *            If provided, the system will attempt to assign the load balancer's vip address to this.
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder address(String vipAddress);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN).
     * @return LoadBalancerV2Builder
     */
    LoadBalancerV2Builder adminStateUp(boolean adminStateUp);

    /**
     * Optional
     *
     * @param provider
     *             Set the provider the load balancer will be provisioned with
     */
    LoadBalancerV2Builder provider(String provider);
}
