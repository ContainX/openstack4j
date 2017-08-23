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
package com.huawei.openstack4j.api.networking;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.Subnet;

/**
 * OpenStack (Neutron) Subnet based Operations
 * 
 * @author Jeremy Unruh
 */
public interface SubnetService extends RestService {

    /**
     * List all the Subnet(s) which are authorized by the current Tenant
     *
     * @return the list of subnets or empty
     */
    List<? extends Subnet> list();

    /**
     * Gets a Subnet by ID
     * 
     * @param subnetId the subnet identifier
     * @return the Subnet or null if not found
     */
    Subnet get(String subnetId);

    /**
     * Delete a Subnet by ID
     * 
     * @param subnetId the subnet identifier to delete
     * @return the action response
     */
    ActionResponse delete(String subnetId);

    /**
     * Creates a new Subnet
     * 
     * @param subnet the subnet to create
     * @return the newly created subnet
     */
    Subnet create(Subnet subnet);

    /**
     * Updates a Subnet. 
     * <p>
     * Example:<br>
     * Subnet updateSN = update(existingSubnet.toBuilder().someChange(change).build());
     * 
     * @param subnet the subnet to update
     * @return the new subnet info
     */
    Subnet update(Subnet subnet);
    
    /**
     * This method is needed if you are updating a subnet without pre-fetching the subnet prior.
     * See example at {@linkplain #update(Subnet)}
     * 
     * @param subnetId the subnet identifier to update
     * @param subnet the subnet to update
     * @return the updated subnet
     */
    Subnet update(String subnetId, Subnet subnet);

}
