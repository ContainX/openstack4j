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
package com.huawei.openstack4j.openstack.manila.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.huawei.openstack4j.api.manila.QuotaSetService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.QuotaSet;
import com.huawei.openstack4j.model.manila.QuotaSetUpdateOptions;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaQuotaSet;
import com.huawei.openstack4j.openstack.manila.domain.ManilaQuotaSetUpdate;

public class QuotaSetServiceImpl extends BaseShareServices implements QuotaSetService {
    @Override
    public QuotaSet get(String tenantId) {
        return get(tenantId, null);
    }

    @Override
    public QuotaSet get(String tenantId, String userId) {
        checkNotNull(tenantId);

        return get(ManilaQuotaSet.class, uri("/os-quota-sets/%s", tenantId))
                .param(userId != null, "user_id", userId)
                .execute();
    }

    @Override
    public QuotaSet update(String tenantId, QuotaSetUpdateOptions options) {
        return update(tenantId, null, options);
    }

    @Override
    public QuotaSet update(String tenantId, String userId, QuotaSetUpdateOptions options) {
        checkNotNull(tenantId);
        checkNotNull(options);

        return put(ManilaQuotaSet.class, uri("/os-quota-sets/%s", tenantId))
                .param(userId != null, "user_id", userId)
                .entity(ManilaQuotaSetUpdate.fromOptions(options))
                .execute();
    }

    @Override
    public ActionResponse delete(String tenantId) {
        return delete(tenantId, null);
    }

    @Override
    public ActionResponse delete(String tenantId, String userId) {
        checkNotNull(tenantId);

        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/os-quota-sets/%s", tenantId))
                        .param(userId != null, "user_id", userId)
                        .executeWithResponse());
    }

    @Override
    public QuotaSet getDefault(String tenantId) {
        checkNotNull(tenantId);

        return get(ManilaQuotaSet.class, uri("/os-quota-sets/%s/defaults", tenantId)).execute();
    }
}
