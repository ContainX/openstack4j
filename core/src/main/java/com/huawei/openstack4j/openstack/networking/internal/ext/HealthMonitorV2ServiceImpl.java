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

import com.huawei.openstack4j.api.networking.ext.HealthMonitorV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2.HealthMonitorsV2;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * OpenStack (Neutron) lbaas v2 health monitor operations
 * @author ashleykasim
 */
public class HealthMonitorV2ServiceImpl extends BaseNetworkingServices implements HealthMonitorV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list(){
        return get(HealthMonitorsV2.class, uri("/lbaas/healthmonitors")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list(Map<String, String> filteringParams){
        Invocation<HealthMonitorsV2> req = get(HealthMonitorsV2.class, uri("/lbaas/healthmonitors"));
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
    public HealthMonitorV2 get(String healthMonitorId){
        checkNotNull(healthMonitorId);
        return get(NeutronHealthMonitorV2.class, uri("/lbaas/healthmonitors/%s", healthMonitorId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String healthMonitorId){
        checkNotNull(healthMonitorId);
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/lbaas/healthmonitors/%s", healthMonitorId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 create(HealthMonitorV2 healthMonitor){
        checkNotNull(healthMonitor);
        return post(NeutronHealthMonitorV2.class,uri("/lbaas/healthmonitors")).entity(healthMonitor).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 update(String healthMonitorId,
                                HealthMonitorV2Update healthMonitor) {
        checkNotNull(healthMonitorId);
        checkNotNull(healthMonitor);
        return put(NeutronHealthMonitorV2.class,uri("/lbaas/healthmonitors/%s",healthMonitorId)).entity(healthMonitor).execute();
    }
}
