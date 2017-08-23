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
package com.huawei.openstack4j.api.storage;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSet;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSetUsage;

/**
 * Quota-Set Extension API for Block Storage
 * 
 * @author Jeremy Unruh
 */
public interface BlockQuotaSetService extends RestService {

    /**
     * Gets default quotas for a tenant
     * 
     * @param tenantId the tenant identifier
     * @return the default quotas
     */
    BlockQuotaSet getDefaults(String tenantId);

    /**
     * Gets quotas for a tenant
     * 
     * @param tenantId the tenant identifier
     * @return the quotas for a tenant
     */
    BlockQuotaSet get(String tenantId);
    
    /**
     * Updates quotas for a tenant
     * 
     * @param tenantId the tenant identifier
     * @param quota the quota-set to update
     * @return the updated quotas
     */
    BlockQuotaSet updateForTenant(String tenantId, BlockQuotaSet quota);
    
    /**
     * Deletes quotas for a tenant so the quotas revert to default values
     * 
     * @param tenantId the tenant identifier
     * @return the action response
     */
    ActionResponse delete(String tenantId);

    /**
     * Gets details for quotas for a specified tenant
     * 
     * @param tenantId the tenant identifier
     * @return the quota usage details
     */
    BlockQuotaSetUsage usageForTenant(String tenantId);
    
    /**
     * Gets details for quotas for a specified tenant and user.
     * 
     * @param tenantId the tenant identifier
     * @param userId the user identifier
     * @return the quota usage details
     */
    BlockQuotaSetUsage usageForUser(String tenantId, String userId);
    
}
