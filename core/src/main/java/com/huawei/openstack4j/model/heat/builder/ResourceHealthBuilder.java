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
package com.huawei.openstack4j.model.heat.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.heat.ResourceHealth;

/**
 * This interface describes a builder for {@link ResourceHealth} objects
 *
 * @author Dan Maas
 */
public interface ResourceHealthBuilder extends Buildable.Builder<ResourceHealthBuilder, ResourceHealth> {

    /**
     * Set the unhealthy status of the resource.
     *
     * @param markUnhealthy
     * @return
     */
    ResourceHealthBuilder markUnhealthy(boolean markUnhealthy);

    /**
     * Set the resource status reason on the resource.
     *
     * @param resourceStatusReason
     * @return
     */
    ResourceHealthBuilder resourceStatusReason(String resourceStatusReason);
}
