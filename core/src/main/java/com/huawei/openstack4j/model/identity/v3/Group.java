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

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v3.builder.GroupBuilder;

/**
 * Group model
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#groups-v3">API reference</a>
 */
public interface Group extends ModelEntity, Buildable<GroupBuilder> {

    /**
     * Globally unique within the owning domain.
     * 
     * @return the id of the group
     */
    String getId();

    /**
     * @return the name of the group
     */
    String getName();

    /**
     * @return the description of the group
     */
    String getDescription();

    /**
     * @return the domain id of the group
     */
    String getDomainId();

    /**
     * @return the links of the group
     */
    Map<String, String> getLinks();

}
