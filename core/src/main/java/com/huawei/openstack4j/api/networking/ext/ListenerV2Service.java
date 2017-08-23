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
import com.huawei.openstack4j.model.network.ext.ListenerV2;
import com.huawei.openstack4j.model.network.ext.ListenerV2Update;

/**
 * Networking (Neutron) Lbaas V2 listener Extension API
 * @author emjburns
 */
public interface ListenerV2Service extends RestService {
    /**
     * List all listeners that the current tenant has access to
     * @return list of all listeners
     */
    List<? extends ListenerV2> list();

    /**
     * Returns list of listeners filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return List
     */
    List<? extends ListenerV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified listener by ID
     *
     * @param listenerId
     * @return the listener or null if not found
     */
    ListenerV2 get(String listenerId);

    /**
     * Delete the specified listener by ID
     *
     * @param listenerId
     * @return the action response
     */
    ActionResponse delete(String listenerId);

    /**
     * Create a listener
     *
     * @param listener
     * @return ListenerV2
     */
    ListenerV2 create(ListenerV2 listener);

    /**
     * Update a listener
     *
     * @param listenerId
     * @param listener
     * @return ListenerV2
     */
    ListenerV2 update(String listenerId, ListenerV2Update listener);
}
