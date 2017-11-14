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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * This class is extended by every MapReduce Service. It is necessary to determine
 * the correct endpoint (url) for MapReduce calls.
 * 
 * @author Ekasit Kijipongse
 * 
 */
public class BaseMapReduceServices extends BaseOpenStackService {
	
	public static String CONTENT_JSON = "application/json;charset=utf-8";

	protected BaseMapReduceServices() {
		super(ServiceType.MAP_REDUCE);
	}
	
	
	/**
	 * HuaWei MapReduceService(known as sahara) validate the content-type in every request
	 */
	protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		return super.builder(returnType, path, method).header("Content-Type", CONTENT_JSON);
	}

}
