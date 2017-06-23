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
package org.openstack4j.openstack.loadbalance.internal;

import org.openstack4j.api.loadbalance.ELBQuotaService;
import org.openstack4j.model.loadbalance.Quotas;
import org.openstack4j.openstack.loadbalance.domain.ELBQuotas;

public class ELBQuotaServiceImpl extends BaseELBServices implements ELBQuotaService {

	@Override
	public Quotas list() {
		return get(ELBQuotas.class, uri("/elbaas/quotas")).execute();
	}
}
