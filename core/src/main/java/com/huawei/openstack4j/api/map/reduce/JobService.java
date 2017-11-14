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
package com.huawei.openstack4j.api.map.reduce;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.Job;
import com.huawei.openstack4j.model.map.reduce.JobConfigHint;
import com.huawei.openstack4j.model.map.reduce.options.JobListOptions;

/**
 * The manipulation of {@link Job}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface JobService extends RestService {

    /**
     * List jobs with filter options
     * 
     * @return list of jobs or empty
     */
     List<? extends Job> list(JobListOptions options);

    /**
     * Get a job by ID
     * @param JobId the job identifier
     * @return the job or null if not found
     */
     Job get(String JobId);

    /**
     * Create a new job
     *
     * @param job the job to create
     * @return the created job
     */
     Job create(Job job);
     
     
     /**
      * Update an exists job
      *
      * @param job the job to update
      * @return the updated job
      */
      Job update(Job job);

    /**
     * Delete the specified job
     * 
     * @param JobId the job identifier
     * @return the action response
     */
     ActionResponse delete(String JobId);

     /**
      * Get Job Configuration Hints
      * 
      * @param type the job type
      * @return the job configuration hints
      */
     JobConfigHint getConfigHint(String type);
}
