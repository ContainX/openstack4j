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
package com.huawei.openstack4j.model.manila.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.manila.Share;
import com.huawei.openstack4j.model.manila.ShareCreate;

/**
 * Builds a {@link com.huawei.openstack4j.model.manila.ShareCreate}.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareCreateBuilder extends Buildable.Builder<ShareCreateBuilder, ShareCreate> {
    /**
     * Set the Shared File Systems protocol.
     *
     * @param shareProto the Shared File Systems protocol
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder shareProto(Share.Protocol shareProto);

    /**
     * Set the share size, in GBs.
     *
     * @param size the share size
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder size(Integer size);

    /**
     * Set the share name.
     *
     * @param name  the share name
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder name(String name);

    /**
     * Set the share description.
     *
     * @param description the share description
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder description(String description);

    /**
     * Set the share name.
     *
     * @param displayName the share name
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder displayName(String displayName);

    /**
     * Set the share description.
     *
     * @param displayDescription the share description
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder displayDescription(String displayDescription);

    /**
     * Set the UUID of the share type.
     *
     * @param shareType the share type
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder shareType(String shareType);

    /**
     * Set the volume type.
     *
     * @param volumeType the volume type
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder volumeType(String volumeType);

    /**
     * Set the UUID of the snapshot from which to create the share.
     *
     * @param snapshotId the snapshot ID
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder snapshotId(String snapshotId);

    /**
     * Set the level of visibility for the share.
     *
     * @param isPublic the level of visibility for the share
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder isPublic(Boolean isPublic);

    /**
     * Adds a new metadata item to the share.
     * 
     * @param key the key of the metadata item
     * @param value the value of the metadata item
     * ShareCreateBuilder
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder addMetadataItem(String key, String value);

    /**
     * One or more metadata key and value pairs as a dictionary of strings.
     *
     * @param metadata the share metadata
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder metadata(Map<String, String> metadata);

    /**
     * Set the UUID of the share network.
     *
     * @param shareNetworkId the share network ID
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder shareNetworkId(String shareNetworkId);

    /**
     * Set the UUID of the consistency group where the share was created.
     *
     * @param consistencyGroupId the consistency group ID
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder consistencyGroupId(String consistencyGroupId);

    /**
     * Set the availability zone.
     *
     * @param availabilityZone the availability zone
     * @return ShareCreateBuilder
     */
    ShareCreateBuilder availabilityZone(String availabilityZone);
}
