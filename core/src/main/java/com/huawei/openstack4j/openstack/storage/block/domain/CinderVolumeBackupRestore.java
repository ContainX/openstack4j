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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.storage.block.VolumeBackupRestore;

@JsonRootName("restore")
public class CinderVolumeBackupRestore implements VolumeBackupRestore {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("volume_name")
	private String name;
	@JsonProperty("volume_id")
	private String volumeId;	
	
	@Nullable
	@JsonProperty("backup_id")
	private String backupId;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getVolumeId() {
		return volumeId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBackupId(){
    	return backupId;
    }

	public CinderVolumeBackupRestore(){
		
	}
	
	public CinderVolumeBackupRestore(String name, String volumeId) {
		this.name = name;
		this.volumeId = volumeId;
	}
	
	public CinderVolumeBackupRestore(  String name, String volumeId , String backupId ) {
		this.backupId = backupId;
		this.name = name;
		this.volumeId = volumeId;
	}
}
