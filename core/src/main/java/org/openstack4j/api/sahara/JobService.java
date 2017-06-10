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
package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.Job;
import org.openstack4j.model.sahara.JobConfigHint;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface JobService extends RestService {

    /**
     * List all jobs
     * 
     * @return list of jobs or empty
     */
     List<? extends Job> list();

    /**
     * Get a job by ID
     * @param JobId the job identifier
     * @return the job or null if not found
     */
     Job get(String JobId);

    /**
     * Create a new job
     *
     * @param jobBinary the job to create
     * @return the created job
     */
     Job create(Job jobBinary);

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
