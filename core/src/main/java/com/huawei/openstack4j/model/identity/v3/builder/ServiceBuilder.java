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
import com.huawei.openstack4j.model.identity.v3.Service;

/**
 * A Builder which creates an identity v3 service
 *
 *
 */
public interface ServiceBuilder extends Builder<ServiceBuilder, Service> {

    /**
     * @see Service#getId()
     */
    ServiceBuilder id(String id);

    /**
     * @see Service#getDescription()
     */
    ServiceBuilder description(String description);

    /**
     * @see Service#getType()
     */
    ServiceBuilder type(String type);

    /**
     * @see Service#getName()
     */
    ServiceBuilder name(String name);

    /**
     * @see Service#getLinks()
     */
    ServiceBuilder links(Map<String, String> links);

    /**
     * @see Service#getVersion()
     */
    ServiceBuilder version(Integer version);

    /**
     * @see Service#isEnabled()
     */
    ServiceBuilder enabled(boolean enabled);

}
