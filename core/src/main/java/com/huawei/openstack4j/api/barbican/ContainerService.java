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
package com.huawei.openstack4j.api.barbican;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.barbican.Container;
import com.huawei.openstack4j.model.common.ActionResponse;

/**
 * Container service provides CRUD capabilities for Containers(s).
 */
public interface ContainerService {

    /**
     * Returns list of containers filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of containers filtered by filteringParams
     */
    List<? extends Container> list(final Map<String, String> filteringParams);

    /**
     * Gets a list of currently existing {@link Container}s for a specified container.
     *
     * @return the list of {@link Container}s
     */
    List<? extends Container> list(final String name);

    /**
     * Get the specified container by ID
     * @param containerId
     * @return
     */
    Container get(final String containerId);

    /**
     * Delete the specified container by ID
     * @param containerId
     * @return
     */
    ActionResponse delete(final String containerId);

    /**
     * Create a container.
     * @param container
     * @return
     */
    Container create(final Container container);
}
