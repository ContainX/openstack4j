/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.loadbalance;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.loadbalance.Server;
import com.huawei.openstack4j.model.loadbalance.ServerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBJob;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBServerListOptions;

public interface ELBServerService extends RestService {

	ELBJob create(String listenerId, List<ServerCreate> servers);

	ELBJob delete(String listenerId, List<String> servers);

	Server[] list(String listenerId);

	Server[] list(String listenerId, ELBServerListOptions options);
	
}
