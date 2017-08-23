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
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolUpdate;

/**
 * A Builder to update a lbpool
 * @author liujunpeng
 *
 */
public interface LbPoolUpdateBuilder extends Builder<LbPoolUpdateBuilder, LbPoolUpdate> {

	/**
	 * 
	 * @param lbMethod
	 *            The load-balancer algorithm, which is round-robin,
	 *            least-connections, and so on. This value, which must be
	 *            supported, is dependent on the load-balancer provider. Round
	 *            robin must be supported.
	 * @return LbPoolUpdateBuilder
	 */
	public LbPoolUpdateBuilder lbMethod(LbMethod lbMethod);
	
	/**
	 * 
	 * @param name
	 *            Pool name. Does not have to be unique.
	 * @return LbPoolUpdateBuilder
	 */
	public LbPoolUpdateBuilder name(String name);
	
	/**
	 * 
	 * @param description
	 *            Description for the pool.
	 * @return LbPoolUpdateBuilder
	 */
	public LbPoolUpdateBuilder description(String description);
	/**
	 * 
	 * @param adminStateUp
	 *            The administrative state of the lb pool, which is up (true) or
	 *            down (false).
	 * @return LbPoolUpdateBuilder
	 */
	public LbPoolUpdateBuilder adminStateUp(boolean adminStateUp);
	
}
