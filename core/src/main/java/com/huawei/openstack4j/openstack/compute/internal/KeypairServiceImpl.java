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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import com.huawei.openstack4j.api.compute.KeypairService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Keypair;
import com.huawei.openstack4j.openstack.compute.domain.NovaKeypair;
import com.huawei.openstack4j.openstack.compute.domain.NovaKeypair.Keypairs;

/**
 * Keypair Service manages SSH Keys within OpenStack Compute (Nova).
 *
 * @author Jeremy Unruh
 */
public class KeypairServiceImpl extends BaseComputeServices implements KeypairService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Keypair> list() {
		return get(Keypairs.class, uri("/os-keypairs")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Keypair get(String name) {
		checkNotNull(name);
		return get(NovaKeypair.class, uri("/os-keypairs/%s", name)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String name) {
		checkNotNull(name);
		return deleteWithResponse(uri("/os-keypairs/%s", name)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Keypair create(String name, @Nullable String publicKey) {
		checkNotNull(name);
		return post(NovaKeypair.class, uri("/os-keypairs")).entity(NovaKeypair.create(name, publicKey)).execute();
	}

}
