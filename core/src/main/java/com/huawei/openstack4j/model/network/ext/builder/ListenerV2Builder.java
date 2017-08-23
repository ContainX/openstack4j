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

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.network.ext.ListenerProtocol;
import com.huawei.openstack4j.model.network.ext.ListenerV2;

public interface ListenerV2Builder extends Buildable.Builder<ListenerV2Builder, ListenerV2> {
    /**
     * @param tenantId
     *            Owner of the listener. Only an administrative user can specify a
     *            tenant ID other than its own.
     * @return ListenerV2Builder
     */
    ListenerV2Builder tenantId(String tenantId);

    /**
     * @param protocol
     *            The protocol of the VIP address. A valid value is TCP, HTTP,
     *            or HTTPS.
     * @return ListenerV2Builder
     */
    ListenerV2Builder protocol(ListenerProtocol protocol);

    /**
     * The port in which the frontend will be listening. Must be an integer in the range of 1-65535
     * @param protocolPort
     * @return ListenerV2Builder
     */
    ListenerV2Builder protocolPort(Integer protocolPort);

    /**
     * The load balancer this listener will be provisioned on. A tenant can only create listeners on
     * load balancers authorized by policy (e.g. her own load balancers).
     * @param loadbalancerId
     * @return ListenerV2Builder
     */
    ListenerV2Builder loadBalancerId(String loadbalancerId);

    /**
     * Optional
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN). Default is true
     * @return ListenerV2Builder
     */
    ListenerV2Builder adminStateUp(boolean adminStateUp);

    /**
     *  Optional
     *
     * @param name
     *            Pool name. Does not have to be unique.
     * @return ListenerV2Builder
     */
    ListenerV2Builder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Description for the pool.
     * @return ListenerV2Builder
     */
    ListenerV2Builder description(String description);

    /**
     * Optional
     *
     * The default value for this attribute will be -1, indicating an infinite limit.
     * @param connectionLimit
     * @return ListenerV2Builder
     */
    ListenerV2Builder connectionLimit(Integer connectionLimit);

    /**
     * Optional
     *
     * Barbican container with tls policy
     * @param tlsContainerRef
     * @return ListenerV2Builder
     */
    ListenerV2Builder defaultTlsContainerRef(String tlsContainerRef);

    /**
     * Optional
     *
     * Barbican container(s) with sni certificates
     * @param sniContainerRefs
     * @return ListenerV2Builder
     */
    ListenerV2Builder sniContainerRefs(List<String> sniContainerRefs);
}
