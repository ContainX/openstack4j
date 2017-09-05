package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortChainParameters;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.PortChainParametersBuilder;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;


@JsonRootName("port_chain_parameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronPortChainParameters implements PortChainParameters {

	private static final long serialVersionUID = 1L;




	private String correlation;
	private Boolean symmetric;

	/**
	 * wrap the PortChainParameters to builder
	 * @return PortChainParametersBuilder
	 */
	@Override
	public PortChainParametersBuilder toBuilder() {
		return new PortChainParametersContreteBuilder();
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
	public Boolean getSymmetric() {
		return symmetric;
	}

	/**
	 * PortChainParameters Builder
	 * @author Massimiliano Romano
	 *
	 */
	public static class PortChainParametersContreteBuilder implements PortChainParametersBuilder {

		private NeutronPortChainParameters m;

		public PortChainParametersContreteBuilder() {
			this(new NeutronPortChainParameters());
		}

		public PortChainParametersContreteBuilder(NeutronPortChainParameters m) {
			this.m = m;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortChainParametersBuilder from(PortChainParameters in) {
			m = (NeutronPortChainParameters)in;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortChainParametersBuilder symmetric(boolean symmetric) {
			m.symmetric = symmetric;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortChainParametersBuilder correlation(String correlation) {
			m.correlation = correlation;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortChainParameters build() {
			return m;
		}

	}

	public static PortChainParametersBuilder builder(){
		return new PortChainParametersContreteBuilder();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
			    .add("correlation",correlation)
			    .add("symmetric", symmetric)
			    .toString();
	}


}
