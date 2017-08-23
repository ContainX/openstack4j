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

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.networking.ext.LbPoolService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.HealthMonitor;
import com.huawei.openstack4j.model.network.ext.HealthMonitorAssociate;
import com.huawei.openstack4j.model.network.ext.LbPool;
import com.huawei.openstack4j.model.network.ext.LbPoolStats;
import com.huawei.openstack4j.model.network.ext.LbPoolUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitor;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPool;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolStats;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPool.LbPools;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 *  OpenStack (Neutron) Lbaas pool based Operations
 * @author liujunpeng
 *
 */
public class LbPoolServiceImpl extends BaseNetworkingServices implements
LbPoolService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LbPool> list() {
        return get(LbPools.class, uri("/lb/pools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LbPool> list(Map<String, String> filteringParams) {
        Invocation<LbPools> req = get(LbPools.class, uri("/lb/pools"));
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
    public LbPool get(String lbPoolId) {
        checkNotNull(lbPoolId);
        return get(NeutronLbPool.class, uri("/lb/pools/%s",lbPoolId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String lbPoolId) {
        checkNotNull(lbPoolId);
        return ToActionResponseFunction.INSTANCE.apply(delete(void.class,
                uri("/lb/pools/%s", lbPoolId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPool create(LbPool lbPool) {
        checkNotNull(lbPool);
        return post(NeutronLbPool.class,uri("/lb/pools")).entity(lbPool).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPool update(String lbPoolId, LbPoolUpdate lbPool) {
        checkNotNull(lbPoolId);
        checkNotNull(lbPool);
        return put(NeutronLbPool.class, uri("/lb/pools/%s", lbPoolId)).entity(
                lbPool).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolStats stats(String lbPoolId) {
        checkNotNull(lbPoolId);
        return get(NeutronLbPoolStats.class, uri("/lb/pools/%s/stats.json",lbPoolId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitor associateHealthMonitor(String lbPoolId, String healthMonitorId) {
        checkNotNull(lbPoolId);
        checkNotNull(healthMonitorId);
        return associateHealthMonitor(lbPoolId, Builders.lbPoolAssociateHealthMonitor().id(healthMonitorId).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitor associateHealthMonitor(String lbPoolId, HealthMonitorAssociate associate) {
        checkNotNull(lbPoolId);
        checkNotNull(associate);
        return post(NeutronHealthMonitor.class,uri("/lb/pools/%s/health_monitors",lbPoolId)).entity(associate).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse disAssociateHealthMonitor(String lbPoolId, String healthMonitorId) {
        checkNotNull(lbPoolId);
        checkNotNull(healthMonitorId);
        return ToActionResponseFunction.INSTANCE.apply(delete(void.class,
                uri("/lb/pools/%s/health_monitors/%s", lbPoolId,healthMonitorId)).executeWithResponse());
    }
}
