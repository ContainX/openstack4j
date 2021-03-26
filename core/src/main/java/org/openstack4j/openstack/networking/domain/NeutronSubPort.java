package org.openstack4j.openstack.networking.domain;

import java.util.List;
import java.util.Objects;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.SubPort;
import org.openstack4j.model.network.builder.SubPortBuilder;
import org.openstack4j.openstack.common.ListEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 * A Subport ONLY used for adding to trunks
 *
 * @author Kashyap Jha
 */
@JsonRootName("sub_port")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSubPort implements SubPort, ModelEntity {

    public static class NeutronSubPorts implements ModelEntity {

        private static final long serialVersionUID = 1L;

        public static NeutronSubPorts fromSubPorts(List<? extends SubPort> subPorts) {
            NeutronSubPorts toCreate = new NeutronSubPorts();
            for (SubPort subPort : subPorts) {
                toCreate.subPorts.add(NeutronSubPort.fromSubPort(subPort));
            }
            return toCreate;
        }

        @JsonProperty("sub_ports")
        private ListEntity<NeutronSubPort> subPorts;

        public NeutronSubPorts() {
            subPorts = new ListEntity<>();
        }
    }

    public static class SubPortConcreteBuilder extends ResourceBuilder<SubPort, SubPortConcreteBuilder>
            implements SubPortBuilder {

        private NeutronSubPort reference;

        SubPortConcreteBuilder() {
            this(new NeutronSubPort());
        }

        SubPortConcreteBuilder(NeutronSubPort subPort) {
            this.reference = subPort;
        }

        @Override
        public SubPort build() {
            return reference;
        }

        @Override
        public SubPortBuilder from(SubPort in) {
            reference = (NeutronSubPort) in;
            return this;
        }

        @Override
        public SubPortBuilder portId(String portId) {
            reference.portId = portId;
            return this;
        }

        @Override
        protected SubPort reference() {
            return reference;
        }

        @Override
        public SubPortBuilder segmentationId(int segmentationId) {
            reference.segmentationId = segmentationId;
            return this;
        }

        @Override
        public SubPortBuilder segmentationType(String segmentationType) {
            reference.segmentationType = segmentationType;
            return this;
        }
    }

    public static class SubPorts extends ListResult<NeutronSubPort> {
        private static final long serialVersionUID = 1L;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("sub_ports")
        private List<NeutronSubPort> subPorts;

        @Override
        protected List<NeutronSubPort> value() {
            return subPorts;
        }
    }

    private static final long serialVersionUID = 1L;

    public static SubPortBuilder builder() {
        return new SubPortConcreteBuilder();
    }

    public static NeutronSubPort fromSubPort(SubPort subPort) {
        NeutronSubPort toCreate = new NeutronSubPort();
        toCreate.portId = subPort.getPortId();
        toCreate.segmentationId = subPort.getSegmentationId();
        toCreate.segmentationType = subPort.getSegmentationType();
        return toCreate;
    }

    @JsonProperty("port_id")
    private String portId;

    @JsonProperty("segmentation_id")
    private int segmentationId;

    @JsonProperty("segmentation_type")
    private String segmentationType;

    public NeutronSubPort() {
    }

    public NeutronSubPort(String portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof NeutronSubPort) {
            NeutronSubPort that = (NeutronSubPort) obj;
            if (Objects.equals(portId, that.portId) && Objects.equals(segmentationId, that.segmentationId)
                    && Objects.equals(segmentationType, that.segmentationType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Subports don't have the ID attribute. Use {@link getPortId} instead
     */
    @Override
    @Deprecated
    public String getId() {
        return portId;
    }

    /**
     * Subports don't have the name attribute
     */
    @Override
    @Deprecated
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPortId() {
        return portId;
    }

    @Override
    public int getSegmentationId() {
        return segmentationId;
    }

    @Override
    public String getSegmentationType() {
        return segmentationType;
    }

    /**
     * Subports don't have the tenantId attrbute
     */
    @Override
    @Deprecated
    public String getTenantId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentationId, portId, segmentationType);
    }

    @Override
    @Deprecated
    public void setId(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void setName(String name) {
        throw new UnsupportedOperationException();

    }

    @Override
    @Deprecated
    public void setTenantId(String tenantId) {
        throw new UnsupportedOperationException();

    }

    @Override
    public SubPortBuilder toBuilder() {
        return new SubPortConcreteBuilder(this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("segmentationId", segmentationId)
                .add("portId", portId).add("segmentationType", segmentationType).toString();

    }
}