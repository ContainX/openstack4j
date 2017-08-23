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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.networking.ext.AgentService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.network.Agent;
import com.huawei.openstack4j.openstack.networking.domain.NeutronAgent;
import com.huawei.openstack4j.openstack.networking.domain.NeutronAgent.Agents;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) Agent Extension API
 *
 * @author Yin Zhang
 */
public class AgentServiceImpl extends BaseNetworkingServices implements AgentService {

    @Override
    public List<? extends Agent> list() {
        return get(Agents.class, uri("/agents")).execute().getList();
    }

    @Override
    public Agent getAgent(String agentId) {
        checkNotNull(agentId);
        return get(NeutronAgent.class, uri("/agents/%s", agentId)).execute();
    }

    @Override
    public Agent setAdminStateUp(String agentId, boolean state) {
        checkNotNull(agentId);
        String json = String.format("{\"%s\": { \"%s\": \"%b\"}}", "agent", "admin_state_up", state);
        return put(NeutronAgent.class, uri("/agents/%s", agentId)).json(json).execute(
                ExecutionOptions.<NeutronAgent> create(PropagateOnStatus.on(404)));
    }
}
