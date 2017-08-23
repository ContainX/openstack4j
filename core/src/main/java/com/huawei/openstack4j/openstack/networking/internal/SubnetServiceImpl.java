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
package com.huawei.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.networking.SubnetService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.Subnet;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSubnet;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSubnetUpdate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSubnet.Subnets;

/**
 * OpenStack (Neutron) Subnet based Operations implementation
 * 
 * @author Jeremy Unruh
 */
public class SubnetServiceImpl extends BaseNetworkingServices implements SubnetService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Subnet> list() {
        return get(Subnets.class, uri("/subnets")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subnet get(String subnetId) {
        checkNotNull(subnetId);
        return get(NeutronSubnet.class, uri("/subnets/%s", subnetId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String subnetId) {
        checkNotNull(subnetId);
        return deleteWithResponse(uri("/subnets/%s", subnetId)).execute();
    }

    @Override
    public Subnet create(Subnet subnet) {
        checkNotNull(subnet);
        return post(NeutronSubnet.class, uri("/subnets")).entity(subnet).execute();
    }

    public Subnet update(Subnet subnet) {
        checkNotNull(subnet);
        return update(subnet.getId(), subnet);
    }

    public Subnet update(String subnetId, Subnet subnet) {
        checkNotNull(subnetId);
        checkNotNull(subnet);
        return put(NeutronSubnet.class, uri("/subnets/%s", subnetId))
                .entity(NeutronSubnetUpdate.createFromSubnet(subnet))
                .execute();
    }
}
