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

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.sahara.internal.JobExeServiceImpl;

/**
 * (Sahara) Data Processing Operations API
 * 
 * @author Ekasit Kijsipongse
 */
public interface SaharaService extends RestService {

    /**
     * Cluster Service API
     *
     * @return the cluster service
     */
    ClusterService clusters();
    
    /**
     * Node Group Template Service API
     *
     * @return the node group template service
     */
    NodeGroupTemplateService nodeGroupTemplates();

    /**
     * Cluster Template Service API
     *
     * @return the cluster template service
     */
    ClusterTemplateService clusterTemplates();

    /**
     * Image Service API
     *
     * @return the image service
     */
    SaharaImageService images();

    /**
     * Plugin Service API
     *
     * @return the plugin service
     */
    SaharaPluginService plugins();

    /**
     * DataSource Service API
     *
     * @return the datasource service
     */
    DataSourceService dataSources();

    /**
     * Job Binary Internal Service API
     *
     * @return the job binary internal service
     */
	JobBinaryInternalService jobBinaryInternals();

    /**
     * Job Binary Service API
     *
     * @return the job binary service
     */
    JobBinaryService jobBinaries();

    /**
     * Job Service API
     *
     * @return the job service
     */
    JobService jobs();


    /**
     * Job Execution Service API
     *
     * @return the job execution service
     */
    JobExecutionService jobExecutions();
    
    /**
     * Job Exe Service API
     *
     * @return the job exe service
     */
    JobExeServiceImpl jobExes();
}
