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
import com.huawei.openstack4j.model.network.ext.Member;

/**
 * A Builder to create a member
 * 
 * @author liujunpeng
 */
public interface MemberBuilder extends Builder<MemberBuilder, Member> {

	/**
	 * @param tenantId
	 *            Owner of the member. Only an administrative user can specify a
	 *            tenant ID other than its own.
	 * @return MemberBuilder
	 */
	public MemberBuilder tenantId(String tenantId);

	/**
	 * @param adminStateUp
	 *            The administrative state of the member, which is up (true) or
	 *            down (false).
	 * @return MemberBuilder
	 */
	public MemberBuilder adminStateUp(boolean adminStateUp);

	/**
	 *
	 * @param address
	 *            The IP address of the member.
	 * @return MemberBuilder
	 */
	public MemberBuilder address(String address);

	/**
	 * @param protocolPort
	 *            . The port on which the application is hosted..A valid value
	 *            is from 1 to 65535
	 * @return MemberBuilder
	 */
	public MemberBuilder protocolPort(Integer protocolPort);


	/**
	 * @param weight
	 *           Weight of member.from 1 to 256
	 * @return MemberBuilder
	 */
	public MemberBuilder weight(Integer weight);
	
	/**
	 * 
	 * @param lbPoolId
	 *            the lb pool identifier
	 * @return MemberBuilder
	 */
	public MemberBuilder poolId(String lbPoolId);
}
