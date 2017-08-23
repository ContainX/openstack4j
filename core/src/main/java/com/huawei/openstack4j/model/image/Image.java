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
package com.huawei.openstack4j.model.image;

import java.util.Date;
import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.BasicResource;
import com.huawei.openstack4j.model.image.builder.ImageBuilder;

/**
 * A Glance v1.1 Image
 * 
 * @author Jeremy Unruh
 * @see http://docs.openstack.org/api/openstack-image-service/1.1/content/index.html
 */
public interface Image extends BasicResource, Buildable<ImageBuilder> {

    public enum Status {
        UNRECOGNIZED, ACTIVE, SAVING, QUEUED, KILLED, PENDING_DELETE, DELETED;

        @JsonCreator
        public static Status value(String v) 
        {
            if (v == null) return UNRECOGNIZED;
            try {
                return valueOf(v.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNRECOGNIZED;
            }
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * @return the ContainerFormat for the image
     */
    ContainerFormat getContainerFormat();

    /**
     * @return the DiskFormat for the image
     */
    DiskFormat getDiskFormat();

    Long getSize();

    /**
     * @return MD5 checksum of the image file data
     */
    String getChecksum();

    /**
     * @return the minimum disk space required in MB. 0 indicates not set
     */
    long getMinDisk();

    /**
     * @return minimum ram required in MB.  0 indicates not set
     */
    long getMinRam();

    /**
     * @return the URI of the image entry
     */
    String getLocation();

    /**
     * @return the owner of the image or null
     */
    @Nullable
    String getOwner();

    /**
     * Timestamp when an image's metadata was last updated, not its image data, as all image data is immutable once stored in Glance
     * 
     * @return the last updated date/time
     */
    Date getUpdatedAt();

    /**
     * The timestamp of when the image was created
     * 
     * @return the created date/time
     */
    Date getCreatedAt();

    /**
     * @return the date/time the image was deleted or null
     */
    @Nullable
    Date getDeletedAt();

    /**
     * @return the current operational status of the image
     */
    Status getStatus();

    /**
     * Indicates  whether the image is publicly available
     * @return true if the image is publicly available
     */
    boolean isPublic();

    /**
     * @return true if the image has been deleted
     */
    boolean isDeleted();

    /**
     * @return true if the image is protected
     */
    boolean isProtected();

    /**
     * A mapping of free-form key/value pairs that have been saved with the image metadata
     * @return Map of key to value
     */
    Map<String, String> getProperties();

    /**
     * This will always be null on any list or get request.  It is intented as an option for creating, updating or reserving images
     * @return the store type
     */
    StoreType getStoreType();

    /**
     * Determines if this image is a snapshot
     * 
     * @return true if this image is a snapshot
     */
    boolean isSnapshot();

    /**
     *
     * @return the image url as String
     */
    String getCopyFrom();

}
