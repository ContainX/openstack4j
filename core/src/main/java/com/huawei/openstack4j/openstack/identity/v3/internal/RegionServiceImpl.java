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
package com.huawei.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.identity.v3.RegionService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Region;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRegion;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneRegion.Regions;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Implementation of v3 region service
 *
 */
public class RegionServiceImpl extends BaseOpenStackService implements RegionService {

    @Override
    public Region create(Region region) {
        checkNotNull(region);
        return post(KeystoneRegion.class, uri(PATH_REGIONS)).entity(region).execute();
    }

    @Override
    public Region create(String regionId, String description, String parentRegionId) {
        checkNotNull(regionId);
        checkNotNull(description);
        checkNotNull(parentRegionId);
        return create(KeystoneRegion.builder().id(regionId).description(description).parentRegionId(parentRegionId).build());
    }

    @Override
    public Region get(String regionId) {
        checkNotNull(regionId);
        return get(KeystoneRegion.class, PATH_REGIONS, "/", regionId).execute();
    }

    @Override
    public Region update(Region region) {
        checkNotNull(region);
        return patch(KeystoneRegion.class, PATH_REGIONS, "/", region.getId()).entity(region).execute();
    }

    @Override
    public ActionResponse delete(String regionId) {
        checkNotNull(regionId);
        return deleteWithResponse(PATH_REGIONS, "/", regionId).execute();
    }

    @Override
    public List<? extends Region> list() {
        return get(Regions.class, uri(PATH_REGIONS)).execute().getList();
    }

}
