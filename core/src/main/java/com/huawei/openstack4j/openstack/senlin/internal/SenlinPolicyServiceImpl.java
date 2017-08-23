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

import com.huawei.openstack4j.api.senlin.SenlinPolicyService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Policy;
import com.huawei.openstack4j.model.senlin.PolicyCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available policy services
 * 
 * @author lion
 */
public class SenlinPolicyServiceImpl extends BaseSenlinServices implements SenlinPolicyService {

	@Override
	public List<? extends Policy> list() {
		return get(SenlinPolicy.Policy.class, uri("/policies")).execute().getList();
	}

	@Override
	public Policy create(PolicyCreate newPolicy) {
		checkNotNull(newPolicy);
		return post(SenlinPolicy.class, uri("/policies")).entity(newPolicy).execute();
	}

	@Override
	public Policy get(String policyID) {
		checkNotNull(policyID);
		return get(SenlinPolicy.class, uri("/policies/%s", policyID)).execute();
	}

	@Override
	public Policy update(String policyID, PolicyCreate newPolicy) {
		checkNotNull(policyID);
		checkNotNull(newPolicy);
		return patch(SenlinPolicy.class, uri("/policies/%s", policyID)).entity(newPolicy).execute();
	}

	@Override
	public ActionResponse delete(String policyID) {
		checkNotNull(policyID);
		return deleteWithResponse(uri("/policies/%s", policyID)).execute();
	}

}
