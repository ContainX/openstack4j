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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;

public interface Cluster extends ModelEntity, Buildable<ClusterBuilder> {
    /**
     * Gets status
     * 
     * @return status
     */
    String getStatus();

    /**
     * Gets clusterTemplateId
     * 
     * @return clusterTemplateId
     */
    String getClusterTemplateId();

    /**
     * Gets uuid
     * 
     * @return uuid
     */
    String getUuid();

    /**
     * Gets links
     * 
     * @return links
     */
    List<GenericLink> getLinks();

    /**
     * Gets stackId
     * 
     * @return stackId
     */
    String getStackId();

    /**
     * Gets masterCount
     * 
     * @return masterCount
     */
    Integer getMasterCount();

    /**
     * Gets createTimeout
     * 
     * @return createTimeout
     */
    Integer getCreateTimeout();

    /**
     * Gets nodeCount
     * 
     * @return nodeCount
     */
    Integer getNodeCount();

    /**
     * Gets discoveryUrl
     * 
     * @return discoveryUrl
     */
    String getDiscoveryUrl();

    /**
     * Gets keypair
     * 
     * @return keypair
     */
    String getKeypair();

    /**
     * Gets name
     * 
     * @return name
     */
    String getName();

}
