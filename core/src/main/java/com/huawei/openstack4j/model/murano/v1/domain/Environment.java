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
package com.huawei.openstack4j.model.murano.v1.domain;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.murano.v1.builder.EnvironmentBuilder;

public interface Environment extends ModelEntity, Buildable<EnvironmentBuilder> {
    /**
     * @return the id of the environment
     */
    String getId();

    /**
     * @return the services of the environment
     */
    List<? extends Application> getServices();

    /**
     * @return the status of the environment
     */
    String getStatus();

    /**
     * @return the updated date of the environment
     */
    String getUpdated();

    /**
     * @return the name of the environment
     */
    String getName();

    /**
     * @return the created date of the environment
     */
    String getCreated();

    /**
     * @return the tenant id of the environment
     */
    String getTenantId();

    /**
     * @return the version of the environment
     */
    String getVersion();
}
