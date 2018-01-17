package org.openstack4j.openstack.networking.domain.ext;

import java.util.List;

import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 *
 *
 * @author Dmitry Gerenrot
 *
 */
@JsonRootName("flow_classifier")
public class NeutronFlowClassifier implements FlowClassifier {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String id;

	@JsonProperty
	private String name;

	@JsonProperty("project_id")
	private String tenantId;

	@JsonProperty
	private String description;

	@JsonProperty
	private String protocol;

	@JsonProperty("source_port_range_min")
	private String sourcePortRangeMin;

	@JsonProperty("source_port_range_max")
	private String sourcePortRangeMax;

	@JsonProperty("destination_port_range_min")
	private String destinationPortRangeMin;

	@JsonProperty("destination_port_range_max")
	private String destinationPortRangeMax;

	@JsonProperty("source_ip_prefix")
	private String sourceIpPrefix;

	@JsonProperty("destination_ip_prefix")
	private String destinationIpPrefix;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getSourcePortRangeMin() {
		return sourcePortRangeMin;
	}

	@Override
	public String getSourcePortRangeMax() {
		return sourcePortRangeMax;
	}

	@Override
	public String getDestinationPortRangeMin() {
		return destinationPortRangeMin;
	}

	@Override
	public String getDestinationPortRangeMax() {
		return destinationPortRangeMax;
	}

	@Override
	public String getSourceIpPrefix() {
		return sourceIpPrefix;
	}

	@Override
	public String getDestinationIpPrefix() {
		return destinationIpPrefix;
	}

	public FlowClassifierBuilder toBuilder() {
		return new FlowClassifierConcreteBuilder(this);
	}

    public static class FlowClassifiers extends ListResult<NeutronFlowClassifier> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("flow_classifiers")
        private List<NeutronFlowClassifier> flowClassifiers;

        public List<NeutronFlowClassifier> value() {
            return flowClassifiers;
        }
    }

	public static class FlowClassifierConcreteBuilder implements FlowClassifierBuilder {

		private NeutronFlowClassifier m;

		public FlowClassifierConcreteBuilder() {
			this.m = new NeutronFlowClassifier();
		}

		public FlowClassifierConcreteBuilder(NeutronFlowClassifier m) {
			this.m = m;
		}

		public FlowClassifierBuilder id(String id) {
			m.id = id;
			return this;
		}

		@Override
		public FlowClassifierBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public FlowClassifierBuilder tenandId(String tenantId) {
			m.tenantId = tenantId;
			return this;
		}

		@Override
		public FlowClassifierBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public FlowClassifierBuilder protocol(String protocol) {
			m.protocol = protocol;
			return this;
		}

		@Override
		public FlowClassifierBuilder sourcePortRangeMin(String sourcePortRangeMin) {
			m.sourcePortRangeMin = sourcePortRangeMin;
			return this;
		}

		@Override
		public FlowClassifierBuilder sourcePortRangeMax(String sourcePortRangeMax) {
			m.sourcePortRangeMax = sourcePortRangeMax;
			return this;
		}

		@Override
		public FlowClassifierBuilder destinationPortRangeMin(String destinationPortRangeMin) {
			m.destinationPortRangeMin = destinationPortRangeMin;
			return this;
		}

		@Override
		public FlowClassifierBuilder destinationPortRangeMax(String destinationPortRangeMax) {
			m.destinationPortRangeMax = destinationPortRangeMax;
			return this;
		}

		@Override
		public FlowClassifierBuilder sourceIpPrefix(String sourceIpPrefix) {
			m.sourceIpPrefix = sourceIpPrefix;
			return this;
		}

		@Override
		public FlowClassifierBuilder destinationIpPrefix(String destinationIpPrefix) {
			m.destinationIpPrefix = destinationIpPrefix;
			return this;
		}

		@Override
		public FlowClassifier build() {
			return m;
		}

		@Override
		public FlowClassifierBuilder from(FlowClassifier in) {
			m = (NeutronFlowClassifier) in;
			return this;
		}
	}
}
