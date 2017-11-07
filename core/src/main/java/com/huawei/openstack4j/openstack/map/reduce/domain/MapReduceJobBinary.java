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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.map.reduce.JobBinary;
import com.huawei.openstack4j.model.map.reduce.JobBinaryCredentials;
import com.huawei.openstack4j.model.map.reduce.builder.JobBinaryBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.ToString;

/**
 * Model represent attributes of JobBinary
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@ToString
@JsonRootName("job_binary")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapReduceJobBinary implements JobBinary {

	private static final long serialVersionUID = 1L;

	@JsonProperty("description")
	private String description;
	@JsonProperty("url")
	private String url;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;
	@JsonProperty("updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("extra")
	private MapReduceJobBinaryCredentials credentials;
	@JsonProperty("is_protected")
	Boolean isProtected;
	@JsonProperty("is_public")
	Boolean isPublic;

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
	public String getURL() {
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdatedAt() {
		return updatedAt;
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
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobBinaryCredentials getCredentials() {
		return credentials;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonIgnore
	public Boolean isProtected() {
		return isProtected;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonIgnore
	public Boolean isPublic() {
		return isPublic;
	}

	public static class JobBinaries extends ListResult<MapReduceJobBinary> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("binaries")
		private List<MapReduceJobBinary> jobbinaries;

		public List<MapReduceJobBinary> value() {
			return jobbinaries;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobBinaryBuilder toBuilder() {
		return new ConcreteJobBinaryBuilder(this);
	}

	/**
	 * @return the job binary Builder
	 */
	public static JobBinaryBuilder builder() {
		return new ConcreteJobBinaryBuilder();
	}

	public static class ConcreteJobBinaryBuilder implements JobBinaryBuilder {

		MapReduceJobBinary m;

		ConcreteJobBinaryBuilder() {
			this(new MapReduceJobBinary());
		}

		ConcreteJobBinaryBuilder(MapReduceJobBinary m) {
			this.m = m;
		}

		@Override
		public JobBinary build() {
			return m;
		}

		@Override
		public JobBinaryBuilder from(JobBinary in) {
			m = (MapReduceJobBinary) in;
			return this;
		}

		@Override
		public JobBinaryBuilder id(String id) {
			m.id = id;
			return this;
		}

		@Override
		public JobBinaryBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public JobBinaryBuilder url(String url) {
			m.url = url;
			return this;
		}

		@Override
		public JobBinaryBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public JobBinaryBuilder credentials(String user, String password) {
			m.credentials = new MapReduceJobBinaryCredentials(user, password);
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobBinaryBuilder isPublic(boolean isPublic) {
			m.isPublic = isPublic;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobBinaryBuilder isProtect(boolean isProtected) {
			m.isProtected = isProtected;
			return this;
		}

	}
}
