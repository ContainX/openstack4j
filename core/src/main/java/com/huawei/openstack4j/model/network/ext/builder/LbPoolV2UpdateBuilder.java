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
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.SessionPersistence;

/**
 * A builder to update an lbaas v2 pool
 * @author emjburns
 */
public interface LbPoolV2UpdateBuilder extends Buildable.Builder<LbPoolV2UpdateBuilder, LbPoolV2Update> {
    /**
     *
     * @param lbMethod
     *            The load-balancer algorithm, which is round-robin,
     *            least-connections, and so on. This value, which must be
     *            supported, is dependent on the load-balancer provider. Round
     *            robin must be supported.
     * @return LbPoolV2UpdateBuilder
     */
    public LbPoolV2UpdateBuilder  lbMethod(LbMethod lbMethod);

    /**
     *
     * @param name
     *            Pool name. Does not have to be unique.
     * @return LbPoolV2UpdateBuilder
     */
    public LbPoolV2UpdateBuilder  name(String name);

    /**
     *
     * @param description
     *            Description for the pool.
     * @return LbPoolV2UpdateBuilder
     */
    public LbPoolV2UpdateBuilder description(String description);
    /**
     *
     * @param adminStateUp
     *            The administrative state of the lb pool, which is up (true) or
     *            down (false).
     * @return LbPoolV2UpdateBuilder
     */
    public LbPoolV2UpdateBuilder  adminStateUp(boolean adminStateUp);

    /**
     * Optional
     *
     * @param sessionPersistence
     *            Session persistence parameters for the lbpool. Omit the
     *            session_persistence parameter to prevent session persistence.
     *            When no session persistence is used, the session_persistence
     *            parameter does not appear in the API response. To clear
     *            session persistence for the lbpool, set the session_persistence
     *            parameter to null in a lbpool update request.
     * @return LbPoolV2UpdateBuilder
     */
    LbPoolV2UpdateBuilder  sessionPersistence(SessionPersistence sessionPersistence);
}
