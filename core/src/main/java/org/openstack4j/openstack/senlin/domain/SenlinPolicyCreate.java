package org.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.openstack4j.model.senlin.PolicyCreate;
import org.openstack4j.model.senlin.builder.PolicyCreateBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all elements required for the creation of a SenlinPolicy. It
 * uses Jackson annotation for (de)serialization into JSON
 *
 * @author lion
 *
 */
public class SenlinPolicyCreate implements PolicyCreate {
	private static final long serialVersionUID = 2130740129242729916L;

	@JsonProperty("policy")
	private Map<String, Object> policy;

	/**
	 * Returnes a {@link SenlinPolicyCreateConcreteBuilder} for configuration and
	 * creation of a {@link SenlinPolicyCreate} object.
	 *
	 * @return a {@link SenlinPolicyCreateConcreteBuilder}
	 */
	public static SenlinPolicyCreateConcreteBuilder build() {
		return new SenlinPolicyCreateConcreteBuilder();
	}

	@Override
	public PolicyCreateBuilder toBuilder() {
		return new SenlinPolicyCreateConcreteBuilder(this);
	}

	/**
	 * A Builder to create a SenlinPolicy. Use {@link #build()} to receive the
	 * {@link PolicyCreate} object.
	 *
	 * @author lion
	 *
	 */
	public static class SenlinPolicyCreateConcreteBuilder implements
			PolicyCreateBuilder {

		private SenlinPolicyCreate model;

		/**
		 * Constructor to create a {@link SenlinPolicyCreateConcreteBuilder} object
		 * with a new, empty {@link SenlinPolicyCreate} object.
		 */
		public SenlinPolicyCreateConcreteBuilder() {

			this(new SenlinPolicyCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link SenlinPolicyCreate}
		 * object.
		 *
		 * @param model
		 *            the {@link SenlinPolicyCreate} object which is to be
		 *            modified.
		 */
		public SenlinPolicyCreateConcreteBuilder(SenlinPolicyCreate model) {
			this.model = model;

			this.model.policy = Maps.newHashMap();
			HashMap<String, Object> spec = Maps.newHashMap();
			HashMap<String, Object> properties = Maps.newHashMap();
			HashMap<String, Object> adjustment = Maps.newHashMap();
			properties.put("adjustment", adjustment);
			spec.put("properties", properties);
			this.model.policy.put("spec", spec);
		}

		@Override
		public PolicyCreate build() {
			return model;
		}

		@Override
		public PolicyCreateBuilder from(PolicyCreate in) {
			model = (SenlinPolicyCreate) in;
			return this;
		}

		@Override
		public PolicyCreateBuilder name(String name) {
			model.policy.put("name", name);
			return this;
		}

		@Override
		public PolicyCreateBuilder spec(Map<String, Object> spec) {
			model.policy.put("spec", spec);
			return this;
		}

		@Override
		public PolicyCreateBuilder properties(Map<String, Object> properties) {
			HashMap<String, Object> spec = (HashMap<String, Object>)(model.policy.get("spec"));
			spec.put("properties", properties);
			return this;
		}

		@Override
		public PolicyCreateBuilder adjustment(Map<String, Object> adjustment) {
			HashMap<String, Object> properties = (HashMap<String, Object>)(((HashMap<String, Object>)(model.policy.get("spec"))).get("properties"));
			properties.put("adjustment", adjustment);
			return this;
		}

		@Override
		public PolicyCreateBuilder min_step(String min_step) {
			HashMap<String, Object> adjustment = (HashMap<String, Object>)(((HashMap<String, Object>)((HashMap<String, Object>)model.policy
					.get("spec")).get("properties")).get("adjustment"));
			adjustment.put("min_step", min_step);
			return this;
		}

		@Override
		public PolicyCreateBuilder number(String number) {
			HashMap<String, Object> adjustment = (HashMap<String, Object>)(((HashMap<String, Object>)((HashMap<String, Object>)model.policy
					.get("spec")).get("properties")).get("adjustment"));
			adjustment.put("number", number);
			return this;
		}

		@Override
		public PolicyCreateBuilder adjustment_type(String adjustment_type) {
			HashMap<String, Object> adjustment = (HashMap<String, Object>)(((HashMap<String, Object>)((HashMap<String, Object>)model.policy
					.get("spec")).get("properties")).get("adjustment"));
			adjustment.put("type", adjustment_type);
			return this;
		}

		@Override
		public PolicyCreateBuilder event(String event) {
			HashMap<String, Object> properties = (HashMap<String, Object>)((HashMap<String, Object>)model.policy
					.get("spec")).get("properties");
			properties.put("event", event);
			return this;
		}

		@Override
		public PolicyCreateBuilder spec_type(String spec_type) {
			HashMap<String, Object> spec = (HashMap<String, Object>)model.policy
					.get("spec");
			spec.put("type", spec_type);
			return this;
		}

		@Override
		public PolicyCreateBuilder version(String version) {
			HashMap<String, Object> spec = (HashMap<String, Object>)model.policy
					.get("spec");
			spec.put("version", version);
			return this;
		}

	}

}
