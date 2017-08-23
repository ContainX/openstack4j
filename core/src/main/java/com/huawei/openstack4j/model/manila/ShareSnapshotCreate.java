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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.manila.builder.ShareSnapshotCreateBuilder;

/**
 * Object used to create new share snapshots
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareSnapshotCreate extends ModelEntity, Buildable<ShareSnapshotCreateBuilder> {
    /**
     * @return the UUID of the share from which to create a snapshot
     */
    String getShareId();

    /**
     * @return whether snapshot creation is enabled when a share is busy
     */
    Boolean getForce();

    /**
     * @return the snapshot name
     */
    String getName();

    /**
     * @return the snapshot name
     */
    String getDisplayName();

    /**
     * @return the snapshot description
     */
    String getDescription();

    /**
     * @return the snapshot description
     */
    String getDisplayDescription();
}
