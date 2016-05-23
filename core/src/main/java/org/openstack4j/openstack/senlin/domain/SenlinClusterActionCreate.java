package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.senlin.ClusterActionCreate;
import org.openstack4j.model.senlin.ClusterCreate;
import org.openstack4j.model.senlin.builder.ClusterActionCreateBuilder;

import java.util.Map;

/**
 * This class contains all elements required for the creation of a SenlinClusterAction. It
 * uses Jackson annotation for (de)serialization into JSON
 *
 * @author lion
 *
 */
public class SenlinClusterActionCreate implements ClusterActionCreate {
	private static final long serialVersionUID = 8159175788259631363L;

	@JsonProperty("add_nodes")
	private Map<String, Object> add_nodes;
	@JsonProperty("del_nodes")
	private Map<String, Object> del_nodes;
	@JsonProperty("scale_out")
	private Map<String, Object> scale_out;
	@JsonProperty("scale_in")
	private Map<String, Object> scale_in;
	@JsonProperty("resize")
	private Map<String, Object> resize;
	@JsonProperty("check")
	private Map<String, Object> check;
	@JsonProperty("recover")
	private Map<String, Object> recover;
	@JsonProperty("policy_attach")
	private Map<String, Object> policy_attach;
	@JsonProperty("policy_detach")
	private Map<String, Object> policy_detach;
	@JsonProperty("policy_update")
	private Map<String, Object> policy_update;

	/**
	 * Returnes a {@link SenlinClusterActionCreateConcreteBuilder} for configuration and
	 * creation of a {@link SenlinClusterActionCreate} object.
	 *
	 * @return a {@link SenlinClusterActionCreateConcreteBuilder}
	 */
	public static SenlinClusterActionCreateConcreteBuilder build() {
		return new SenlinClusterActionCreateConcreteBuilder();
	}

	@Override
	public ClusterActionCreateBuilder toBuilder() {
		return new SenlinClusterActionCreateConcreteBuilder(this);
	}


	/**
	 * A Builder to create a SenlinClusterAction. Use {@link #build()} to receive the
	 * {@link ClusterCreate} object.
	 *
	 * @author lion
	 *
	 */
	public static class SenlinClusterActionCreateConcreteBuilder implements
			ClusterActionCreateBuilder {

		private SenlinClusterActionCreate model;

		/**
		 * Constructor to create a {@link SenlinClusterActionCreateConcreteBuilder} object
		 * with a new, empty {@link SenlinClusterActionCreate} object.
		 */
		public SenlinClusterActionCreateConcreteBuilder() {

			this(new SenlinClusterActionCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link SenlinClusterActionCreate}
		 * object.
		 *
		 * @param model
		 *            the {@link SenlinClusterActionCreate} object which is to be
		 *            modified.
		 */
		public SenlinClusterActionCreateConcreteBuilder(SenlinClusterActionCreate model) {
			this.model = model;
		}

		@Override
		public ClusterActionCreate build() {
			return model;
		}

		@Override
		public ClusterActionCreateBuilder from(ClusterActionCreate in) {
			model = (SenlinClusterActionCreate) in;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder add_nodes(Map<String, Object> add_nodes) {
			model.add_nodes = add_nodes;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder del_nodes(Map<String, Object> del_nodes) {
			model.del_nodes = del_nodes;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder scale_out(Map<String, Object> scale_out) {
			model.scale_out = scale_out;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder scale_in(Map<String, Object> scale_in) {
			model.scale_in = scale_in;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder resize(Map<String, Object> resize) {
			model.resize = resize;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder check(Map<String, Object> check) {
			model.check = check;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder recover(Map<String, Object> recover) {
			model.recover = recover;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder policy_attach(Map<String, Object> policy_attach) {
			model.policy_attach = policy_attach;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder policy_detach(Map<String, Object> policy_detach) {
			model.policy_detach = policy_detach;
			return this;
		}

		@Override
		public ClusterActionCreateBuilder policy_update(Map<String, Object> policy_update) {
			model.policy_update = policy_update;
			return this;
		}

	}

}
