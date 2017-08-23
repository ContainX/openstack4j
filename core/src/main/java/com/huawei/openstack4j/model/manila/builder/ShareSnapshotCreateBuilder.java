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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.manila.ShareSnapshotCreate;

/**
 * Builds a {@link com.huawei.openstack4j.model.manila.ShareSnapshotCreate}.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareSnapshotCreateBuilder extends Buildable.Builder<ShareSnapshotCreateBuilder, ShareSnapshotCreate> {
    /**
     * Set the UUID of the share from which to create a snapshot.
     *
     * @param shareId the share ID
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder shareId(String shareId);

    /**
     * Set whether snapshot creation is enabled when a share is busy.
     *
     * @param force whether snapshot creation is forced
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder force(boolean force);

    /**
     * Set the snapshot name.
     *
     * @param name the snapshot name
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder name(String name);

    /**
     * Set the snapshot name.
     *
     * The Shared File Systems API supports the use of both <code>name</code> and <code>display_name</code> attributes,
     * which are inherited attributes from the Block Storage API.
     *
     * @param displayName the snapshot name
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder displayName(String displayName);

    /**
     * Set the snapshot description.
     *
     * @param description the snapshot description
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder description(String description);

    /**
     * Set the snapshot description.
     *
     * The Shared File Systems API supports the use of both <code>description</code> and
     * <code>display_description</code> parameters, which are inherited attributes from the Block Storage API.
     *
     * @param displayDescription the snapshot description
     * @return ShareSnapshotCreateBuilder
     */
    ShareSnapshotCreateBuilder displayDescription(String displayDescription);
}
