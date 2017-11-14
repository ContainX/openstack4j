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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.Image;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of Image
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
@JsonRootName("image")
public class MapReduceImage implements Image {

	private static final long serialVersionUID = 1L;

	private Status status;
        private String username;
	private Date updated;
	@JsonProperty("OS-EXT-IMG-SIZE:size")
	private long size;
	private String name;
	private Date created;
	private List<String> tags;
	private int minDisk;
	private int progress;
	private int minRam;
	@JsonProperty("metadata")
	private Map<String, Object> metadata;
        private String id;
        private String description;

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
	public String getUsername() {
		return username;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdated() {
		return updated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getSize() {
		return size;
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
	public Date getCreated() {
		return created;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getTags() {
		return tags;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinDisk() {
		return minDisk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getProgress() {
		return progress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinRam() {
		return minRam;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getMetaData() {
		return metadata;
	}

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
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
						.add("id", id).add("name", name).add("status", status).add("description", description).add("username", username)
						.add("progress", progress).add("size", size).add("minRam", minRam)
						.add("minDisk", minDisk).add("created", created).add("updated", updated)
						.add("metadata", metadata).add("tags", tags).addValue("\n")
						.toString();
	}

	public static class MapReduceImages extends ListResult<MapReduceImage> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("images")
		private List<MapReduceImage> images;

		public List<MapReduceImage> value() {
			return images;
		}

	}
}
