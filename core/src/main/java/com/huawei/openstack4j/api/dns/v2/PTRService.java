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
package com.huawei.openstack4j.api.dns.v2;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.dns.v2.PTR;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignatePTR;

/**
 * Designate V2 ReverseRecord Service
 *
 */
public interface PTRService extends RestService {
    /**
     * Set the PTR record for floating IP
     * @param record
     * @return
     */
	DesignatePTR setup(DesignatePTR record);

    /**
     * Query the PTR record for a single floating IP.
     * @param region
     * @param floatingIpId
     * @return
     */
	DesignatePTR get(String region, String floatingIpId);

    /**
     * Query the PTR records for all floating IP without filters.
     * @return
     */
	List<? extends PTR> list();

    /**
     * Query the PTR records for all floating IP with filters
     * @param filters
     * @return
     */
    List<? extends PTR> list(Map<String, Object> filters);

    /**
     * Restore the PTR record of floating IP to the default value.
     * @param region
     * @param floatingIpId
     * @return
     */
	ActionResponse restore(String region, String floatingIpId);


}
