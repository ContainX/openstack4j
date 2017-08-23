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

import java.util.List;

import com.huawei.openstack4j.api.networking.PortService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.Port;
import com.huawei.openstack4j.model.network.options.PortListOptions;
import com.huawei.openstack4j.openstack.networking.domain.NeutronPort;
import com.huawei.openstack4j.openstack.networking.domain.NeutronPortCreate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronPort.Ports;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * OpenStack (Neutron) Port based Operations Implementation
 * 
 * @author Jeremy Unruh
 */
public class PortServiceImpl extends BaseNetworkingServices implements PortService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Port> list() {
        return get(Ports.class, uri("/ports")).execute().getList();
    }

    @Override
    public List<? extends Port> list(PortListOptions options) {
        if (options == null)
            return list();
        
        return get(Ports.class, uri("/ports")).params(options.getOptions()).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Port get(String portId) {
        checkNotNull(portId);
        return get(NeutronPort.class, uri("/ports/%s", portId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String portId) {
        checkNotNull(portId);
        return deleteWithResponse(uri("/ports/%s", portId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Port create(Port port) {
        checkNotNull(port);
        checkNotNull(port.getNetworkId(), "NetworkId is a required field");
        return post(NeutronPort.class, uri("/ports")).entity(NeutronPortCreate.fromPort(port)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Port> create(List<? extends Port> ports) {
        checkNotNull(ports);
        for (Port port : ports) {
            checkNotNull(port.getNetworkId(), "NetworkId is a required field");
        }
        return post(Ports.class, uri("/ports")).entity(NeutronPortCreate.NeutronPortsCreate.fromPorts(ports))
                .execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Port update(Port port) {
        checkNotNull(port);
        checkNotNull(port.getId());
        Port p = port.toBuilder().networkId(null).state(null).tenantId(null).macAddress(null)
        		.vifType(null).vifDetails(null)
        		.build();
        return put(NeutronPort.class, uri("/ports/%s", getAndClearIdentifier(p))).entity(p).execute();
    }

    private String getAndClearIdentifier(Port port) {
        String portId = port.getId();
        port.setId(null);
        return portId;
    }
}
