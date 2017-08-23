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

import java.util.List;

import com.huawei.openstack4j.api.manila.ShareInstanceService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.ShareInstance;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareInstance;
import com.huawei.openstack4j.openstack.manila.domain.actions.ShareInstanceActions;

import static com.google.common.base.Preconditions.checkNotNull;

public class ShareInstanceServiceImpl extends BaseShareServices implements ShareInstanceService {
    @Override
    public List<? extends ShareInstance> list() {
        return get(ManilaShareInstance.ShareInstances.class, uri("/share_instances"))
                .execute()
                .getList();
    }

    @Override
    public ShareInstance get(String shareInstanceId) {
        checkNotNull(shareInstanceId);
        return get(ManilaShareInstance.class, uri("/share_instances/%s", shareInstanceId)).execute();
    }

    @Override
    public ActionResponse resetState(String shareInstanceId, ShareInstance.Status status) {
        checkNotNull(shareInstanceId);
        checkNotNull(status);

        return ToActionResponseFunction.INSTANCE.apply(
                post(Void.class, uri("/share_instances/%s/action", shareInstanceId))
                        .entity(ShareInstanceActions.resetState(status))
                        .executeWithResponse());
    }

    @Override
    public ActionResponse forceDelete(String shareInstanceId) {
        checkNotNull(shareInstanceId);

        return ToActionResponseFunction.INSTANCE.apply(
                post(Void.class, uri("/share_instances/%s/action", shareInstanceId))
                        .entity(ShareInstanceActions.forceDelete())
                        .executeWithResponse());
    }
}
