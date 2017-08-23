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
import com.huawei.openstack4j.model.identity.v3.Group;

/**
 * A Builder which creates a identity v3 group
 * 
 * 
 */
public interface GroupBuilder extends Builder<GroupBuilder, Group> {

    /**
     * @see Group#getId()
     */
    GroupBuilder id(String id);

    /**
     * @see Group#getName()
     */
    GroupBuilder name(String name);

    /**
     * @see Group#getId()
     */
    GroupBuilder description(String description);

    /**
     * @see Group#getDomainId()
     */
    GroupBuilder domainId(String domainId);

    /**
     * @see Group#getLinks()
     */
    GroupBuilder links(Map<String, String> links);

}
