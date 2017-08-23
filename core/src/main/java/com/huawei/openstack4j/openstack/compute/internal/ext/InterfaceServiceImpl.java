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
package com.huawei.openstack4j.openstack.compute.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.compute.ext.InterfaceService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.InterfaceAttachment;
import com.huawei.openstack4j.openstack.compute.domain.NovaInterfaceAttachment;
import com.huawei.openstack4j.openstack.compute.domain.NovaInterfaceAttachment.NovaInterfaceAttachments;
import com.huawei.openstack4j.openstack.compute.internal.BaseComputeServices;

/**
 * API to Create, list, get details for, and delete port interfaces on a Server Instance
 * 
 * @author Jeremy Unruh
 */
public class InterfaceServiceImpl extends BaseComputeServices  implements InterfaceService {

    @Override
    public InterfaceAttachment create(String serverId, String portId) {
        checkNotNull(serverId, "serverId");
        checkNotNull(portId, "portId");
        return post(NovaInterfaceAttachment.class, uri("/servers/%s/os-interface", serverId))
                 .entity(new NovaInterfaceAttachment(portId))
                 .execute();
    }

    @Override
    public List<? extends InterfaceAttachment> list(String serverId) {
        checkNotNull(serverId, "serverId");
        return get(NovaInterfaceAttachments.class , uri("/servers/%s/os-interface", serverId))
                  .execute().getList();
    }

    @Override
    public InterfaceAttachment get(String serverId, String attachmentId) {
        checkNotNull(serverId, "serverId");
        checkNotNull(attachmentId, "attachmentId");
        return get(NovaInterfaceAttachment.class, uri("/servers/%s/os-interface/%s", serverId, attachmentId)).execute();
    }

    @Override
    public ActionResponse detach(String serverId, String attachmentId) {
        checkNotNull(serverId, "serverId");
        checkNotNull(attachmentId, "attachmentId");
        return delete(ActionResponse.class, uri("/servers/%s/os-interface/%s", serverId, attachmentId)).execute();
    }

}
