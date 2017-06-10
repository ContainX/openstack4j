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
package org.openstack4j.openstack.sahara.builder;

import org.openstack4j.model.sahara.builder.*;
import org.openstack4j.openstack.sahara.domain.*;

/**
 * The Data Processing Builders
 */
public class SaharaBuilders implements DataProcessingBuilders {

    @Override
    public ClusterBuilder cluster() {
        return SaharaCluster.builder();
    }

    @Override
    public ClusterTemplateBuilder clusterTemplate() {
        return SaharaClusterTemplate.builder();
    }

    @Override
    public NodeGroupBuilder nodeGroup() {
        return SaharaNodeGroup.builder();
    }

    @Override
    public NodeGroupTemplateBuilder nodeGroupTemplate() {
        return SaharaNodeGroupTemplate.builder();
    }

    @Override
    public ServiceConfigBuilder serviceConfig() {
        return SaharaServiceConfig.builder();
    }

    @Override
    public DataSourceBuilder dataSource() {
        return SaharaDataSource.builder();
    }

    @Override
    public JobBinaryBuilder jobBinary() {
        return SaharaJobBinary.builder();
    }

    @Override
    public JobBuilder job() {
        return SaharaJob.builder();
    }

    @Override
    public JobConfigBuilder jobConfig() {
        return SaharaJobConfig.builder();
    }

    @Override
    public JobExecutionBuilder jobExecution() {
        return SaharaJobExecution.builder();
    }
}
