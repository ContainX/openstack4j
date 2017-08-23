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
package com.huawei.openstack4j.openstack.tacker.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.tacker.VimService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.tacker.Vim;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.tacker.domain.TackerVim;
import com.huawei.openstack4j.openstack.tacker.domain.TackerVim.TackerVims;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 18, 2016
 */
public class VimServiceImpl extends BaseTackerServices implements VimService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends TackerVim> list() {
		return get(TackerVims.class, uri("/vims")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends TackerVim> list(Map<String, String> filteringParams) {
		Invocation<TackerVims> req = get(TackerVims.class, uri("/vims"));
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
	public TackerVim show(String vimId) {
		checkNotNull(vimId);
		return get(TackerVim.class, uri("/vims/%s", vimId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String vimId) {
		checkNotNull(vimId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/vims/%s", vimId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vim register(Vim vim) {
		return post(TackerVim.class, uri("/vims")).entity(vim).execute(ExecutionOptions.<TackerVim>create(PropagateOnStatus.on(500)));
	}

	/**
	 * {@inheritDoc}
	 */
	/*@Override
	public Vim update(String vimId, VimUpdate vimUpdate) {
		checkNotNull(vimId);
        checkNotNull(vimUpdate);
        return put(TackerVim.class, uri("/vims/%s", vimId)).entity(vimUpdate).execute();
	}*/
}
