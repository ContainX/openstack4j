/*******************************************************************************
 *  Copyright 2017 Huawei TLD                                        
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupCreate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 08:54:29
 */
@Getter
@ToString
@Builder(toBuilder = true)
@JsonRootName("backup")
public class VBSVolumeBackupCreate implements AsyncVolumeBackupCreate {

	private static final long serialVersionUID = 5031528431685550961L;

	@JsonProperty("name")
	String name;

	@JsonProperty("description")
	String description;

	@JsonProperty("volume_id")
	String volumeId;
	
	@JsonProperty("snapshot_id")
	String snapshotId;

}
