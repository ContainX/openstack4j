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
package com.huawei.openstack4j.model.image.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.image.ContainerFormat;
import com.huawei.openstack4j.model.image.DiskFormat;
import com.huawei.openstack4j.model.image.Image;
import com.huawei.openstack4j.model.image.StoreType;

/**
 * Builder which creates an Image
 * 
 * @author Jeremy Unruh
 */
public interface ImageBuilder extends Builder<ImageBuilder, Image>{

	/**
	 * @see Image#getId()
	 */
	ImageBuilder id(String id);
	
	/**
	 * @see Image#getName()
	 */
	ImageBuilder name(String name);
	
	/**
	 * @see Image#getDiskFormat()
	 */
	ImageBuilder diskFormat(DiskFormat diskFormat);
	
	/**
	 * @see Image#getContainerFormat()
	 */
	ImageBuilder containerFormat(ContainerFormat containerFormat);
	
	/**
	 * @see Image#getSize()
	 */
	ImageBuilder size(Long size);

	/**
	 * @see Image#getChecksum()
	 */
	ImageBuilder checksum(String checksum);
	
	/**
	 * @see Image#getMinDisk()
	 */
	ImageBuilder minDisk(Long minDisk);

	/**
	 * @see Image#getMinRam()
	 */
	ImageBuilder minRam(Long minRam);
	
	/**
	 * @see Image#getOwner()
	 */
	ImageBuilder owner(String owner);

	/**
	 * @see Image#isPublic()
	 */
	ImageBuilder isPublic(Boolean isPublic);

	/**
	 * @see Image#getProperties()
	 */
	ImageBuilder properties(Map<String, String> properties);
	
	/**
	 * @see Image#getProperties()
	 */
	ImageBuilder property(String key, String value);
	
	/**
	 * Store type to be used during create or reserving of new images
	 * 
	 * @param storeType the store type
	 * @return the image builder
	 */
	ImageBuilder storeType(StoreType storeType);


    /**
     *
     * @see Image#getCopyFrom()
     */
    ImageBuilder copyFrom(String copyFrom);
	
}
