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
package com.huawei.openstack4j.model.identity.v3;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v3.builder.CredentialBuilder;

/**
 * Domain model.
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#domains-v3">API reference</a>
 */
public interface Credential extends ModelEntity, Buildable<CredentialBuilder> {

    /**
     * @return the id of the credential
     */
    String getId();

    /**
     * @return the id of the user who owns the credential
     */
    String getUserId();

    /**
     * @return the id of the associated project
     */
    String getProjectId();

    /**
     * @return the credential type such as 'ec2', 'cert'
     */
    String getType();

    /**
     * the credential itself as serialized blob
     *
     * @return the blob the credential
     */
    String getBlob();

    /**
     * @return the links for the credential resource
     */
    Map<String, String> getLinks();

}
