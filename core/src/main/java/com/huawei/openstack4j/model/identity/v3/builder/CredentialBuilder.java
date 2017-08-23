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
package com.huawei.openstack4j.model.identity.v3.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v3.Credential;

public interface CredentialBuilder extends Builder<CredentialBuilder, Credential>{

    /**
     * @see Credential#getId()
     */
    CredentialBuilder id(String id);

    /**
     * @see Credential#getUserId()
     */
    CredentialBuilder userId(String userId);

    /**
     * @see Credential#getProjectId()
     */
    CredentialBuilder projectId(String projectId);

    /**
     * @see Credential#getType()
     */
    CredentialBuilder type(String type);

    /**
     * @see Credential#getBlob()
     */
    CredentialBuilder blob(String blob);

    /**
     * @see Credential#getLinks()
     */
    CredentialBuilder links(Map<String, String> links);

}
