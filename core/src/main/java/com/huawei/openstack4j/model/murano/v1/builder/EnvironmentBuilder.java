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
package com.huawei.openstack4j.model.murano.v1.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.murano.v1.domain.Environment;

/**
 * Builder interface used for {@link Environment} object.
 * For mapping from object builder to JSON request
 *
 */
public interface EnvironmentBuilder extends Builder<EnvironmentBuilder, Environment> {

    //
    // Define setter apis for user to create a new environment
    //

    /**
     * See {@link Environment#getName()}
     *
     * @param name the name of the environment
     * @return EnvironmentBuilder
     */
    EnvironmentBuilder name(String name);
}
