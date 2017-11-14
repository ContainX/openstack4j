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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.map.reduce.ClusterService;
import com.huawei.openstack4j.api.map.reduce.ClusterTemplateService;
import com.huawei.openstack4j.api.map.reduce.DataSourceService;
import com.huawei.openstack4j.api.map.reduce.JobBinaryInternalService;
import com.huawei.openstack4j.api.map.reduce.JobBinaryService;
import com.huawei.openstack4j.api.map.reduce.JobExecutionService;
import com.huawei.openstack4j.api.map.reduce.JobService;
import com.huawei.openstack4j.api.map.reduce.MapReduceImageService;
import com.huawei.openstack4j.api.map.reduce.MapReducePluginService;
import com.huawei.openstack4j.api.map.reduce.MapReduceService;
import com.huawei.openstack4j.api.map.reduce.NodeGroupTemplateService;

/**
 * Map Reduce (Data Processing) Operations API implementation
 * 
 * @author Ekasit Kijsipongse
 */
public class MapReduceServiceImpl extends BaseMapReduceServices implements MapReduceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public ClusterService clusters() {
       return Apis.get(ClusterService.class);
    }

    /**
     * {@inheritDoc}
     */
     @Override
     public NodeGroupTemplateService nodeGroupTemplates() {
        return Apis.get(NodeGroupTemplateService.class);
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public ClusterTemplateService clusterTemplates() {
        return Apis.get(ClusterTemplateService.class);
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public MapReduceImageService images() {
        return Apis.get(MapReduceImageService.class);
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public MapReducePluginService plugins() {
        return Apis.get(MapReducePluginService.class);
     }
		
     /**
      * {@inheritDoc}
      */
     @Override
     public DataSourceService dataSources() {
	return Apis.get(DataSourceService.class);
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobBinaryInternalService jobBinaryInternals() {
        return Apis.get(JobBinaryInternalService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobBinaryService jobBinaries() {
        return Apis.get(JobBinaryService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobService jobs() {
        return Apis.get(JobService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecutionService jobExecutions() {
        return Apis.get(JobExecutionService.class);
    }

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public JobExeServiceImpl jobExes() {
		return Apis.get(JobExeServiceImpl.class);
	}

	/* 
	 * {@inheritDoc}
	 */
//	@Override
//	public ClusterServiceImpl2 clusters2() {
//		return Apis.get(ClusterServiceImpl2.class);
//	}
}
