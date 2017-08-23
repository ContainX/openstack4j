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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Link;

import java.util.List;

/**
 * A share snapshot is a point-in-time, read-only copy of the data that is contained in a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareSnapshot extends ModelEntity {
    enum Status {
        AVAILABLE, ERROR, CREATING, DELETING, ERROR_DELETING;

        @JsonCreator
        public static Status value(String v) {
            return valueOf(v.toUpperCase());
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * @return the UUID of the snapshot
     */
    String getId();

    /**
     * @return the snapshot status
     */
    Status getStatus();

    /**
     * @return the UUID of the source share that was used to create the snapshot
     */
    String getShareId();

    /**
     * @return the snapshot name
     */
    String getName();

    /**
     * @return The share snapshot links
     */
    List<? extends Link> getLinks();

    /**
     * @return the snapshot description
     */
    String getDescription();

    /**
     * @return the date and time stamp when the snapshot was created
     */
    String getCreatedAt();

    /**
     * @return the file system protocol of a share snapsho
     */
    Share.Protocol getShareProto();

    /**
     * @return the size of a source share, in GBs
     */
    Integer getShareSize();

    /**
     * @return the snapshot size, in GBs
     */
    Integer getSize();
}
