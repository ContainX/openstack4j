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
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.FirewallService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.Firewall;
import com.huawei.openstack4j.model.network.ext.FirewallUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewall;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronFirewall.Firewalls;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) FwaaS Firewall Extension API
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallServiceImpl extends BaseNetworkingServices implements FirewallService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Firewall> list() {
		return get(Firewalls.class, uri("/fw/firewalls")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Firewall> list(Map<String, String> filteringParams) {
		Invocation<Firewalls> req = get(Firewalls.class, uri("/fw/firewalls"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Firewall get(String firewallId) {
		checkNotNull(firewallId);
		return get(NeutronFirewall.class, uri("/fw/firewalls/%s", firewallId)).execute();	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String firewallId) {
		checkNotNull(firewallId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri("/fw/firewalls/%s", firewallId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Firewall create(Firewall firewall) {
		return post(NeutronFirewall.class, uri("/fw/firewalls")).entity(firewall)
				.execute(ExecutionOptions.<NeutronFirewall>create(PropagateOnStatus.on(404)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Firewall update(String firewallId, FirewallUpdate firewallUpdate) {
		checkNotNull(firewallId);
        checkNotNull(firewallUpdate);
        return put(NeutronFirewall.class, uri("/fw/firewalls/%s", firewallId)).entity(firewallUpdate).execute();
	}

}
