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

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.LoadBalancerV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Stats;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2StatusTree;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2Stats;
import com.huawei.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree.NeutronLoadBalancerV2StatusTree;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Openstack (Neutron) lbaas v2 load balancer operations
 * @author emjburns
 */
public class LoadBalancerV2ServiceImpl extends BaseNetworkingServices implements LoadBalancerV2Service {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LoadBalancerV2> list(){
        return get(NeutronLoadBalancerV2.LoadBalancersV2.class, uri("/lbaas/loadbalancers")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LoadBalancerV2> list(Map<String, String> filteringParams){
        Invocation<NeutronLoadBalancerV2.LoadBalancersV2> req = get(NeutronLoadBalancerV2.LoadBalancersV2.class, uri("/lbaas/loadbalancers"));
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
    public LoadBalancerV2 get(String loadbalancerId){
        checkNotNull(loadbalancerId);
        return get(NeutronLoadBalancerV2.class, uri("/lbaas/loadbalancers/%s",loadbalancerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2 create(LoadBalancerV2 loadbalancer){
        checkNotNull(loadbalancer);
        return post(NeutronLoadBalancerV2.class,uri("/lbaas/loadbalancers")).entity(loadbalancer).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2 update(String loadbalancerId, LoadBalancerV2Update loadbalancer){
        checkNotNull(loadbalancerId);
        checkNotNull(loadbalancer);
        return put(NeutronLoadBalancerV2.class, uri("/lbaas/loadbalancers/%s",loadbalancerId)).entity(loadbalancer).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String loadbalancerId){
        checkNotNull(loadbalancerId);
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/lbaas/loadbalancers/%s",loadbalancerId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2Stats stats(String loadbalancerId){
        checkNotNull(loadbalancerId);
        return get(NeutronLoadBalancerV2Stats.class, uri("/lbaas/loadbalancers/%s/stats",loadbalancerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2StatusTree statusTree(String loadbalancerId){
        checkNotNull(loadbalancerId);
        return get(NeutronLoadBalancerV2StatusTree.class, uri("/lbaas/loadbalancers/%s/statuses", loadbalancerId)).execute();
    }
}
