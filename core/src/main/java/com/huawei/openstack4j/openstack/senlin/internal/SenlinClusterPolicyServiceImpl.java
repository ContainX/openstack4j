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

import com.huawei.openstack4j.api.senlin.SenlinClusterPolicyService;
import com.huawei.openstack4j.model.senlin.ClusterPolicy;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinClusterPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available cluster services
 * 
 * @author lion
 */
public class SenlinClusterPolicyServiceImpl extends BaseSenlinServices implements SenlinClusterPolicyService {

	@Override
	public List<? extends ClusterPolicy> list(String clusterID) {
		checkNotNull(clusterID);
		return get(SenlinClusterPolicy.ClusterPolicy.class, uri("/clusters/%s/policies", clusterID)).execute().getList();
	}

	@Override
	public ClusterPolicy get(String clusterID, String policyID) {
		checkNotNull(clusterID);
		checkNotNull(policyID);
		return get(SenlinClusterPolicy.class, uri("/clusters/%s/policies/%s", clusterID, policyID)).execute();
	}

}
