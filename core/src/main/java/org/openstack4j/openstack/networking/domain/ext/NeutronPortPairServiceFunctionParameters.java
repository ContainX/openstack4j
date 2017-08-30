package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;


@JsonRootName("service_function_parameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronPortPairServiceFunctionParameters implements PortPairServiceFunctionParameters {

	private static final long serialVersionUID = 1L;




	private String correlation;
	private int weight;

	/**
	 * wrap the PortPairServiceFunctionParameters to builder
	 * @return SessionPersistenceBuilder
	 */
	@Override
	public PortPairServiceFunctionParametersBuilder toBuilder() {
		return new PortPairServiceFunctionParametersContreteBuilder();
	}








	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCorrelation() {
		return correlation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getWeight() {
		return weight;
	}

	/**
	 * SessionPersistence Builder
	 * @author Massimiliano Romano
	 *
	 */
	public static class PortPairServiceFunctionParametersContreteBuilder implements PortPairServiceFunctionParametersBuilder {

		private NeutronPortPairServiceFunctionParameters m;

		public PortPairServiceFunctionParametersContreteBuilder() {
			this(new NeutronPortPairServiceFunctionParameters());
		}

		public PortPairServiceFunctionParametersContreteBuilder(NeutronPortPairServiceFunctionParameters m) {
			this.m = m;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairServiceFunctionParametersBuilder from(PortPairServiceFunctionParameters in) {
			m = (NeutronPortPairServiceFunctionParameters)in;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairServiceFunctionParametersBuilder weight(int weight) {
			m.weight = weight;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairServiceFunctionParametersBuilder correlation(String correlation) {
			m.correlation = correlation;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairServiceFunctionParameters build() {
			return m;
		}

	}

	public static PortPairServiceFunctionParametersBuilder builder(){
		return new PortPairServiceFunctionParametersContreteBuilder();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
			    .add("correlation",correlation)
			    .add("weight", weight)
			    .toString();
	}


}
