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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.NetQuota;
import com.huawei.openstack4j.model.network.builder.NetQuotaBuilder;

/**
 * Networking (Neutron) Quota Extension API
 * 
 * @author Jeremy Unruh
 */
public interface NetQuotaService extends RestService {
    
    /**
     * Lists quotas for tenants who have non-default quota values, and lists, updates, and resets quotas for a tenan
     * 
     * @return the list of quotas
     */
    List<? extends NetQuota> get();
    
    /**
     * Fetches the network quotas for the specified tenant
     * 
     * @param tenantId the tenant identifier
     * @return the tenants quota
     */
    NetQuota get(String tenantId);
    
    /**
     * Updates the network quotas for the current tenant
     * 
     * @param netQuota the net quota to update
     * @return the updated network quota
     * @see NetQuotaBuilder
     */
    NetQuota update(NetQuota netQuota);
    
    /**
     * Updates the network quotas for the specified tenant
     * 
     * @param tenantId the tenant identifier
     * @param netQuota the net quota to update
     * @return the updated network quota
     * @see NetQuotaBuilder
     */
    NetQuota updateForTenant(String tenantId, NetQuota netQuota);
    
    /**
     * Resets the current network quota for the current tenant back to defaults
     * @return the action response
     */
    ActionResponse reset();

    /**
     * Resets the current network quota for the current tenant back to defaults
     * 
     * @param netQuota the net quota to update
     * @return the action response
     */
    ActionResponse reset(String tenantId);
}
