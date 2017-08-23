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
package com.huawei.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.senlin.ProfileCreate;
import com.huawei.openstack4j.model.senlin.builder.ProfileCreateBuilder;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all elements required for the creation of a SenlinProfile. It
 * uses Jackson annotation for (de)serialization into JSON
 *
 * @author lion
 *
 */
public class SenlinProfileCreate implements ProfileCreate {
	private static final long serialVersionUID = -7343560700665811096L;

	@JsonProperty("profile")
	private Map<String, Object> profile;

	/**
	 * Returnes a {@link SenlinProfileCreateConcreteBuilder} for configuration and
	 * creation of a {@link SenlinProfileCreate} object.
	 *
	 * @return a {@link SenlinProfileCreateConcreteBuilder}
	 */
	public static SenlinProfileCreateConcreteBuilder build() {
		return new SenlinProfileCreateConcreteBuilder();
	}

	@Override
	public ProfileCreateBuilder toBuilder() {
		return new SenlinProfileCreateConcreteBuilder(this);
	}

	/**
	 * A Builder to create a SenlinProfile. Use {@link #build()} to receive the
	 * {@link ProfileCreate} object.
	 *
	 * @author lion
	 *
	 */
	public static class SenlinProfileCreateConcreteBuilder implements
			ProfileCreateBuilder {

		private SenlinProfileCreate model;

		/**
		 * Constructor to create a {@link SenlinProfileCreateConcreteBuilder} object
		 * with a new, empty {@link SenlinProfileCreate} object.
		 */
		public SenlinProfileCreateConcreteBuilder() {

			this(new SenlinProfileCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link SenlinProfileCreate}
		 * object.
		 *
		 * @param model
		 *            the {@link SenlinProfileCreate} object which is to be
		 *            modified.
		 */
		public SenlinProfileCreateConcreteBuilder(SenlinProfileCreate model) {
			this.model = model;

			this.model.profile = Maps.newHashMap();
			HashMap<String, Object> metadata = Maps.newHashMap();
			HashMap<String, Object> spec = Maps.newHashMap();
			this.model.profile.put("metadata", metadata);
			this.model.profile.put("spec", spec);
		}

		@Override
		public ProfileCreate build() {
			return model;
		}

		@Override
		public ProfileCreateBuilder from(ProfileCreate in) {
			model = (SenlinProfileCreate) in;
			return this;
		}

		@Override
		public ProfileCreateBuilder name(String name) {
			model.profile.put("name", name);
			return this;
		}

		@Override
		public ProfileCreateBuilder spec(Map<String, Object> spec) {
			model.profile.put("spec", spec);
			return this;
		}

		@Override
		public ProfileCreateBuilder metadata(Map<String, Map> metadata) {
			model.profile.put("metadata", metadata);
			return this;
		}

	}

}
