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
package org.openstack4j.openstack.tacker.domain;

import java.util.List;

import org.openstack4j.model.tacker.Vim;
import org.openstack4j.model.tacker.builder.VimBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
@JsonRootName("vim")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TackerVim implements Vim {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	@JsonProperty("tenant_id")
	private String tenantId;

	private String description;

	private String type;

	private TackerVimStatus status;

	@JsonProperty("is_default")
	private Boolean isDefault;

	@JsonProperty("auth_cred")
	private AuthCredentials authCredentials;

	@JsonProperty("auth_url")
	private String authUrl;

	@JsonProperty("placement_attr")
	private VimPlacementAttribute placementAttribute;

	@JsonProperty("vim_project")
	private VimProject vimProject;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("tenantId", tenantId)
				.add("description", description).add("type", type).add("status", status).add("isDefault", isDefault)
				.add("authCredentials", authCredentials).add("authUrl", authUrl)
				.add("placementAttribute", placementAttribute).add("vimProject", vimProject).toString();
	}

	/**
	 * Wrap this TackerVim to a builder
	 * @return VimBuilder
	 */
	@Override
	public VimBuilder toBuilder() {
		return new VimConcreteBuilder(this);
	}

	/**
	 * @return VimBuilder
	 */
	public static VimBuilder builder() {
		return new VimConcreteBuilder();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the status
	 */
	public TackerVimStatus getStatus() {
		return status;
	}

	/**
	 * @return the isDefault
	 */
	@Override
	public Boolean isDefault() {
		return null;
	}

	/**
	 * @return the authCredentials
	 */
	public AuthCredentials getAuthCredentials() {
		return authCredentials;
	}

	/**
	 * @return the authUrl
	 */
	public String getAuthUrl() {
		return authUrl;
	}

	/**
	 * @return the placementAttribute
	 */
	public VimPlacementAttribute getPlacementAttribute() {
		return placementAttribute;
	}

	/**
	 * @return the vimProject
	 */
	public VimProject getVimProject() {
		return vimProject;
	}

	public static class TackerVims extends ListResult<TackerVim> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("vims")
		List<TackerVim> vims;

		@Override
		public List<TackerVim> value() {
			return vims;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).omitNullValues()
					.add("vims", vims).toString();
		}
	}

	public static class VimConcreteBuilder implements VimBuilder {

		TackerVim vim;

		@Override
		public Vim build() {
			return vim;
		}

		public VimConcreteBuilder() {
			this(new TackerVim());
		}

		public VimConcreteBuilder(TackerVim f) {
			this.vim = f;
		}

		@Override
		public VimBuilder from(Vim in) {
			this.vim = (TackerVim) in;
			return this;
		}

		@Override
		public VimBuilder name(String name) {
			vim.name = name;
			return this;
		}

		@Override
		public VimBuilder description(String description) {
			vim.description = description;
			return this;
		}

		@Override
		public VimBuilder authUrl(String authUrl) {
			vim.authUrl = authUrl;
			return this;
		}

		@Override
		public VimBuilder vimProject(VimProject vimProject) {
			vim.vimProject = vimProject;
			return this;
		}

		@Override
		public VimBuilder isDefault(Boolean isDefault) {
			vim.isDefault = isDefault;
			return this;
		}

		@Override
		public VimBuilder authCredentials(AuthCredentials authCredentials) {
			vim.authCredentials = authCredentials;
			return this;
		}

		@Override
		public VimBuilder type(String type) {
			vim.type = type;
			return this;
		}
	}
}
