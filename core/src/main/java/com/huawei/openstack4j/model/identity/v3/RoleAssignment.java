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
import com.huawei.openstack4j.model.identity.v3.builder.RoleBuilder;

/**
 * identity v3 role assignment class
 * 
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#roles-v3">API reference</a>
 */
public interface RoleAssignment extends ModelEntity {

    /**
     * Get role id for role assignment
     * 
     * @return the id of the role
     */
    String getRoleId();

    /**
     * Get user id for role assignment
     *
     * @return the id of the user
     */
    String getUserId();

    /**
     * Get group id for role assignment
     *
     * @return the id of the group
     */
    String getGroupId();

    /**
     * Get scoped domain id for role assignment
     *
     * @return the id of the domain
     */
    String getDomainId();

    /**
     * Get scoped project id for role assignment
     *
     * @return the id of the project
     */
    String getProjectId();

    /**
     * @return the links of the role assignment
     */
    Map<String, String> getLinks();

}
