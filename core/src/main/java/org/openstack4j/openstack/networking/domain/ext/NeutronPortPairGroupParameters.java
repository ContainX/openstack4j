package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortPairGroupParameters;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.PortPairGroupParametersBuilder;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;

import java.util.List;


@JsonRootName("service_function_parameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronPortPairGroupParameters implements PortPairGroupParameters {

	private static final long serialVersionUID = 1L;


	@JsonProperty("lb_fields")
	private List<String> lbFields;


	/**
	 * wrap the PortPairGroupParameters to builder
	 * @return PortPairGroupParametersBuilder
	 */
	@Override
	public PortPairGroupParametersBuilder toBuilder() {
		return new PortPairGroupParametersContreteBuilder();
	}








	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getLbFields() {
		return lbFields;
	}


	/**
	 * PortPairGroupPArameters Builder
	 * @author Massimiliano Romano
	 *
	 */
	public static class PortPairGroupParametersContreteBuilder implements PortPairGroupParametersBuilder {

		private NeutronPortPairGroupParameters m;

		public PortPairGroupParametersContreteBuilder() {
			this(new NeutronPortPairGroupParameters());
		}

		public PortPairGroupParametersContreteBuilder(NeutronPortPairGroupParameters m) {
			this.m = m;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairGroupParametersBuilder from(PortPairGroupParameters in) {
			m = (NeutronPortPairGroupParameters)in;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairGroupParametersBuilder lbFields(List<String> lbFields) {
			m.lbFields = lbFields;
			return this;
		}


		/**
		 * {@inheritDoc}
		 */
		@Override
		public PortPairGroupParameters build() {
			return m;
		}

	}

	public static PortPairGroupParametersBuilder builder(){
		return new PortPairGroupParametersContreteBuilder();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
			    .add("lbFields",lbFields)
			    .toString();
	}


}
