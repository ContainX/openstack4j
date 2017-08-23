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

import com.huawei.openstack4j.api.networking.NetworkService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.NetworkUpdate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetwork;
import com.huawei.openstack4j.openstack.networking.domain.NeutronNetwork.Networks;

/**
 * OpenStack (Neutron) Network based Operations
 * 
 * @author Jeremy Unruh
 */
public class NetworkServiceImpl extends BaseNetworkingServices implements NetworkService {
	
	 private Invocation<Networks> buildInvocation(Map<String, String> filteringParams) {
	        Invocation<Networks> invocation = get(Networks.class, "/networks");
	        if (filteringParams == null) {
	            return invocation;
	        } else {
	            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
	            	invocation = invocation.param(entry.getKey(), entry.getValue());
	            }
	        }
	        return invocation;
	    }
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Network> list(Map<String, String> filteringParams){
		  Invocation<Networks> invocation = buildInvocation(filteringParams);
	        return invocation.execute().getList();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Network> list() {
		return get(Networks.class, uri("/networks")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String networkId) {
		checkNotNull(networkId);
		return deleteWithResponse(uri("/networks/%s", networkId)).execute();
	}

	@Override
	public Network get(String networkId) {
		checkNotNull(networkId);
		return get(NeutronNetwork.class, uri("/networks/%s", networkId)).execute();
	}

	@Override
	public Network create(Network network) {
		checkNotNull(network);
		return post(NeutronNetwork.class, uri("/networks")).entity(network).execute();
	}

    @Override
    public Network update(String networkId, NetworkUpdate network) {
        checkNotNull(networkId);
        checkNotNull(network, "network");
        return put(NeutronNetwork.class, uri("/networks/%s", networkId)).entity(network).execute();
    }

}
