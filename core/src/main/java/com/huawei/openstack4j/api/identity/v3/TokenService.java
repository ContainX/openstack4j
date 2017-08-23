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
package com.huawei.openstack4j.api.identity.v3;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.model.identity.v3.Project;
import com.huawei.openstack4j.model.identity.v3.Service;
import com.huawei.openstack4j.model.identity.v3.Token;

/**
 * Identity V3 Token operations
 *
 */
public interface TokenService extends RestService {
    
    /***
     * Validates and shows information for a token.
     * 
     * @param tokenId the identifier of the token that is subject to be checked
     * @return the token if valid
     */
    Token get(String tokenId);
    
    /**
     * Validates a token.
     * 
     * @param tokenId the identifier of the token that is subject to be checked
     * @return the ActionResponse
     */
    ActionResponse check(String tokenId);
    
    /**
     * Revokes a token.
     * 
     * @param tokenId the identifier of the token that is going to be deleted
     * @return the ActionResponse
     */
    ActionResponse delete(String tokenId);

    /**
     * Get service catalog for specified token
     *
     * @param tokenId the identifier of the token, of which the catalog of services is requested
     * @return the service catalog for the token provided in the request
     */
    List<? extends Service> getServiceCatalog(String tokenId);

    /**
     * Get available project scopes for specified token
     *
     * @param tokenId the identifier of the token in question
     * @return list of projects that are available to be scoped to
     */
    List<? extends Project> getProjectScopes(String tokenId);

    /**
     * Get available domain scopes for specified token
     *  @param tokenId the identifier of the token in question
     *  @return list of domains that are available to be scoped to
     */
    List<? extends Domain> getDomainScopes(String tokenId);


}
