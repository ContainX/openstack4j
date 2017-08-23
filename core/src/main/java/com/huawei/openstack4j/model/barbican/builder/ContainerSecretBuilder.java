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
package com.huawei.openstack4j.model.barbican.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.barbican.ContainerSecret;

public interface ContainerSecretBuilder extends Buildable.Builder<ContainerSecretBuilder, ContainerSecret> {
    /**
     * @param name Human readable name for identifying your secret within the container.
     * @return
     */
    ContainerSecretBuilder name(String name);

    /**
     * @param type Full URI reference to an existing secret.
     * @return
     */
    ContainerSecretBuilder reference(String type);
}
