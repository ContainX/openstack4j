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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.Vip;
import com.huawei.openstack4j.model.network.ext.VipUpdate;
/**
 * Networking (Neutron) Lbaas vip Extension API
 * @author liujunpeng
 *
 */
public interface VipService extends RestService {
    /**
     * List all vipss  that the current tenant has access to
     *
     * @return list of all vip
     */
    List<? extends Vip> list();

    /**
     * Returns list of vip filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of vip fitered by filteringParams
     */
    List<? extends Vip> list(Map<String, String> filteringParams);


    /**
     * Get the specified vip by ID
     *
     * @param vipId the vip identifier
     * @return the vip or null if not found
     */
    Vip get(String vipId);
    
    /**
     * Delete the specified vip by ID
     * @param vipId the vip identifier
     * @return the action response
     */
    ActionResponse delete(String vipId);
    /**
     * Create a vip
     * @param vip vip
     * @return Vip
     */
    Vip create(Vip vip);
    /**
     * Update a vip
     * @param vipId the vip identifier
     * @param vip VipUpdate
     * @return Vip
     */
    Vip update(String vipId,VipUpdate vip);
}
