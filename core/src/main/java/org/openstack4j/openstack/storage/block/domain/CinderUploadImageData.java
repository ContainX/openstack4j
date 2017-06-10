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
package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.ContainerFormat;
import org.openstack4j.model.image.DiskFormat;
import org.openstack4j.model.storage.block.options.UploadImageData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * REST Entity for uploading a Volume to the Image Service
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("os-volume_upload_image")
public class CinderUploadImageData implements ModelEntity {

    private static final long serialVersionUID = 1L;
    @JsonProperty("image_name")
    private String imageName;
    @JsonProperty("container_format")
    private ContainerFormat containerFormat;
    @JsonProperty("disk_format")
    private DiskFormat diskFormat;
    @JsonProperty("force")
    private boolean force;
    
    public CinderUploadImageData() {
    }
    
    public static CinderUploadImageData create(UploadImageData data) {
        CinderUploadImageData ret = new CinderUploadImageData();
        ret.imageName = data.getImageName();
        ret.containerFormat = data.getContainerFormat();
        ret.diskFormat = data.getDiskFormat();
        ret.force = data.isForce();
        return ret;
    }
    
}
