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
package com.huawei.openstack4j.openstack.map.reduce.builder;

import com.huawei.openstack4j.model.map.reduce.builder.ClusterBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.ClusterTemplateBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.DataSourceBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobBinaryBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobConfigBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.JobExecutionBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.NodeGroupTemplateBuilder;
import com.huawei.openstack4j.model.map.reduce.builder.ServiceConfigBuilder;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceCluster;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceDataSource;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJob;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinary;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobConfig;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecution;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceNodeGroup;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceNodeGroupTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceServiceConfig;

/**
 * The Map Reduce Builders
 */
public class MapReduceBuilders implements com.huawei.openstack4j.model.map.reduce.builder.DataProcessingBuilders {

	public ClusterBuilder cluster() {
		return MapReduceCluster.builder();
	}

	public ClusterTemplateBuilder clusterTemplate() {
		return MapReduceClusterTemplate.builder();
	}

	public NodeGroupBuilder nodeGroup() {
		return MapReduceNodeGroup.builder();
	}

	public NodeGroupTemplateBuilder nodeGroupTemplate() {
		return MapReduceNodeGroupTemplate.builder();
	}

	public ServiceConfigBuilder serviceConfig() {
		return MapReduceServiceConfig.builder();
	}

	public DataSourceBuilder dataSource() {
		return MapReduceDataSource.builder();
	}

	public JobBinaryBuilder jobBinary() {
		return MapReduceJobBinary.builder();
	}

	public JobBuilder job() {
		return MapReduceJob.builder();
	}

	public JobConfigBuilder jobConfig() {
		return MapReduceJobConfig.builder();
	}

	public JobExecutionBuilder jobExecution() {
		return MapReduceJobExecution.builder();
	}
}
