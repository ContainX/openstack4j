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
import com.huawei.openstack4j.model.network.ext.Member;
import com.huawei.openstack4j.model.network.ext.MemberUpdate;
/**
 * Networking (Neutron) Lbaas member Extension API
 * @author liujunpeng
 *
 */
public interface MemberService extends RestService {
    /**
     * List all members  that the current tenant has access to
     *
     * @return list of all Member
     */
    List<? extends Member> list();

    /**
     * Returns list of member filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends Member> list(Map<String, String> filteringParams);


    /**
     * Get the specified member by ID
     *
     * @param memberId the member identifier
     * @return the member or null if not found
     */
    Member get(String memberId);
    
    /**
     * Delete the specified member by ID
     * @param memberId the member identifier
     * @return the action response
     */
    ActionResponse delete(String memberId);
    /**
     * Create a member
     * @param member Member
     * @return Member
     */
    Member create(Member member);
    /**
     * Update a member
     * @param memberId the member identifier
     * @param member MemberUpdate
     * @return Member
     */
    Member update(String memberId,MemberUpdate member);
}
