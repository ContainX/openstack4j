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
package com.huawei.openstack4j.api.identity.v3;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Region;

/**
 * Identity V3 Region operations
 *
 */
public interface RegionService extends RestService {

    /**
     * Create a new region
     *
     * @param region the region
     * @return the newly created region
     */
    Region create(Region region);

    /**
     * Create a new region
     *
     * @param regionId the user-defined region id
     * @param description the description of the region
     * @param parentRegionId the region id of the parent region
     * @return the newly created region
     */
    Region create(String regionId, String description, String parentRegionId);

    /**
     * Get details for a region specified by id
     *
     * @param regionId the region id
     * @return the region
     */
    Region get(String regionId);

    /**
     * Update a region
     *
     * @param region the region set to update
     * @return the updated region
     */
    Region update(Region region);

    /**
     * Delete a region specified by id
     *
     * @param regionId the id of the region
     * @return the ActionResponse
     */
    ActionResponse delete(String regionId);

    /**
     * List regions
     *
     * @return a list of regions
     */
    List<? extends Region> list();

}
