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
package com.huawei.openstack4j.model.identity.v3;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v3.builder.ServiceBuilder;

/**
 * Identity V3 Service model
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#service-catalog-v3"> API reference</a>
 */
public interface Service extends ModelEntity, Buildable<ServiceBuilder>, Comparable<Service> {

    /**
     * @return the version of the service
     */
    Integer getVersion();

    /**
     * @return the id of the service
     */
    String getId();

    /**
     * @return the description of the service
     */
    String getDescription();

    /**
     * @return the name of the service
     */
    String getName();

    /**
     * @return the type of the service
     */
    String getType();

    /**
     * @return the links of the service
     */
    Map<String, String> getLinks();

    /**
     * @return the list of endpoints
     */
    List<? extends Endpoint> getEndpoints();

    /**
     * @return the enabled status of the service
     */
    boolean isEnabled();

    /**
     * sets the enabled status of the service
     *
     * @param enabled the enabled
     */
    void setEnabled(Boolean enabled);

}
