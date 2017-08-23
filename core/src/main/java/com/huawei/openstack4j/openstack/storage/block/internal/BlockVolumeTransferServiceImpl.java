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

import java.util.List;

import com.huawei.openstack4j.api.storage.BlockVolumeTransferService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeTransfer;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeTransfer;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeTransferAccept;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderVolumeTransfer.VolumeTransferList;

/**
 * Related services for managing volume transfers (os-volume-transfer)
 * 
 * @author Jeremy Unruh
 */
public class BlockVolumeTransferServiceImpl extends BaseBlockStorageServices implements BlockVolumeTransferService {

    @Override
    public List<? extends VolumeTransfer> list() {
        return list(Boolean.TRUE);
    }
    
    @Override
    public List<? extends VolumeTransfer> list(boolean detailed) {
        String url = (detailed) ? "/os-volume-transfer/detail" : "/os-volume-transfer";
        return get(VolumeTransferList.class, url).execute().getList();
    }
    
    @Override
    public VolumeTransfer get(String transferId) {
        checkNotNull(transferId, "TransferId must contain a value");
        return get(CinderVolumeTransfer.class, uri("/os-volume-transfer/%s", transferId)).execute();
    }
    
    @Override
    public VolumeTransfer create(String volumeId) {
        return create(volumeId, null);
    }

    @Override
    public VolumeTransfer create(String volumeId, String name) {
        checkNotNull(volumeId, "VolumeId must contain a value");
        return post(CinderVolumeTransfer.class, "/os-volume-transfer").entity(CinderVolumeTransfer.create(volumeId, name)).execute();
    }

    @Override
    public VolumeTransfer accept(String transferId, String authKey) {
        checkNotNull(transferId, "TransferId must contain a value");
        checkNotNull(authKey, "AuthKey must contain a value");
        return post(CinderVolumeTransfer.class, uri("/os-volume-transfer/%s/accept", transferId))
                  .entity(CinderVolumeTransferAccept.create(authKey)).execute();
    }

    @Override
    public ActionResponse delete(String transferId) {
        checkNotNull(transferId, "TransferId must contain a value");
        return delete(ActionResponse.class, uri("/os-volume-transfer/%s", transferId)).execute();
    }
}
