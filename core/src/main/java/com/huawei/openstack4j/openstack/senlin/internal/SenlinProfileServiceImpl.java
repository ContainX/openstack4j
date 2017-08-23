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

import com.huawei.openstack4j.api.senlin.SenlinProfileService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Profile;
import com.huawei.openstack4j.model.senlin.ProfileCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinProfile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available profile services
 * 
 * @author lion
 */
public class SenlinProfileServiceImpl extends BaseSenlinServices implements SenlinProfileService {

	@Override
	public List<? extends Profile> list() {
		return get(SenlinProfile.Profile.class, uri("/profiles")).execute().getList();
	}

	@Override
	public Profile create(ProfileCreate newProfile) {
		checkNotNull(newProfile);
		return post(SenlinProfile.class, uri("/profiles")).entity(newProfile).execute();
	}

	@Override
	public Profile get(String policyID) {
		checkNotNull(policyID);
		return get(SenlinProfile.class, uri("/profiles/%s", policyID)).execute();
	}

	@Override
	public Profile update(String policyID, ProfileCreate newPolicy) {
		checkNotNull(policyID);
		checkNotNull(newPolicy);
		return patch(SenlinProfile.class, uri("/profiles/%s", policyID)).entity(newPolicy).execute();
	}

	@Override
	public ActionResponse delete(String policyID) {
		checkNotNull(policyID);
		return deleteWithResponse(uri("/profiles/%s", policyID)).execute();
	}
}
