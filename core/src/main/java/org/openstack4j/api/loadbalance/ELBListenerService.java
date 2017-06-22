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
package org.openstack4j.api.loadbalance;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.loadbalance.Listener;
import org.openstack4j.model.loadbalance.ListenerCreate;
import org.openstack4j.model.loadbalance.ListenerUpdate;
import org.openstack4j.model.loadbalance.ListenerUpdateResp;
import org.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;

public interface ELBListenerService extends RestService {
	ListenerCreate create(ListenerCreate listener);
	
	ActionResponse delete(String listenerId);
	
	ListenerUpdateResp update(String listenerId, ListenerUpdate listener);
	
	Listener get(String listenerId);
	
	List<? extends Listener> list();
	
	List<? extends Listener> list(ELBListenerListOptions options);
}
