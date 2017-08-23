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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.VipService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.Vip;
import com.huawei.openstack4j.model.network.ext.VipUpdate;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronVip;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronVip.Vips;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 *  OpenStack (Neutron) Lbaas vip based Operations
 * @author liujunpeng
 *
 */
public class VipServiceImpl extends BaseNetworkingServices implements
		VipService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Vip> list() {
		return get(Vips.class, uri("/lb/vips")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Vip> list(Map<String, String> filteringParams) {
		Invocation<Vips> req = get(Vips.class, uri("/lb/vips"));
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
	public Vip get(String vipId) {
		checkNotNull(vipId);
		return get(NeutronVip.class,uri("/lb/vips/%s",vipId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String vipId) {
		checkNotNull(vipId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/lb/vips/%s",vipId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vip create(Vip vip) {
		checkNotNull(vip);
		return post(NeutronVip.class,uri("/lb/vips")).entity(vip).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vip update(String vipId, VipUpdate vip) {
		checkNotNull(vipId);
		checkNotNull(vip);
		return put(NeutronVip.class,uri("/lb/vips/%s",vipId)).entity(vip).execute();
	}

}
