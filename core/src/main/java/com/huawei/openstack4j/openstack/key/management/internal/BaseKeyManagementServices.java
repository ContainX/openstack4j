/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC                              
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
package com.huawei.openstack4j.openstack.key.management.internal;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * 
 *
 * @author QianBiao.NG
 * @date   2017-07-13 09:21:47
 */
public class BaseKeyManagementServices extends BaseOpenStackService {

	public static String CONTENT_JSON = "application/json;charset=utf-8";

	protected BaseKeyManagementServices() {
		super(ServiceType.KEY_MANAGEMENT);
	}

}
