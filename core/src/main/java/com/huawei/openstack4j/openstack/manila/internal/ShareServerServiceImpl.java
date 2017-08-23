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

import com.huawei.openstack4j.api.manila.ShareServerService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.ShareServer;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareServer;

import static com.google.common.base.Preconditions.checkNotNull;

public class ShareServerServiceImpl extends BaseShareServices implements ShareServerService {
    @Override
    public List<? extends ShareServer> list() {
        return get(ManilaShareServer.ShareServers.class, uri("/share-servers"))
                .execute()
                .getList();
    }

    @Override
    public ShareServer get(String shareServerId) {
        checkNotNull(shareServerId);
        return get(ManilaShareServer.class, uri("/share-servers/%s", shareServerId)).execute();
    }

    @Override
    public ActionResponse delete(String shareServerId) {
        checkNotNull(shareServerId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/share-servers/%s", shareServerId)).executeWithResponse());
    }
}
