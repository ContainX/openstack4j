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
import com.huawei.openstack4j.model.identity.v3.Policy;

/**
 *
 * the policy builder
 *
 */
public interface PolicyBuilder extends Builder<PolicyBuilder, Policy> {

    /**
     * @see Policy#getId()
     */
    PolicyBuilder id(String id);

    /**
     * @see Policy#getType()
     */
    PolicyBuilder type(String type);

    /**
     * @see Policy#getBlob()
     */
    PolicyBuilder blob(String blob);

    /**
     * @see Policy#getLinks()
     */
    PolicyBuilder links(Map<String, String> links);

    /**
     * @see Policy#getProjectId()
     */
    PolicyBuilder projectId(String projectId);

    /**
     * @see Policy#getUserId()
     */
    PolicyBuilder userId(String userId);

}
