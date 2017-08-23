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
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.model.identity.v3.Project;

/**
 * A Builder which creates a identity v3 project
 * 
 * 
 */
public interface ProjectBuilder extends Builder<ProjectBuilder, Project> {

    /**
     * @see Project#getId()
     */
    ProjectBuilder id(String id);

    /**
     * 
     * @see Project#getDomainId()
     */
    ProjectBuilder domainId(String domainId);

    /**
     * Accepts an existing domain and uses its id
     * 
     * @see Project#getDomainId()
     */
    ProjectBuilder domain(Domain domain);

    /**
     * 
     * @see Project#getDescription
     */
    ProjectBuilder description(String description);

    /**
     * @see Project#isEnabled()
     */
    ProjectBuilder enabled(boolean enabled);

    /**
     * @see Project#getName()
     */
    ProjectBuilder name(String name);

    /**
     * 
     * @see Project#getLinks()
     */
    ProjectBuilder links(Map<String, String> links);

    /**
     * 
     * @see Project#getParentId()
     */
    ProjectBuilder parentId(String parentId);

    /**
     * 
     * @see Project#getSubtree()
     */
    ProjectBuilder subtree(String subtree);

    /**
     * 
     * @see Project#getParents()
     */
    ProjectBuilder parents(String parents);

    /**
     *
     * @see Project#getExtra(String)
     */
    ProjectBuilder setExtra(String name, String value);
}
