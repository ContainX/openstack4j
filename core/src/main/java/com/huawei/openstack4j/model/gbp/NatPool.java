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
package com.huawei.openstack4j.model.gbp;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.gbp.builder.NatPoolBuilder;

/**
 * Nat Pool Model Entity
 * 
 * @author vinod borole
 */
public interface NatPool extends Resource, Buildable<NatPoolBuilder> {

    /**
     * Gets the subnet Id
     *
     * @return the subnet Id
     */
    String getSubnetId();

    /**
     * Gets the Ip Version
     *
     * @return the Ip Version
     */
    String getIpVersion();

    /**
     * Gets the Ip Pool
     *
     * @return the Ip Pool
     */
    String getIpPool();

    /**
     * Gets the External segment Id
     *
     * @return the External segment Id
     */
    String getExternalSegmentId();

    /**
     * Is Nat Pool shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

}
