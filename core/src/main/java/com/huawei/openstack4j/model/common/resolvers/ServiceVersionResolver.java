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
package com.huawei.openstack4j.model.common.resolvers;

import java.util.SortedSet;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.identity.v2.Access;
import com.huawei.openstack4j.model.identity.v3.Service;

/**
 * Responsible for resolving a Service to a specific version.
 *
 * @author Jeremy Unruh
 */
public interface ServiceVersionResolver {

    /**
     * Resolves to a single identity v3 service for the given {@code ServiceType} and set of associated {@code services}.
     *
     * For example if a deployment contains Compute/Nova version 2 and 2.1 then resolve would be invoked with the
     * params of {@link ServiceType#COMPUTE} and a set of 2 services (Compute 2 and Compute 2.1).  The resolver is
     * responsible for picking the appropriate version
     *
     * @param type the type of {@code services}
     * @param services the sorted by version set of service(s)
     * @return a single service
     */
    Service resolveV3(ServiceType type, SortedSet<? extends  Service> services);
    
    /**
     * Resolves to a single identity v2 service for the given {@code ServiceType} and set of associated {@code services}.
     *
     * For example if a deployment contains Compute/Nova version 2 and 2.1 then resolve would be invoked with the
     * params of {@link ServiceType#COMPUTE} and a set of 2 services (Compute 2 and Compute 2.1).  The resolver is
     * responsible for picking the appropriate version
     * 
     * @param type type the type of {@code services}
     * @param services the sorted by version set of service(s)
     * @return a single service
     */
    Access.Service resolveV2(ServiceType type, SortedSet<? extends Access.Service> services);

}
