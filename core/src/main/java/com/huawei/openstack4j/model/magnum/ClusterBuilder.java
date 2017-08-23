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

public interface ClusterBuilder extends Builder<ClusterBuilder, Cluster> {
    /**
     * @see Cluster#getStatus
     */
    ClusterBuilder status(String status);

    /**
     * @see Cluster#getClusterTemplateId
     */
    ClusterBuilder clusterTemplateId(String clusterTemplateId);

    /**
     * @see Cluster#getUuid
     */
    ClusterBuilder uuid(String uuid);

    /**
     * @see Cluster#getLinks
     */
    ClusterBuilder links(List<GenericLink> links);

    /**
     * @see Cluster#getStackId
     */
    ClusterBuilder stackId(String stackId);

    /**
     * @see Cluster#getMasterCount
     */
    ClusterBuilder masterCount(Integer masterCount);

    /**
     * @see Cluster#getCreateTimeout
     */
    ClusterBuilder createTimeout(Integer createTimeout);

    /**
     * @see Cluster#getNodeCount
     */
    ClusterBuilder nodeCount(Integer nodeCount);

    /**
     * @see Cluster#getDiscoveryUrl
     */
    ClusterBuilder discoveryUrl(String discoveryUrl);

    /**
     * @see Cluster#getKeypair
     */
    ClusterBuilder keypair(String keypair);

    /**
     * @see Cluster#getName
     */
    ClusterBuilder name(String name);

}
