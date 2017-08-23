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

import java.net.URL;
import java.util.Map;

import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v3.Endpoint;

/**
 * A Builder which creates an identity v3 endpoint.
 *
 *
 */
public interface EndpointBuilder extends Builder<EndpointBuilder, Endpoint> {

    /**
     * @see Endpoint#getId()
     */
    EndpointBuilder id(String id);

    /**
     * @see Endpoint#getType()
     */
    EndpointBuilder type(String type);

    /**
     * @see Endpoint#getDescription()
     */
    EndpointBuilder description(String description);

    /**
     * @see Endpoint#getIface()
     */
    EndpointBuilder iface(Facing iface);

    /**
     * @see Endpoint#getServiceId()
     */
    EndpointBuilder serviceId(String serviceId);

    /**
     * @see Endpoint#getName()
     */
    EndpointBuilder name(String name);

    /**
     * @see Endpoint#getRegion()
     */
    EndpointBuilder region(String region);

    /**
     * @see Endpoint#getRegionId()
     */
    EndpointBuilder regionId(String regionId);

    /**
     * @see Endpoint#getUrl()
     */
    EndpointBuilder url(URL url);

    /**
     * @see Endpoint#getLinks()
     */
    EndpointBuilder links(Map<String, String> links);

    /**
     * @see Endpoint#isEnabled()
     */
    EndpointBuilder enabled(boolean enabled);

}
