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
package com.huawei.openstack4j.model.storage.block;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.image.ContainerFormat;
import com.huawei.openstack4j.model.image.DiskFormat;
import com.huawei.openstack4j.model.storage.block.Volume.Status;

/**
 * Represents an action state when uploading a volume to the image service
 * 
 * @author Jeremy Unruh
 */
public interface VolumeUploadImage extends ModelEntity {

    /**
     * @return the identifier for this transactions
     */
    String getId();
    
    /**
     * @return the display description
     */
    String getDisplayDescription();

    /**
     * @return the last update date
     */
    Date getUpdatedAt();
    
    /**
     * @return the current status
     */
    Status getStatus();
    
    /**
     * @return the assigned image identifier
     */
    String getImageId();
    
    /**
     * @return the image name
     */
    String getImageName();
    
    /**
     * @return the container format
     */
    ContainerFormat getContainerFormat();
    
    /**
     * @return the overall size
     */
    int getSize();
    
    /**
     * @return the disk format
     */
    DiskFormat getDiskFormat();
    
    
}
