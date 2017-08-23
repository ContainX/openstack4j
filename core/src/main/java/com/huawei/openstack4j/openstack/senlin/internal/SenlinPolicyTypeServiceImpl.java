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

import com.huawei.openstack4j.api.senlin.SenlinPolicyTypeService;
import com.huawei.openstack4j.model.senlin.PolicyType;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinPolicyType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available policy-type services
 * 
 * @author lion
 */
public class SenlinPolicyTypeServiceImpl extends BaseSenlinServices implements SenlinPolicyTypeService {

	@Override
	public List<? extends PolicyType> list() {
		return get(SenlinPolicyType.PolicyType.class, uri("/policy-types")).execute().getList();
	}

	@Override
	public PolicyType get(String policyType) {
		checkNotNull(policyType);
		return get(SenlinPolicyType.class, uri("/policy-types/%s", policyType)).execute();
	}
}
