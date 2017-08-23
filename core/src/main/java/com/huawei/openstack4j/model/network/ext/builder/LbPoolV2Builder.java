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
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.Protocol;
import com.huawei.openstack4j.model.network.ext.SessionPersistence;

/**
 * A Builder to create a lbpoolV2
 *
 * @author emjburns
 *
 */
public interface LbPoolV2Builder extends Buildable.Builder<LbPoolV2Builder, LbPoolV2> {
    /**
     * @param tenantId
     *            Owner of the pool. Only an administrative user can specify a
     *            tenant ID other than its own.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder tenantId(String tenantId);

    /**
     * Optional
     *
     * @param name
     *            Pool name. Does not have to be unique.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Description for the pool.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder description(String description);

    /**
     * @param protocol
     *            The protocol of the VIP address. A valid value is TCP, HTTP,
     *            or HTTPS.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder protocol(Protocol protocol);

    /**
     * @param lbMethod
     *            The load-balancer algorithm, which is round-robin,
     *            least-connections, and so on. This value, which must be
     *            supported, is dependent on the load-balancer provider. Round
     *            robin must be supported.
     *            Must be one of ROUND_ROBIN, LEAST_CONNECTIONS, or SOURCE_IP.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder lbMethod(LbMethod lbMethod);

    /**
     * Optional
     *
     * @param sessionPersistence
     *            Default value empty dictionary
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder sessionPersistence(SessionPersistence sessionPersistence);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the lb pool, which is up (true) or
     *            down (false). Default value true.
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder adminStateUp(boolean adminStateUp);

    /**
     * The listener in which this pool will become the default pool.
     * There can only be on default pool for a listener.
     * @param listenerId
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder listenerId(String listenerId);
}
