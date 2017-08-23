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
package com.huawei.openstack4j.model.magnum;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.magnum.MagnumEnvironment;


public interface ContainerBuilder extends Builder<ContainerBuilder, Container> {
    /**
     * @see Container#getStatus
     */
    ContainerBuilder status(String status);

    /**
     * @see Container#getUuid
     */
    ContainerBuilder uuid(String uuid);

    /**
     * @see Container#getLinks
     */
    ContainerBuilder links(List<GenericLink> links);

    /**
     * @see Container#getImage
     */
    ContainerBuilder image(String image);

    /**
     * @see Container#getEnvironment
     */
    ContainerBuilder environment(MagnumEnvironment environment);

    /**
     * @see Container#getCommand
     */
    ContainerBuilder command(String command);

    /**
     * @see Container#getMemory
     */
    ContainerBuilder memory(String memory);

    /**
     * @see Container#getBayUuid
     */
    ContainerBuilder bayUuid(String bayUuid);

    /**
     * @see Container#getName
     */
    ContainerBuilder name(String name);

}
