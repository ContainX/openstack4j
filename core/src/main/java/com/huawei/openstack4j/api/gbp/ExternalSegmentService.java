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
import com.huawei.openstack4j.model.gbp.ExternalSegment;

/**
 * This interface defines all methods for the manipulation of external segments
 * 
 * @author vinod borole
 * 
 */ 
public interface ExternalSegmentService {
    /**
     * List all external segment
     * 
     * @return List of external segment
     */
    List<? extends ExternalSegment> list();
    /**
     * Returns list of external segments filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends ExternalSegment> list(Map<String, String> filteringParams);
    /**
     * Get the specified external segment by ID
     *
     * @param id the external segment id
     * @return external segment or null if not found
     */
    ExternalSegment get(String id);
    /**
     * Delete of the external segment
     * @param id the external segment id
     * @return the action response
     */
    ActionResponse delete(String id);
    /**
     * Create a new external segment
     *
     * @param external segment
     * @return the newly created external segment
     */
    ExternalSegment create(ExternalSegment externalSegment);
    /**
     * Updates an existing external segment
     * 
     * @param external segment identifier
     * @param external segment that is be used to updated
     * @return the updated external segment
     */
    ExternalSegment update(String externalSegmentId,ExternalSegment externalSegment);
}
