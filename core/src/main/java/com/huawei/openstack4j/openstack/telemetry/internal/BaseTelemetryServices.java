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
package com.huawei.openstack4j.openstack.telemetry.internal;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Base class for Telemetry services
 * 
 * @author Jeremy Unruh
 */
public class BaseTelemetryServices extends BaseOpenStackService {

	protected BaseTelemetryServices() {
		super(ServiceType.TELEMETRY, EndpointFunction.instance);
	}
	
	/**
	 * Sometimes the endpoint does not contain the API version which is required.  This insures that
	 */
	private static class EndpointFunction implements Function<String, String> {

		static final EndpointFunction instance = new EndpointFunction();
		
		@Override
		public String apply(String input) {
			if (input == null || input.contains("/v"))
				return input;
			return input.concat(input.endsWith("/") ? "v2" : "/v2");
		}
	}
	
	protected <T> List<T> wrapList(T[] type) {
		if (type != null)
			return Lists.newArrayList(type);
		return Collections.emptyList();
			
	}
}
