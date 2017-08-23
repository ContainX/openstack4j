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
package com.huawei.openstack4j.model.manila;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.builder.ShareCreateBuilder;

/**
 * Object used to create new shares.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareCreate extends ModelEntity, Buildable<ShareCreateBuilder> {
    /**
     * @return The Shared File Systems protocol
     */
    Share.Protocol getShareProto();

    /**
     * @return The share size, in GBs
     */
    Integer getSize();

    /**
     * @return The share name
     */
    String getName();

    /**
     * @return The share description
     */
    String getDescription();

    /**
     * @return The share name
     */
    String getDisplayName();

    /**
     * @return The share description.
     */
    String getDisplayDescription();

    /**
     * @return The UUID of the share type
     */
    String getShareType();

    /**
     * @return The volume type
     */
    String getVolumeType();

    /**
     * @return The UUID of the snapshot from which to create the share
     */
    String getSnapshotId();

    /**
     * @return The level of visibility for the share
     */
    Boolean isPublic();

    /**
     * @return One or more metadata key and value pairs as a dictionary of strings
     */
    Map<String, String> getMetadata();

    /**
     * @return The UUID of the share network
     */
    String getShareNetworkId();

    /**
     * @return The UUID of the consistency group where the share was created
     */
    String getConsistencyGroupId();

    /**
     * @return The availability zone
     */
    String getAvailabilityZone();
}
