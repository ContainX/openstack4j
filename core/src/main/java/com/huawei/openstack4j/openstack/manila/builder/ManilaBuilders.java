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
package com.huawei.openstack4j.openstack.manila.builder;

import com.huawei.openstack4j.model.manila.builder.*;
import com.huawei.openstack4j.openstack.manila.domain.*;

/**
 * The Share File System Builders
 */
public class ManilaBuilders implements SharedFileSystemBuilders {

    @Override
    public SecurityServiceCreateBuilder securityService() {
        return ManilaSecurityServiceCreate.builder();
    }

    @Override
    public ShareNetworkCreateBuilder shareNetwork() {
        return ManilaShareNetworkCreate.builder();
    }

    @Override
    public ShareCreateBuilder share() {
        return ManilaShareCreate.builder();
    }

    @Override
    public ShareTypeCreateBuilder shareType() {
        return ManilaShareTypeCreate.builder();
    }

    @Override
    public ShareSnapshotCreateBuilder shareSnapshot() {
        return ManilaShareSnapshotCreate.builder();
    }

    @Override
    public ShareManageBuilder shareManage() {
        return ManilaShareManage.builder();
    }
}
