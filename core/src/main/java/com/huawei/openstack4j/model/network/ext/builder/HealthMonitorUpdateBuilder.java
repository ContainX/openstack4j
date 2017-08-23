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
import com.huawei.openstack4j.model.network.ext.HealthMonitorUpdate;
/**
 *  A builder to update a vip
 * @author liujunpeng
 *
 */
public interface HealthMonitorUpdateBuilder  extends Builder<HealthMonitorUpdateBuilder,HealthMonitorUpdate>{
	/**
	 * @param dealy
	 *            The time, in seconds, between sending probes to members.
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder delay(Integer delay);
	/**
	 * 
	 * @param urlPath
	 *            Path portion of URI that will be probed if type is HTTP(S).
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder urlPath(String urlPath);

	/**
	 *
	 * @param expectedCodes
	 *            Expected HTTP codes for a passing HTTP(S) monitor.
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder expectedCodes(String expectedCodes);
	
	/**
	 * 
	 * @param httpMethod
	 *            GET/PUT/POST
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder httpMethod(String httpMethod);
	
	/**
	 * 
	 * @param maxRetries
	 *            Maximum consecutive health probe tries.
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder maxRetries(Integer maxRetries);
	
	/**
	 * 
	 * @param adminStateUp
	 *            The administrative state of the VIP. A valid value is true
	 *            (UP) or false (DOWN).
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder adminStateUp(boolean adminStateUp);
	
	/**
	 * 
	 * @param timeout
	 *            Time in seconds to timeout each probe.
	 * @return HealthMonitorUpdateBuilder
	 */
	public HealthMonitorUpdateBuilder timeout(Integer timeout);
	
}
