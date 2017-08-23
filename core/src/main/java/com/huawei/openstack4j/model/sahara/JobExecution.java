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
package com.huawei.openstack4j.model.sahara;

import java.util.Date;
import java.util.HashMap;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.sahara.builder.JobExecutionBuilder;

/**
 * An Openstack Sahara Job Execution (X)
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface JobExecution extends ModelEntity, Buildable<JobExecutionBuilder> {

    /**
     * @return the cluster identifier
     */
    String getClusterId();

    /**
     * @return the input identifier
     */
    String getInputId();

    /**
     * @return the output identifier
     */
    String getOutputId();

    /**
     * @return the job configurations
     */
    JobConfig getJobConfigs();

    /**
     * @return the tenant id of the job execution
     */
    String getTenantId();

    /**
     * @return start time of the job execution
     */
    Date getStartTime();

    /**
     * @return end time of the job execution
     */
    Date getEndTime();

    /**
     * @return create time of the job execution
     */
    Date getCreatedAt();

    /**
     * @return update time of the job execution
     */
    Date getUpdatedAt();

    /**
     * @return return code of the job execution
     */
    String getReturnCode();

    /**
     * @return the OOzie job identifier
     */
    String getOozieJobId();

    /**
     * @return the job identifier
     */
    String getJobId();

    /**
     * @return the job execution identifier
     */
    String getId();

    /**
     * @return the progress of the job execution
     */
    String getProgress();

    /**
     * @return informations of the job executions
     */
    JobExecutionInfo getInfo();

    /**
     * @return the job identifier
     */
    String getJobIdForExecution();
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is job execution protected
     */
    Boolean isProtected();
    
    /**
     * reserved attribute, not support for now
     * 
     * @return is job execution public
     */
    Boolean isPublic();
    
    /**
     * OTC new property: oozie workflow id
     * 
     * @return the oozie workflow id
     */
    String getEngineJobId();
    
    /**
     * OTC new property: Data source URL list of a job
     * 
     * @return A HashMap which key is data-source id value is data source url
     */
    HashMap<String, String> getDataSourceUrls();
    
    
}
