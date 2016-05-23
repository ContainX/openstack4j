package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.openstack4j.model.senlin.ClusterCreate;
import org.openstack4j.model.senlin.builder.ClusterCreateBuilder;

import java.util.HashMap;
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
			HashMap<String, Object> metadata = Maps.newHashMap();
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
		public ClusterCreateBuilder metadata(Map<String, Object> metadata) {
			model.cluster.put("metadata", metadata);
			return this;
		}

		@Override
		public ClusterCreateBuilder desired_capacity(String desired_capacity) {
			model.cluster.put("desired_capacity", desired_capacity);
			return this;
		}

		@Override
		public ClusterCreateBuilder max_size(String max_size) {
			model.cluster.put("max_size", max_size);
			return this;
		}

		@Override
		public ClusterCreateBuilder min_size(String min_size) {
			model.cluster.put("min_size", min_size);
			return this;
		}

		@Override
		public ClusterCreateBuilder profile_id(String profile_id) {
			model.cluster.put("profile_id", profile_id);
			return this;
		}

		@Override
		public ClusterCreateBuilder timeout(String timeout) {
			model.cluster.put("timeout", timeout);
			return this;
		}

	}

}
