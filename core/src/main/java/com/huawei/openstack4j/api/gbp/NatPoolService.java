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
package com.huawei.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.NatPool;

/**
 * This interface defines all methods for the manipulation of nat-pool
 * 
 * @author vinod borole
 *  
 */
public interface NatPoolService {
    /**
     * List all nat pool
     * 
     * @return List of nat pool
     */
    List<? extends NatPool> list();
    /**
     * Returns list of nat pool filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends NatPool> list(Map<String, String> filteringParams);
    /**
     * Get the specified nat pool by ID
     *
     * @param id the nat pool id
     * @return nat pool or null if not found
     */
    NatPool get(String id);
    /**
     * Delete of the nat pool
     * @param id the nat pool id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new nat pool
     *
     * @param nat pool
     * @return the newly created nat pool
     */
    NatPool create(NatPool natpool);
    /**
     * Updates an existing nat pool
     * 
     * @param nat pool identifier
     * @param nat pool that is be used to updated
     * @return the updated nat pool
     */
   NatPool update(String natpoolId,NatPool natpool);
}
