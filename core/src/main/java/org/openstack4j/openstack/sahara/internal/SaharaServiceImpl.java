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
package org.openstack4j.openstack.sahara.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.sahara.ClusterService;
import org.openstack4j.api.sahara.ClusterTemplateService;
import org.openstack4j.api.sahara.DataSourceService;
import org.openstack4j.api.sahara.JobBinaryInternalService;
import org.openstack4j.api.sahara.JobBinaryService;
import org.openstack4j.api.sahara.JobExecutionService;
import org.openstack4j.api.sahara.JobService;
import org.openstack4j.api.sahara.NodeGroupTemplateService;
import org.openstack4j.api.sahara.SaharaImageService;
import org.openstack4j.api.sahara.SaharaPluginService;
import org.openstack4j.api.sahara.SaharaService;

/**
 * Sahara (Data Processing) Operations API implementation
 * 
 * @author Ekasit Kijsipongse
 */
public class SaharaServiceImpl extends BaseSaharaServices implements SaharaService {

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
     public SaharaImageService images() {
        return Apis.get(SaharaImageService.class);
     }

     /**
      * {@inheritDoc}
      */
     @Override
     public SaharaPluginService plugins() {
        return Apis.get(SaharaPluginService.class);
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
	@Override
	public ClusterServiceImpl2 clusters2() {
		return Apis.get(ClusterServiceImpl2.class);
	}
}
