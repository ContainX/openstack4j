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
package com.huawei.openstack4j.model.image.v2.builder;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.image.v2.ContainerFormat;
import com.huawei.openstack4j.model.image.v2.DiskFormat;
import com.huawei.openstack4j.model.image.v2.Image;

/**
 * Builder which creates a v2 Image
 * @author emjburns
 */
public interface ImageBuilder extends Buildable.Builder<ImageBuilder, Image> {

    /**
     * @see Image#getName()
     */
    ImageBuilder name(String name);

    /**
     * @see Image#getId()
     */
    ImageBuilder id(String id);

    /**
     * @see Image#getVisibility()
     */
    ImageBuilder visibility(Image.ImageVisibility visibility);

    /**
     * @see Image#getTags()
     */
    ImageBuilder tags(List<String> tags);

    /**
     * @see Image#getContainerFormat()
     */
    ImageBuilder containerFormat(ContainerFormat containerFormat);

    /**
     * @see Image#getDiskFormat()
     */
    ImageBuilder diskFormat(DiskFormat diskFormat);

    /**
     * @see Image#getMinDisk()
     */
    ImageBuilder minDisk(Long minDisk);

    /**
     * @see Image#getMinRam()
     */
    ImageBuilder minRam(Long minRam);

    /**
     * @see Image#getIsProtected()
     */
    ImageBuilder isProtected(Boolean isProtected);

    /**
     * @see Image#getArchitecture()
     */
    ImageBuilder architecture(String architecture);

    /**
     * @see Image#getInstanceUuid()
     */
    ImageBuilder instanceUuid(String instanceUuid);

    /**
     * @see Image#getKernelId()
     */
    ImageBuilder kernelId(String kernelId);

    /**
     * @see Image#getOsVersion()
     */
    ImageBuilder osVersion(String osVersion);

    /**
     * @see Image#getOsDistro()
     */
    ImageBuilder osDistro(String osDistro);

    /**
     * @see Image#getRamdiskId()
     */
    ImageBuilder ramdiskId(String ramdiskId);

    /**
     * @see Image#getAdditionalPropertyValue()
     */
    ImageBuilder additionalProperty(String key, String value);
}
