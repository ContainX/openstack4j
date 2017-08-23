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

import com.huawei.openstack4j.api.networking.ext.HealthMonitorService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.HealthMonitor;
import com.huawei.openstack4j.model.network.ext.HealthMonitorUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitor;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitor.HealthMonitors;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 *  OpenStack (Neutron) Lbaas healthmonitor based Operations
 * @author liujunpeng
 *
 */
public class HealthMonitorServiceImpl extends BaseNetworkingServices implements
HealthMonitorService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends HealthMonitor> list() {
		return get(HealthMonitors.class, uri("/lb/health_monitors")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends HealthMonitor> list(Map<String, String> filteringParams) {
		Invocation<HealthMonitors> req = get(HealthMonitors.class, uri("/lb/health_monitors"));
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
	public HealthMonitor get(String healthMonitorId) {
		checkNotNull(healthMonitorId);
		return get(NeutronHealthMonitor.class,uri("/lb/health_monitors/%s",healthMonitorId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String healthMonitorId) {
		checkNotNull(healthMonitorId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/lb/health_monitors/%s",healthMonitorId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HealthMonitor create(HealthMonitor healthMonitor) {
		checkNotNull(healthMonitor);
		return post(NeutronHealthMonitor.class,uri("/lb/health_monitors")).entity(healthMonitor).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HealthMonitor update(String healthMonitorId,
			HealthMonitorUpdate healthMonitor) {
		checkNotNull(healthMonitorId);
		checkNotNull(healthMonitor);
		return put(NeutronHealthMonitor.class,uri("/lb/health_monitors/%s",healthMonitorId)).entity(healthMonitor).execute();
	}

}
