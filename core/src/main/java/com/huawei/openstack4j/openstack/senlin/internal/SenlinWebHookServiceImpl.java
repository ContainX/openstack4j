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

import static com.google.common.base.Preconditions.checkNotNull;

import com.huawei.openstack4j.api.senlin.SenlinWebHookService;
import com.huawei.openstack4j.core.transport.HttpRequest;
import com.huawei.openstack4j.core.transport.internal.HttpExecutor;
import com.huawei.openstack4j.model.senlin.ActionID;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinAction;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinActionID;

/**
 * This class contains getters for all implementation of the available webHook services
 * 
 * @author lion
 */
public class SenlinWebHookServiceImpl extends BaseOpenStackService implements SenlinWebHookService {

	@Override
	public ActionID action(String webHookUrl) {
		checkNotNull(webHookUrl);
		HttpRequest newReq = new HttpRequest();
		newReq.toBuilder().methodPost().endpoint(webHookUrl).path("");
		newReq.builder(SenlinAction.class);
		return HttpExecutor.create().execute(newReq).getEntity(SenlinActionID.class);
	}
}
