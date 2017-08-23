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
package com.huawei.openstack4j.model.network.ext;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorUpdateBuilder;

/**
 * An entity used to update a healthMonitor
 * 
 * @author liujunpeng
 *
 */
public interface HealthMonitorUpdate extends ModelEntity,
		Buildable<HealthMonitorUpdateBuilder> {

	/**
	 * Optional.
	 * 
	 * @return delay The time, in seconds, between sending probes to members
	 */
	public Integer getDelay();

	/**
	 * Optional.
	 * 
	 * @return urlPath The HTTP path of the request sent by the monitor to test
	 *         the health of a member. Must be a string beginning with a forward
	 *         slash (/).
	 */
	public String getUrlPath();
	
	/**
	 * @return timeout The maximum number of seconds for a monitor to wait for a
	 *         connection to be established before it times out. This value must
	 *         be less than the delay value.
	 */
	public Integer getTimeout();
	
	/**
	 * 
	 * @return maxRetries Number of allowed connection failures before changing
	 *         the status of the member to INACTIVE. A valid value is from 1 to
	 *         10.
	 */
	public Integer getMaxRetries();
	
	/**
	 * Optional.
	 * 
	 * @return httpMethod The HTTP method that the monitor uses for requests.
	 */
	public String getHttpMethod();
	
	/**
	 * Optional.
	 * 
	 * @return expectedCodes Expected HTTP codes for a passing HTTP(S) monitor.
	 */
	public String getExpectedCodes();
	
	/**
	 * Optional.
	 * 
	 * @return adminstateup The administrative state of the health monitor,
	 *         which is up (true) or down (false).
	 */
	public boolean isAdminStateUp();
}
