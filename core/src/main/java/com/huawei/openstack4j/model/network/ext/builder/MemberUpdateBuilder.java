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
package com.huawei.openstack4j.model.network.ext.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.ext.MemberUpdate;

/**
 * A Builder to update a  member
 * 
 * @author liujunpeng
 */
public interface MemberUpdateBuilder extends Builder<MemberUpdateBuilder, MemberUpdate> {
		
	/**
	 * @param adminStateUp
	 *            The administrative state of the member, which is up (true) or
	 *            down (false).
	 * @return MemberUpdateBuilder
	 */
	public MemberUpdateBuilder adminStateUp(boolean adminStateUp);

	/**
	 * @param weight
	 *            Weight of member.from 1 to 256
	 * @return MemberUpdateBuilder
	 */
	public MemberUpdateBuilder weight(Integer weight);
	
	/**
	 * 
	 * @param poolId
	 *            the lb pool identifier
	 * @return MemberUpdateBuilder
	 */
	public MemberUpdateBuilder poolId(String poolId);
}
