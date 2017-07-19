/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.openstack.antiddos.internal;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.antiddos.domain.AntiDDoSWarn;
import org.openstack4j.openstack.common.functions.ReplaceVersionOfURL;

public class AntiDDoSWarnService extends BaseAntiDDoSService implements RestService {
	 
	public AntiDDoSWarnService() {
		super(ReplaceVersionOfURL.instance("/v2"));
	}
	
	public AntiDDoSWarn query() {
		return get(AntiDDoSWarn.class, uri("/warnalert/alertconfig/query")).execute();
	}
}
