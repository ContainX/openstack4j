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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.MemberV2;
import com.huawei.openstack4j.model.network.ext.MemberV2Update;

/**
 * Networking (Neutron) Lbaas V2 pool Extension API
 * @author emjburns
 */
public interface LbPoolV2Service extends RestService {
    /**
     * List all lb pools that the current tenant has access to
     *
     * @return list of all lb pools
     */
    List<? extends LbPoolV2> list();

    /**
     * Returns list of lb v2 pools filtered by parameters.
     *
     * @param filteringParams
     *            map (name, value) of filtering parameters
     * @return List
     */
    List<? extends LbPoolV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified lb pool by ID
     *
     * @param lbPoolId
     *            the lb v2 pool identifier
     * @return the lbPoolV2 or null if not found
     */
    LbPoolV2 get(String lbPoolId);

    /**
     * Delete the specified lb Pool by ID
     *
     * @param lbPoolId
     *            the lb pool identifier
     * @return the action response
     */
    ActionResponse delete(String lbPoolId);

    /**
     * Create a lb Pool
     *
     * @param lbPool
     *            LbPool
     * @return lbPoolV2
     */
    LbPoolV2 create(LbPoolV2 lbPool);

    /**
     * Update a lb pool
     *
     * @param lbPoolId
     *            the lb pool identifier
     * @param lbPool
     *            LbPoolV2Update
     * @return LbPoolV2
     */
    LbPoolV2 update(String lbPoolId, LbPoolV2Update lbPool);

    /**
     * List all members  that the current tenant has access to
     *
     * @param lbPoolId the load balancer pool
     * @return list of all Member
     */
    List<? extends MemberV2> listMembers(String lbPoolId);

    /**
     * Returns list of member filtered by parameters.
     *
     * @param lbPoolId the load balancer pool
     * @param filteringParams map (name, value) of filtering parameters
     * @return
     */
    List<? extends MemberV2> listMembers(String lbPoolId, Map<String, String> filteringParams);


    /**
     * Get the specified member by ID
     *
     * @param lbPoolId the load balancer pool
     * @param memberId the member identifier
     * @return the member or null if not found
     */
    MemberV2 getMember(String lbPoolId, String memberId);

    /**
     * Create a member
     * @param lbPoolId the load balancer pool
     * @param member Member
     * @return Member
     */
    MemberV2 createMember(String lbPoolId, MemberV2 member);

    /**
     * Delete the specified member by ID
     * @param lbPoolId the load balancer pool
     * @param memberId the member identifier
     * @return the action response
     */
    ActionResponse deleteMember(String lbPoolId, String memberId);

    /**
     * Update a member
     * @param lbPoolId the load balancer pool
     * @param memberId the member identifier
     * @param member MemberUpdate
     * @return Member
     */
    MemberV2 updateMember(String lbPoolId, String memberId, MemberV2Update member);
}
