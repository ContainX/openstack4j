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
package com.huawei.openstack4j.api.manila;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.ShareNetwork;
import com.huawei.openstack4j.model.manila.ShareNetworkCreate;
import com.huawei.openstack4j.model.manila.ShareNetworkUpdateOptions;
import com.huawei.openstack4j.model.manila.builder.ShareNetworkCreateBuilder;

/**
 * Share Networks Service for Manila Shared File Systems.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareNetworkService extends RestService {

    /**
     * Creates a share network.
     *
     * @param shareNetworkCreate the share network to create
     * @return the created share network
     */
    ShareNetwork create(ShareNetworkCreate shareNetworkCreate);

    /**
     * Lists all share networks.
     *
     * @return list of all share networks
     */
    List<? extends ShareNetwork> list();

    /**
     * Lists all share networks with details.
     *
     * @return list of all share networks with details
     */
    List<? extends ShareNetwork> listDetails();

    /**
     * Shows details for a share network.
     *
     * @param shareNetworkId the share network ID
     * @return the share network or null if not found
     */
    ShareNetwork get(String shareNetworkId);

    /**
     * Updates a share network.
     *
     * @param shareNetworkId the share network ID
     * @param shareNetworkUpdateOptions the options to update on the share network
     * @return the updated share network
     */
    ShareNetwork update(String shareNetworkId, ShareNetworkUpdateOptions shareNetworkUpdateOptions);

    /**
     * Deletes a share network.
     *
     * @param shareNetworkId the share network ID
     * @return the action response
     */
    ActionResponse delete(String shareNetworkId);

    /**
     * Adds a security service to a share network.
     *
     * @param shareNetworkId the share network ID
     * @param securityServiceId the security service ID
     * @return the share network the security service was added to
     */
    ShareNetwork addSecurityService(String shareNetworkId, String securityServiceId);

    /**
     * Removes a security service from a share network.
     *
     * @param shareNetworkId the share network ID
     * @param securityServiceId the security service ID
     * @return the share network the security service was removed from
     */
    ShareNetwork removeSecurityService(String shareNetworkId, String securityServiceId);

    /**
     * @return a builder to create a share network
     */
    ShareNetworkCreateBuilder shareNetworkCreateBuilder();
}
