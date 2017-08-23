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

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.storage.block.builder.VolumeTypeBuilder;

/**
 * The volume type defines the characteristics of a volume. It usually maps to a set of capabilities
 * of the storage back-end driver to be used for this volume. 
 * Examples: "Performance", "SSD", "Backup", etc. 
 * 
 * @author Jeremy Unruh
 */
public interface VolumeType extends ModelEntity, Buildable<VolumeTypeBuilder> {

	/**
	 * @return the identifier for the volume type
	 */
	String getId();
	
	/**
	 * @return the name of the volume type
	 */
	String getName();
	
	/**
	 * @return the extra specifications (meta-data) associated with the volume type
	 */
	Map<String, String> getExtraSpecs();
	
	
}
