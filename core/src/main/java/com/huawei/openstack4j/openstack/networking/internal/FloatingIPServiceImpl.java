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
package com.huawei.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.NetFloatingIPService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.NetFloatingIP;
import com.huawei.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import com.huawei.openstack4j.openstack.networking.domain.NeutronFloatingIP.FloatingIPs;

/**
 * FloatingIPService implementation that provides Neutron Floating-IP based Service Operations.
 *
 * @author Nathan Anderson
 */
public class FloatingIPServiceImpl extends BaseNetworkingServices implements NetFloatingIPService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetFloatingIP> list() {
        return get(FloatingIPs.class, uri("/floatingips")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetFloatingIP> list(Map<String, String> filteringParams) {
        Invocation<FloatingIPs> fIPsInvocation = get(FloatingIPs.class, "/floatingips");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                fIPsInvocation = fIPsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return fIPsInvocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIP get(String id) {
        checkNotNull(id);
        return get(NeutronFloatingIP.class, uri("/floatingips/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/floatingips/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIP create(NetFloatingIP floatingIp) {
        checkNotNull(floatingIp);
        checkNotNull(floatingIp.getFloatingNetworkId());
        return post(NeutronFloatingIP.class, uri("/floatingips")).entity(floatingIp).execute(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIP associateToPort(String id, String portId) {
        checkNotNull(id);
        checkNotNull(portId);
        String inner = String.format("{ \"port_id\":\"%s\" }", portId);
        String json = String.format("{ \"%s\": %s }", "floatingip", inner);
        return put(NeutronFloatingIP.class, uri("/floatingips/%s",id)).json(json)
                .execute(ExecutionOptions.<NeutronFloatingIP>create(PropagateOnStatus.on(404)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIP disassociateFromPort(String id) {
        checkNotNull(id);
        String json = String.format("{ \"%s\": %s }", "floatingip", "{ \"port_id\":null }");
        return put(NeutronFloatingIP.class, uri("/floatingips/%s",id)).json(json)
                .execute(ExecutionOptions.<NeutronFloatingIP>create(PropagateOnStatus.on(404)));
    }
}
