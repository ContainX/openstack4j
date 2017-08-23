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
package com.huawei.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.huawei.openstack4j.api.storage.BlockQuotaSetService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSet;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSetUsage;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSetUsage;

/**
 * Quota-Set Extension API for Block Storage
 * 
 * @author Jeremy Unruh
 */
public class BlockQuotaSetServiceImpl extends BaseBlockStorageServices implements BlockQuotaSetService {

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockQuotaSet getDefaults(String tenantId) {
        checkNotNull(tenantId, "Tenant cannot be null");
        return get(CinderBlockQuotaSet.class, uri("/os-quota-sets/%s/defaults", tenantId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockQuotaSet get(String tenantId) {
        checkNotNull(tenantId, "Tenant cannot be null");
        return get(CinderBlockQuotaSet.class, uri("/os-quota-sets/%s", tenantId)).param("usage", false).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockQuotaSet updateForTenant(String tenantId, BlockQuotaSet quota) {
        checkNotNull(tenantId, "Tenant cannot be null");
        checkNotNull(quota, "Quota cannot be null");
        return put(CinderBlockQuotaSet.class, uri("/os-quota-sets/%s", tenantId)).entity(quota).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String tenantId) {
        checkNotNull(tenantId, "Tenant cannot be null");
        return delete(ActionResponse.class, uri("/os-quota-sets/%s", tenantId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockQuotaSetUsage usageForTenant(String tenantId) {
        checkNotNull(tenantId, "Tenant cannot be null");
        return get(CinderBlockQuotaSetUsage.class, uri("/os-quota-sets/%s", tenantId)).param("usage", true).execute();
    }

    @Override
    public BlockQuotaSetUsage usageForUser(String tenantId, String userId) {
        checkNotNull(tenantId, "Tenant cannot be null");
        checkNotNull(userId, "User cannot be null");
        return get(CinderBlockQuotaSetUsage.class, uri("/os-quota-sets/%s", tenantId))
                   .param("user_id", userId)
                   .param("usage", true)
                   .execute();
    }

}
