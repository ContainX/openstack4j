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

import com.huawei.openstack4j.api.senlin.SenlinProfileTypeService;
import com.huawei.openstack4j.model.senlin.ProfileType;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinProfileType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available profile-type services
 * 
 * @author lion
 */
public class SenlinProfileTypeServiceImpl extends BaseSenlinServices implements SenlinProfileTypeService {

	@Override
	public List<? extends ProfileType> list() {
		return get(SenlinProfileType.ProfileType.class, uri("/profile-types")).execute().getList();
	}

	@Override
	public ProfileType get(String ProfileType) {
		checkNotNull(ProfileType);
		return get(SenlinProfileType.class, uri("/profile-types/%s", ProfileType)).execute();
	}
}
