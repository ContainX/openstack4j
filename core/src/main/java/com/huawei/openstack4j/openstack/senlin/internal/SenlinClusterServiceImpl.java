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
package com.huawei.openstack4j.openstack.senlin.internal;

import java.util.List;

import com.huawei.openstack4j.api.senlin.SenlinClusterService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.ActionID;
import com.huawei.openstack4j.model.senlin.Cluster;
import com.huawei.openstack4j.model.senlin.ClusterActionCreate;
import com.huawei.openstack4j.model.senlin.ClusterCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinActionID;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinCluster;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available cluster services
 * 
 * @author lion
 */
public class SenlinClusterServiceImpl extends BaseSenlinServices implements SenlinClusterService {

	@Override
	public List<? extends Cluster> list() {
		return get(SenlinCluster.Cluster.class, uri("/clusters")).execute().getList();
	}

	@Override
	public Cluster get(String clusterID) {
		checkNotNull(clusterID);
		return get(SenlinCluster.class, uri("/clusters/%s", clusterID)).execute();
	}

	@Override
	public Cluster create(ClusterCreate newCluster) {
		checkNotNull(newCluster);
		return post(SenlinCluster.class, uri("/clusters")).entity(newCluster).execute();
	}

	@Override
	public ActionResponse delete(String clusterID) {
		checkNotNull(clusterID);
		return deleteWithResponse(uri("/clusters/%s", clusterID)).execute();
	}

	@Override
	public Cluster update(String clusterID, ClusterCreate newCluster) {
		checkNotNull(clusterID);
		checkNotNull(newCluster);
		return patch(SenlinCluster.class, uri("/clusters/%s", clusterID)).entity(newCluster).execute();
	}

	@Override
	public ActionID action(String clusterID, ClusterActionCreate newClusterAction) {
		checkNotNull(clusterID);
		checkNotNull(newClusterAction);
		return post(SenlinActionID.class, uri("/clusters/%s/actions", clusterID)).entity(newClusterAction).execute();
	}

}
