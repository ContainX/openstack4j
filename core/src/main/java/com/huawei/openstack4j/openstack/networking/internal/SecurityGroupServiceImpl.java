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
import java.util.Map;

import com.huawei.openstack4j.api.networking.SecurityGroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.SecurityGroup;
import com.huawei.openstack4j.model.network.SecurityGroupUpdate;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroup;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroup.SecurityGroups;

/**
 * FloatingIPService implementation that provides Neutron Floating-IP based Service Operations.
 *
 * @author Nathan Anderson
 */
public class SecurityGroupServiceImpl extends BaseNetworkingServices implements SecurityGroupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroup get(String id) {
        checkNotNull(id);
        return get(NeutronSecurityGroup.class, uri("/security-groups/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String groupId) {
        checkNotNull(groupId);
        return deleteWithResponse(uri("/security-groups/%s", groupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroup create(SecurityGroup securityGroup) {
        checkNotNull(securityGroup);
        return post(NeutronSecurityGroup.class, uri("/security-groups")).entity(securityGroup).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroup update(String securityGroupId, SecurityGroupUpdate securityGroupUpdate) {
        checkNotNull(securityGroupId);
        checkNotNull(securityGroupUpdate);
        return put(NeutronSecurityGroup.class, uri("/security-groups/%s", securityGroupId)).entity(securityGroupUpdate).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityGroup> list() {
        return get(SecurityGroups.class, uri("/security-groups")).execute().getList();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityGroup> list(Map<String, String> filteringParams) {
        Invocation<SecurityGroups> securityGroupInvocation = get(SecurityGroups.class, "/security-groups");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                securityGroupInvocation = securityGroupInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return securityGroupInvocation.execute().getList();
    }
}
