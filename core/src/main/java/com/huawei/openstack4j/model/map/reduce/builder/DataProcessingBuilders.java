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
package com.huawei.openstack4j.model.map.reduce.builder;

/**
 * The Map Reduce builders
 */
public interface DataProcessingBuilders {

    /**
     * The builder to create a map reduce cluster
     *
     * @return the cluster builder
     */
    public ClusterBuilder cluster();

    /**
     * The builder to create a map reduce cluster template
     *
     * @return the cluster template builder
     */
    public ClusterTemplateBuilder clusterTemplate();

    /**
     * The builder to create a map reduce node group
     *
     * @return the node group builder
     */
    public NodeGroupBuilder nodeGroup();

    /**
     * The builder to create a map reduce node group template
     *
     * @return the node group template builder
     */
    public NodeGroupTemplateBuilder nodeGroupTemplate();

    /**
     * The builder to create a map reduce service configuration
     *
     * @return the service configuration builder
     */
    public ServiceConfigBuilder serviceConfig();

    /**
     * The builder which creates a map reduce Data Source
     *
     * @return the data source builder
     */
    public DataSourceBuilder dataSource();

    /**
     * The builder which creates a map reduce Job Binary
     *
     * @return the job binary builder
     */
    public JobBinaryBuilder jobBinary();

    /**
     * The builder which creates a map reduce Job
     *
     * @return the job builder
     */
    public JobBuilder job();

    /**
     * The builder which creates a job configuration for map reduce job execution
     *
     * @return the job config builder
     */
    public JobConfigBuilder jobConfig();

    /**
     * The builder which creates a map reduce job execution
     *
     * @return the job execution builder
     */
    public JobExecutionBuilder jobExecution();


}
