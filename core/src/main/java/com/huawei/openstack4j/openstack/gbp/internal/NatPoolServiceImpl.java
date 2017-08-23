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
package com.huawei.openstack4j.openstack.gbp.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.gbp.NatPoolService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.NatPool;
import com.huawei.openstack4j.openstack.gbp.domain.GbpNatPool;
import com.huawei.openstack4j.openstack.gbp.domain.GbpNatPool.NatPools;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Nat Pool API Implementation
 * 
 * @author vinod borole
 */
public class NatPoolServiceImpl extends BaseNetworkingServices implements NatPoolService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends NatPool> list() {
        return get(NatPools.class, uri("/grouppolicy/nat_pools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NatPool> list(Map<String, String> filteringParams) {
        Invocation<NatPools> natpoolInvocation = buildInvocation(filteringParams);
        return natpoolInvocation.execute().getList();
    }
    
    private Invocation<NatPools> buildInvocation(Map<String, String> filteringParams) {
        Invocation<NatPools> natpoolInvocation = get(NatPools.class, "/grouppolicy/nat_pools");
        if (filteringParams == null) { 
            return natpoolInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                natpoolInvocation = natpoolInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return natpoolInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NatPool get(String id) {
        checkNotNull(id);
        return get(GbpNatPool.class, uri("/grouppolicy/nat_pools/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/nat_pools/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NatPool create(NatPool natpool) {
        return post(GbpNatPool.class, uri("/grouppolicy/nat_pools")).entity(natpool).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NatPool update(String natpoolId, NatPool natpool) {
        checkNotNull(natpoolId);
        checkNotNull(natpool);
        return put(GbpNatPool.class, uri("/grouppolicy/nat_pools/%s", natpoolId)).entity(natpool).execute();
    }


}
