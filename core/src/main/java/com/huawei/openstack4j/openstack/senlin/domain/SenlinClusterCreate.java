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
import com.huawei.openstack4j.model.senlin.ClusterCreate;
import com.huawei.openstack4j.model.senlin.builder.ClusterCreateBuilder;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * This class contains all elements required for the creation of a SenlinCluster. It
 * uses Jackson annotation for (de)serialization into JSON
 *
 * @author lion
 *
 */
public class SenlinClusterCreate implements ClusterCreate {
	private static final long serialVersionUID = 795057209038510264L;

	@JsonProperty("cluster")
	private Map<String, Object> cluster;

	/**
	 * Returnes a {@link SenlinClusterCreateConcreteBuilder} for configuration and
	 * creation of a {@link SenlinClusterCreate} object.
	 *
	 * @return a {@link SenlinClusterCreateConcreteBuilder}
	 */
	public static SenlinClusterCreateConcreteBuilder build() {
		return new SenlinClusterCreateConcreteBuilder();
	}

	@Override
	public ClusterCreateBuilder toBuilder() {
		return new SenlinClusterCreateConcreteBuilder(this);
	}


	/**
	 * A Builder to create a SenlinCluster. Use {@link #build()} to receive the
	 * {@link ClusterCreate} object.
	 *
	 * @author lion
	 *
	 */
	public static class SenlinClusterCreateConcreteBuilder implements
			ClusterCreateBuilder {

		private SenlinClusterCreate model;

		/**
		 * Constructor to create a {@link SenlinClusterCreateConcreteBuilder} object
		 * with a new, empty {@link SenlinClusterCreate} object.
		 */
		public SenlinClusterCreateConcreteBuilder() {

			this(new SenlinClusterCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link SenlinClusterCreate}
		 * object.
		 *
		 * @param model
		 *            the {@link SenlinClusterCreate} object which is to be
		 *            modified.
		 */
		public SenlinClusterCreateConcreteBuilder(SenlinClusterCreate model) {
			this.model = model;

			this.model.cluster = Maps.newHashMap();
			Map<String, String> metadata = Maps.newHashMap();
			this.model.cluster.put("metadata", metadata);
		}

		@Override
		public ClusterCreate build() {
			return model;
		}

		@Override
		public ClusterCreateBuilder from(ClusterCreate in) {
			model = (SenlinClusterCreate) in;
			return this;
		}

		@Override
		public ClusterCreateBuilder name(String name) {
			model.cluster.put("name", name);
			return this;
		}

		@Override
		public ClusterCreateBuilder metadata(Map<String, String> metadata) {
			model.cluster.put("metadata", metadata);
			return this;
		}

		@Override
		public ClusterCreateBuilder desiredCapacity(int desiredCapacity) {
			model.cluster.put("desired_capacity", desiredCapacity);
			return this;
		}

		@Override
		public ClusterCreateBuilder maxSize(int maxSize) {
			model.cluster.put("max_size", maxSize);
			return this;
		}

		@Override
		public ClusterCreateBuilder minSize(int minSize) {
			model.cluster.put("min_size", minSize);
			return this;
		}

		@Override
		public ClusterCreateBuilder profileID(String profileID) {
			model.cluster.put("profile_id", profileID);
			return this;
		}

		@Override
		public ClusterCreateBuilder timeout(int timeout) {
			model.cluster.put("timeout", timeout);
			return this;
		}

	}

}
