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
package org.openstack4j.api.dns.v2;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.PTR;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR;

/**
 * Designate V2 ReverseRecord Service
 *
 */
public interface PTRService extends RestService {
	public DesignatePTR setup(DesignatePTR record);
	public DesignatePTR get(String region, String floatingIpId);
	public List<? extends PTR> list();
	public ActionResponse restore(DesignatePTR record);
}
