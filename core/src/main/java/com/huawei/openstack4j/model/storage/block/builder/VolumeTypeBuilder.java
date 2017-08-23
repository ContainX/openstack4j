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
package com.huawei.openstack4j.model.storage.block.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.storage.block.VolumeType;

public interface VolumeTypeBuilder extends Builder<VolumeTypeBuilder, VolumeType> {

	/**
	 * See {@link VolumeType#getName()}
	 * 
	 * @param name the name of the volume type
	 * @return VolumeTypeBuilder
	 */
	VolumeTypeBuilder name(String name);

	/**
	 * See {@link VolumeType#getExtraSpecs()} <b>Optional</b>
	 * 
	 * @param extraSpecs Defining extra specs for the volume type as a key-value map.
	 * @return VolumeTypeBuilder
	 */
	VolumeTypeBuilder extraSpecs(Map<String, String> extraSpecs);
}
