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

import com.huawei.openstack4j.api.manila.SchedulerStatsService;
import com.huawei.openstack4j.model.manila.BackendStoragePool;
import com.huawei.openstack4j.openstack.manila.domain.ManilaBackendStoragePool;

public class SchedulerStatsServiceImpl extends BaseShareServices implements SchedulerStatsService {
    @Override
    public List<? extends BackendStoragePool> pools() {
        return list(false);
    }

    @Override
    public List<? extends BackendStoragePool> poolsDetail() {
        return list(true);
    }

    private List<? extends BackendStoragePool> list(boolean detail) {
        return get(ManilaBackendStoragePool.BackendStoragePools.class, uri("/scheduler-stats/pools" +  (detail ? "/detail" : "")))
                .execute()
                .getList();
    }
}
