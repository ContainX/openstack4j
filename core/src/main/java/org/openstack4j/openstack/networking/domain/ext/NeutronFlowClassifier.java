package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierProtocol;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("flow_classifier")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronFlowClassifier implements FlowClassifier {

    private static final long serialVersionUID = 1L;

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("project_id")
    private String projectId;

    private String name;

    private String description;

    @JsonProperty("source_port_range_min")
    private Integer sourcePortRangeMin;

    @JsonProperty("source_port_range_max")
    private Integer sourcePortRangeMax;

    @JsonProperty("destination_port_range_min")
    private Integer destinationPortRangeMin;

    @JsonProperty("destination_port_range_max")
    private Integer destinationPortRangeMax;

    @JsonProperty("destination_ip_prefix")
    private String destinationIpPrefix;


    private FlowClassifierProtocol protocol;


    private String ethertype;

    @JsonProperty("logical_source_port")
    private String logicalSourcePort;

    @JsonProperty("logical_destination_port")
    private String logicalDestinationPort;

    @JsonProperty("source_ip_prefix")
    private String sourceIpPrefix;






















    @Override
    public FlowClassifierBuilder toBuilder() {
        return new FlowClassifierContreteBuilder() ;
    }

    /**
     *
     * {@inheritDoc}
     */





    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("source_port_range_min", sourcePortRangeMin)
                .add("source_port_range_max", sourcePortRangeMax)
                .add("destination_port_range_min", destinationPortRangeMin)
                .add("destination_port_range_max", destinationPortRangeMax)
                .add("destination_ip_prefix", destinationIpPrefix)
                .add("protocol", protocol)
                .add("ethertype", ethertype)
                .add("logical_source_port", logicalSourcePort)
                .add("logical_destination_port", logicalDestinationPort)
                .add("source_ip_prefix", sourceIpPrefix)
                .add("projectId", projectId)
                .add("tenantId", tenantId)
                .toString();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public Integer getSourcePortRangeMin() {
        return sourcePortRangeMin;
    }

    @Override
    public Integer getSourcePortRangeMax() {
        return sourcePortRangeMax;
    }

    @Override
    public Integer getDestinationPortRangeMin() {
        return destinationPortRangeMin;
    }

    @Override
    public Integer getDestinationPortRangeMax() {
        return destinationPortRangeMax;
    }

    @Override
    public String getDestinationIpPrefix() {
        return destinationIpPrefix;
    }

    @Override
    public FlowClassifierProtocol getProtocol() {
        return protocol;
    }

    @Override
    public String getEthertype() {
        return ethertype;
    }

    @Override
    public String getLogicalSourcePort() {
        return logicalSourcePort;
    }

    @Override
    public String getLogicalDestinationPort() {
        return logicalDestinationPort;
    }

    @Override
    public String getSourceIpPrefix() {
        return sourceIpPrefix;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }


    /**
     *
     * @author Massimiliano Romano
     *
     */
    public static class FlowClassifiers extends ListResult<NeutronFlowClassifier>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("flow_classifiers")
        List<NeutronFlowClassifier> flowClassifierList;
        @Override
        public List<NeutronFlowClassifier> value() {
            return flowClassifierList;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("flow_classifiers", flowClassifierList).toString();
        }

    }
    public static class FlowClassifierContreteBuilder implements FlowClassifierBuilder {

        private NeutronFlowClassifier m;
        public FlowClassifierContreteBuilder(){
            this(new NeutronFlowClassifier());
        }
        public FlowClassifierContreteBuilder(NeutronFlowClassifier m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifier build() {
            return m;
        }

        @Override
        public FlowClassifierBuilder from(FlowClassifier in) {
            m = (NeutronFlowClassifier)in;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder sourcePortRangeMin(Integer sourcePortRangeMin) {
            m.sourcePortRangeMin = sourcePortRangeMin;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder sourcePortRangeMax(Integer sourcePortRangeMax) {
            m.sourcePortRangeMax=sourcePortRangeMax;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder destinationPortRangeMin(Integer destinationPortRangeMin) {
            m.destinationPortRangeMin=destinationPortRangeMin;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder destinationPortRangeMax(Integer destinationPortRangeMax) {
            m.destinationPortRangeMax = destinationPortRangeMax;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder destinationIpPrefix(String destinationIpPrefix) {
            m.destinationIpPrefix=destinationIpPrefix;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder protocol(FlowClassifierProtocol protocol) {
            m.protocol=protocol;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder ethertype(String ethertype) {
            m.ethertype=ethertype;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder logicalSourcePort(String logicalSourcePort) {
            m.logicalSourcePort=logicalSourcePort;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder logicalDestinationPort(String logicalDestinationPort) {
            m.logicalDestinationPort=logicalDestinationPort;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder sourceIpPrefix(String sourceIpPrefix) {
            m.sourceIpPrefix=sourceIpPrefix;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder name(String name) {
            m.name = name;
            return this;
        }









        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierBuilder description(String description) {
            m.description = description;
            return this;
        }









    }

    public static FlowClassifierBuilder  builder(){
        return new FlowClassifierContreteBuilder();

    }
}
