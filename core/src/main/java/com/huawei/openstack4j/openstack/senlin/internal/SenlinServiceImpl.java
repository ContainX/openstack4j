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


import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.senlin.*;

/**
 * This class contains getters for all implementation of the available senlin services
 * 
 * @author lion
 */
public class SenlinServiceImpl extends BaseSenlinServices implements SenlinService {

	@Override
	public SenlinPolicyService policy() {
		return Apis.get(SenlinPolicyService.class);
	}

	@Override
	public SenlinActionService action() {
		return Apis.get(SenlinActionService.class);
	}

	@Override
	public SenlinBuildInfoService buildInfo() {
		return Apis.get(SenlinBuildInfoService.class);
	}

	@Override
	public SenlinClusterPolicyService clusterPolicy() {
		return Apis.get(SenlinClusterPolicyService.class);
	}

	@Override
	public SenlinClusterService cluster() {
		return Apis.get(SenlinClusterService.class);
	}

	@Override
	public SenlinEventService event() {
		return Apis.get(SenlinEventService.class);
	}

	@Override
	public SenlinNodeService node() {
		return Apis.get(SenlinNodeService.class);
	}

	@Override
	public SenlinProfileService profile() {
		return Apis.get(SenlinProfileService.class);
	}

	@Override
	public SenlinProfileTypeService profileType() {
		return Apis.get(SenlinProfileTypeService.class);
	}

	@Override
	public SenlinPolicyTypeService policyType() {
		return Apis.get(SenlinPolicyTypeService.class);
	}

	@Override
	public SenlinReceiverService receiver() {
		return Apis.get(SenlinReceiverService.class);
	}

	@Override
	public SenlinWebHookService webHook() {
		return Apis.get(SenlinWebHookService.class);
	}

	@Override
	public SenlinVersionService version() {
		return Apis.get(SenlinVersionService.class);
	}
}
