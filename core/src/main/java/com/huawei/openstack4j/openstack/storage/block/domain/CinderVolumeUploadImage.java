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
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.image.ContainerFormat;
import com.huawei.openstack4j.model.image.DiskFormat;
import com.huawei.openstack4j.model.storage.block.VolumeUploadImage;
import com.huawei.openstack4j.model.storage.block.Volume.Status;

import com.google.common.base.MoreObjects;

/**
 * Represents an action state when uploading a volume to the image service
 *
 * @author Jeremy Unruh
 */
@JsonRootName("os-volume_upload_image")
public class CinderVolumeUploadImage implements VolumeUploadImage {

    private static final long serialVersionUID = 1L;

    private String id;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("display_description")
    private String displayDescription;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("image_id")
    private String imageId;
    @JsonProperty("image_name")
    private String imageName;
    private ContainerFormat containerFormat;
    @JsonProperty("disk_format")
    private DiskFormat diskFormat;
    private int size;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayDescription() {
        return displayDescription;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public ContainerFormat getContainerFormat() {
        return containerFormat;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public DiskFormat getDiskFormat() {
        return diskFormat;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                 .add("id", id).add("status", status).add("display_description", displayDescription)
                 .add("updatedAt", updatedAt).add("image_id", imageId).add("image_name", imageName)
                 .add("container_format", containerFormat).add("disk_format", diskFormat)
                 .toString();
    }
}
