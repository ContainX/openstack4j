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

/**
 * A Builder which creates a identity v3 domain.
 * 
 * 
 */
public interface DomainBuilder extends Builder<DomainBuilder, Domain> {

    /**
     * @see Domain#getId()
     */
    DomainBuilder id(String id);

    /**
     * @see Domain#getDescription()
     */
    DomainBuilder description(String description);

    /**
     * @see Domain#getName()
     */
    DomainBuilder name(String name);

    /**
     * @see Domain#getLinks()
     */
    DomainBuilder links(Map<String, String> links);

    /**
     * @see Domain#isEnabled()
     */
    DomainBuilder enabled(boolean enabled);

}
