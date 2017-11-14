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

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.openstack.map.reduce.constants.BillingType;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterInfo;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreateResult;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;

/**
 * The implementation of manipulation of {@link MapReduceClusterInfo}
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public class ClusterServiceImpl2 extends BaseMapReduceServices implements RestService {

	/**
	 * {@inheritDoc}
	 */
	public MapReduceClusterInfo get(String clusterId) {
		checkNotNull(!Strings.isNullOrEmpty(clusterId), "parameter `clusterId` should not be null");
		return get(MapReduceClusterInfo.class, uri("/cluster_infos/%s", clusterId)).execute();
	}

	public MapReduceClusterCreateResult createAndRunJob(MapReduceClusterCreate cluster, MapReduceJobExeCreate jobExe) {
		checkNotNull(cluster, "parameter `cluster` should not be null");
		// checkNotNull(jobExe, "parameter `jobExe` should not be null");
		// setup default values
		ArrayList<MapReduceJobExeCreate> jobs = jobExe == null ? new ArrayList<MapReduceJobExeCreate>() : Lists.newArrayList(jobExe);
		MapReduceClusterCreate create = cluster.toBuilder().billingType(BillingType.Metered).masterNodeNum(2).jobs(jobs)
				.build();
		return post(MapReduceClusterCreateResult.class, "/run-job-flow").entity(create).execute();
	}

}
