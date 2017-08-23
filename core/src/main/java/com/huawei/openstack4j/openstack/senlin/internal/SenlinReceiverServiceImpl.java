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

import com.huawei.openstack4j.api.senlin.SenlinReceiverService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Receiver;
import com.huawei.openstack4j.model.senlin.ReceiverCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinReceiver;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class contains getters for all implementation of the available receiver services
 * 
 * @author lion
 */
public class SenlinReceiverServiceImpl extends BaseSenlinServices implements SenlinReceiverService {

	@Override
	public List<? extends Receiver> list() {
		return get(SenlinReceiver.Receiver.class, uri("/receivers")).execute().getList();
	}

	@Override
	public Receiver create(ReceiverCreate newReceiver) {
		checkNotNull(newReceiver);
		return post(SenlinReceiver.class, uri("/receivers")).entity(newReceiver).execute();
	}

	@Override
	public Receiver get(String policyID) {
		checkNotNull(policyID);
		return get(SenlinReceiver.class, uri("/receivers/%s", policyID)).execute();
	}

	@Override
	public ActionResponse delete(String policyID) {
		checkNotNull(policyID);
		return deleteWithResponse(uri("/receivers/%s", policyID)).execute();
	}
}
