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

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import org.openstack4j.model.storage.block.VolumeBackup;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.ToString;

@ToString
@JsonRootName("backup")
public class CinderVolumeBackup  implements  VolumeBackup{
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String name;
	@Nullable
	private Status status;
	private String description;
	private int size;
	private String container;
 
	@JsonProperty("volume_id")
	private String volumeId;
	
	@JsonProperty("availability_zone")
	@Nullable
	private String zone;
	
	@JsonProperty("created_at")
	private Date created;
	
	@JsonProperty("fail_reason")
	private String failReason;
	
	@JsonProperty("object_count")
	private int objectCount;
	
	@JsonProperty("has_dependent_backups")
	@Nullable
	private Boolean hasDependent;
	
	@JsonProperty("is_incremental")
	@Nullable
	private Boolean incremental;

	@JsonProperty("snapshot_id")
	@Nullable
	private String snapshotId;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
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
	public String getVolumeId() {
		return volumeId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getZone() {
		return zone;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isIncremental(){
		return incremental;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreated() {
		return created;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContainer() { 
		return container;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFailReason() {
		return failReason;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getObjectCount() {
		return objectCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean hasDependent() {
		return hasDependent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSnapshotId() {
		return snapshotId;
	}


	public static class VolumeBackups extends ListResult<CinderVolumeBackup> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("backups")
		private List<CinderVolumeBackup> backups;

		@Override
		protected List<CinderVolumeBackup> value() {
			return backups;
		}
	}


}
