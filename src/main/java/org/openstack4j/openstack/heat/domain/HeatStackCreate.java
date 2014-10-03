package org.openstack4j.openstack.heat.domain;

import java.util.Map;

import org.openstack4j.model.heat.StackCreate;
import org.openstack4j.model.heat.builder.StackCreateBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * This class contains all elements required for the creation of a HeatStack. It
 * uses Jackson annotation for (de)serialization into JSON
 * 
 * @author Matthias Reisser
 * 
 */
public class HeatStackCreate implements StackCreate {
	private static final long serialVersionUID = -8775995682456485275L;

	@JsonProperty("disableRollback")
	private boolean disableRollback;
	@JsonProperty("stack_name")
	private String name;
	@JsonRawValue
	@JsonProperty("template")
	private String jsonTemplate;
	@JsonProperty("parameters")
	private Map<String, String> parameters;
	@JsonProperty("timeout_mins")
	private Long timeoutMins;

	@JsonIgnore
	private String yamlTemplate;

	// To be added in the future
	// @JsonProperty("environment")
	// @JsonProperty("files")

	/**
	 * Returnes a {@link HeatStackCreateConcreteBuilder} for configuration and
	 * creation of a {@link HeatStackCreate} object.
	 * 
	 * @return a {@link HeatStackCreateConcreteBuilder}
	 */
	public static HeatStackCreateConcreteBuilder build() {
		return new HeatStackCreateConcreteBuilder();
	}

	@Override
	public StackCreateBuilder toBuilder() {
		return new HeatStackCreateConcreteBuilder(this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getJsonTemplate() {
		return jsonTemplate;
	}

	@Override
	public Map<String, String> getParameters() {

		return parameters;
	}

	@Override
	public boolean getDisableRollback() {

		return disableRollback;
	}

	@Override
	public String getYamlTemplate() {
		return yamlTemplate;
	}

	/**
	 * A Builder to create a HeatStack. Use {@link #build()} to receive the
	 * {@link StackCreate} object.
	 * 
	 * @author Matthias Reisser
	 * 
	 */
	public static class HeatStackCreateConcreteBuilder implements
			StackCreateBuilder {

		private HeatStackCreate model;

		/**
		 * Constructor to create a {@link HeatStackCreateConcreteBuilder} object
		 * with a new, empty {@link HeatStackCreate} object.
		 */
		public HeatStackCreateConcreteBuilder() {
			this(new HeatStackCreate());
		}

		/**
		 * Constructor for manipulation of an existing {@link HeatStackCreate}
		 * object.
		 * 
		 * @param model
		 *            the {@link HeatStackCreate} object which is to be
		 *            modified.
		 */
		public HeatStackCreateConcreteBuilder(HeatStackCreate model) {
			this.model = model;
		}

		@Override
		public StackCreate build() {
			return model;
		}

		@Override
		public StackCreateBuilder from(StackCreate in) {
			model = (HeatStackCreate) in;
			return this;
		}

		@Override
		public StackCreateBuilder name(String name) {
			model.name = name;
			return this;
		}

		@Override
		public StackCreateBuilder jsonTemplate(String template) {
			model.jsonTemplate = template;
			return this;
		}

		@Override
		public StackCreateBuilder parameters(Map<String, String> parameters) {
			model.parameters = parameters;
			return this;
		}

		@Override
		public StackCreateBuilder timeoutMins(Long timeoutMins) {
			model.timeoutMins = timeoutMins;
			return this;
		}

		@Override
		public StackCreateBuilder disableRollback(boolean disableRollback) {
			model.disableRollback = disableRollback;
			return this;
		}

		@Override
		public StackCreateBuilder yamlTemplate(String yamlTemplate) {
			model.yamlTemplate = yamlTemplate;
			return this;
		}

	}

}
