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
package org.openstack4j.openstack.tacker.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.tacker.VnfdService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.tacker.Vnfd;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.tacker.domain.TackerVnfd;
import org.openstack4j.openstack.tacker.domain.TackerVnfd.TackerVnfds;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public class VnfdServiceImpl extends BaseTackerServices implements VnfdService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends TackerVnfd> list() {
		return get(TackerVnfds.class, uri("/vnfds")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends TackerVnfd> list(Map<String, String> filteringParams) {
		Invocation<TackerVnfds> req = get(TackerVnfds.class, uri("/vnfds"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TackerVnfd get(String vnfdId) {
		checkNotNull(vnfdId);
		return get(TackerVnfd.class, uri("/vnfds/%s", vnfdId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String vnfdId) {
		checkNotNull(vnfdId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/vnfds/%s", vnfdId)).executeWithResponse());
	}

	@Override
	public Vnfd create(Vnfd vnfd) {
		return post(TackerVnfd.class, uri("/vnfds")).entity(vnfd).execute(ExecutionOptions.<TackerVnfd>create(PropagateOnStatus.on(500)));
	}
}
