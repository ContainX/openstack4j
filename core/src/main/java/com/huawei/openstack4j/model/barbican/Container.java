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
package com.huawei.openstack4j.model.barbican;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.barbican.builder.ContainerCreateBuilder;

/**
 * Project storage unit for secrets.
 */
public interface Container extends ModelEntity, Buildable<ContainerCreateBuilder> {

    /**
     * @return current status of the container.
     */
    String getStatus();

    /**
     * @return container type (generic, rsa, certificate).
     */
    String getType();

    /**
     * @return name of the container.
     */
    String getName();

    /**
     * @return system generated last update time.
     */
    Date getUpdatedTime();

    /**
     * @return system generated creation time.
     */
    Date getCreatedTime();

    /**
     * @return URL reference to the container.
     */
    String getContainerReference();

    /**
     * @return user uuid of the creator of this container.
     */
    String getCreatorId();

    /**
     * @return current consumers of this container.
     */
    List<? extends ContainerConsumer> getConsumers();

    /**
     * @return associated secrets.
     */
    List<? extends ContainerSecret> getSecretReferences();
}
