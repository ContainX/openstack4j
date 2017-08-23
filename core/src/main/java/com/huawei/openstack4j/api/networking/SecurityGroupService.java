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
package com.huawei.openstack4j.api.networking;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.SecurityGroup;
import com.huawei.openstack4j.model.network.SecurityGroupUpdate;


/**
 * Provides Neutron-based Security Group services.
 *
 * @author Nathan Anderson
 */
public interface SecurityGroupService extends RestService {

    /**
     * Get list of security groups accessible by the current tenant
     *
     * @return the list<? extends security group>
     */
    List<? extends SecurityGroup> list();

    /**
     * Gets the Security Group by id.
     *
     * @param id the id
     * @return the security group
     */
    SecurityGroup get(String id);

    /**
     * Deletes SecurityGroup by id.
     *
     * @param id SecurityGroup id
     */
    ActionResponse delete(String id);

    /**
     * Creates a SecurityGroup.
     *
     * @param securityGroup the security group
     * @return the security group
     */
    SecurityGroup create(SecurityGroup securityGroup);

    /**
     * Updates a SecurityGroup associated by the specified {@code securityGroupId}
     *
     * @param securityGroupId     the security group identifier
     * @param securityGroupUpdate the security group options to update (see {@link Builders#securityGroupUpdate()}
     * @return the updated security group
     */
    SecurityGroup update(String securityGroupId, SecurityGroupUpdate securityGroupUpdate);

    /**
     * Get list of security groups accessible by the current tenant
     * @param filteringParams map (name, value) of filtering parameters
     *
     * @return the list<? extends security group>
     */
    List<? extends SecurityGroup> list(Map<String, String> filteringParams);
}
