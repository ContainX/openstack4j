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
import com.huawei.openstack4j.model.map.reduce.JobExecution;
import com.huawei.openstack4j.model.map.reduce.options.JobExecutionListOptions;

/**
 * The manipulation of {@link JobExecution}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface JobExecutionService extends RestService {

    /**
     * Create a new job execution
     * 
     * @param jobExecution the job execution to create
     * @return the created job execution
     */
    JobExecution create(JobExecution jobExecution);

    /**
     * List job executions with filter options
     * 
     * @return list of job executions or empty
     */
    List<? extends JobExecution> list(JobExecutionListOptions options);

    /**
     * Get a job execution by ID
     * 
     * @param jobExecutionId the job execution identifier
     * @return the job execution or null if not found
     */
    JobExecution get(String jobExecutionId);

    /**
     * Refresh the status of a job execution by ID
     * 
     * @param jobExecutionId the job execution identifier 
     * @return the refreshed job execution or null if not found
     */
    JobExecution refreshStatus(String jobExecutionId);

    /**
     * Cancel a job execution by ID
     * 
     * @param jobExecutionId the job execution identifier
     * @return the canceled job execution or null if not found
     */
    JobExecution cancel(String jobExecutionId);

    /**
     * Delete a job execution by ID
     * 
     * @param jobExecutionId the job execution identifier
     * @return the action response
     */
    ActionResponse delete(String jobExecutionId);
}
