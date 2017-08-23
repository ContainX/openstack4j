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
import com.huawei.openstack4j.model.identity.v3.Credential;

/**
 * Identity V3 Credential service
 *
 */
public interface CredentialService extends RestService {

    /**
     * Create a new credential
     *
     * @param credential the credential
     * @return the newly created credential
     */
    Credential create(Credential credential);

    /**
     * Create a new credential
     *
     * @param blob the credential itself as serialized blob
     * @param type the credential type such as 'ec2', 'cert', ..
     * @param projectId the id of the associated project
     * @param userId the id of the user who owns the credential
     * @return
     */
    Credential create(String blob, String type, String projectId, String userId);

    /**
     * Get details for a credential
     *
     * @param credentialId the id of the credential object
     * @return the credential
     */
    Credential get(String credentialId);

    /**
     * Update credentials
     *
     * @param credential the credential set to update
     * @return the updated credential
     */
    Credential update(Credential credential);

    /**
     * Delete credential
     *
     * @param credentialId the id of the credential object
     * @return the ActionResponse
     */
    ActionResponse delete(String credentialId);

    /**
     * List all credentials
     *
     * @return list of credentials
     */
    List<? extends Credential> list();

}
