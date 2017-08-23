/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC                                          
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
package com.huawei.openstack4j.model.sahara.builder;

import java.util.HashMap;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.sahara.JobConfig;
import com.huawei.openstack4j.model.sahara.JobExecution;

/**
 * Builder interface used for {@link JobExecution} object.
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface JobExecutionBuilder extends Builder<JobExecutionBuilder, JobExecution> {

    /**
     * See {@link JobExecution#getClusterId()}
     * 
     * @param clusterId the cluster identifier
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder clusterId(String clusterId);

    /**
     * See {@link JobExecution#getInputId()}
     * 
     * @param inputId the input identifier
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder inputId(String inputId);

    /**
     * See {@link JobExecution#getOutputId()}
     * 
     * @param outputId the output identifier
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder outputId(String outputId);

    /**
     * See {@link JobExecution#getJobConfigs()}
     * 
     * @param jobConfig the job configuration
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder setJobConfig(JobConfig jobConfig);

    /**
     * See {@link JobExecution#getJobId()}
     * 
     * @param jobId the job identifier
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder jobId(String jobId);
    
    /**
     * See {@link JobExecution#isPublic()}
     * 
     * @param isPublic is job execution public
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder isPublic(boolean isPublic);
    
    /**
     * See {@link JobExecution#isProtected()}
     * 
     * @param isProtected is job execution protected
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder isProtect(boolean isProtected);
    
    
    /**
     * See {@link JobExecution#getEngineJobId()}
     * 
     * @param engineJobId oozie workflow id
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder engineJobId(String engineJobId);
    
    
    /**
     * See {@link JobExecution#getDataSourceUrls()}
     * 
     * @param dataSourceUrls data source URL mapping
     * @return JobExecutionBuilder
     */
    JobExecutionBuilder dataSourceUrls(HashMap<String, String> dataSourceUrls);

}
