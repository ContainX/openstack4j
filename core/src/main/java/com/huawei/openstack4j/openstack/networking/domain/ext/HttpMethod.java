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
package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * HttpMethod
 * @author liujunpeng
 *
 */
public enum HttpMethod {
	HEAD(0), GET(1), POST(2), PUT(2), PATCH(4), DELETE(5), OPTIONS(6), TRACE(7);
	int code;

	private HttpMethod(int code) {
		this.code = code;
	}

	@JsonCreator
	public static HttpMethod valueOf(int value) {
		for (HttpMethod method : HttpMethod.values()) {
			if (method.code() == value) {
				return method;
			}
		}
		return GET;
	}

	@JsonValue
	public int code() {
		return code;
	}
}
