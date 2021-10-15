package org.openstack4j.openstack.networking.domain;

import java.util.List;
import java.util.Objects;

import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.State;
import org.openstack4j.model.network.Trunk;
import org.openstack4j.model.network.builder.TrunkBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * A Neutron Trunk
 *
 * This is the parent class for {@link NeutronTrunk} and
 * {@link NeutronTrunkSubport} NeutronTrunk has the json root name trunk whereas
 * NeutronTrunkSubport doesn't as the API doesn't expect it
 *
 * @author Kashyap Jha
 */
public abstract class AbstractNeutronTrunk implements Trunk {

    public static class TrunkConcreteBuilder extends ResourceBuilder<Trunk, TrunkConcreteBuilder>
            implements TrunkBuilder {

        NeutronTrunk reference;

        TrunkConcreteBuilder() {
            this(new NeutronTrunk());
        }

        TrunkConcreteBuilder(Trunk trunk) {
            this.reference = (NeutronTrunk) trunk;
        }

        @Override
        public TrunkBuilder adminState(boolean adminStateUp) {
            reference.adminStateUp = adminStateUp;
            return this;
        }

        @Override
        public Trunk build() {
            return reference;
        }

        @Override
        public TrunkBuilder description(String description) {
            reference.description = description;
            return this;
        }

        @Override
        public TrunkBuilder from(Trunk in) {
            reference = (NeutronTrunk) in;
            return this;
        }

        @Override
        public TrunkBuilder parentPort(String parentPortId) {
            reference.parentPortId = parentPortId;
            return this;
        }

        @Override
        protected Trunk reference() {
            return reference;
        }

        @Override
        public TrunkBuilder subPorts(List<NeutronSubPort> subPorts) {
            reference.subPorts = subPorts;
            return this;
        }
    }

    public static class Trunks extends ListResult<NeutronTrunk> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("trunks")
        private List<NeutronTrunk> trunks;

        @Override
        protected List<NeutronTrunk> value() {
            return trunks;
        }
    }

    private static final long serialVersionUID = 1L;

    public static TrunkBuilder builder() {
        return new TrunkConcreteBuilder();
    }

    @JsonProperty("admin_state_up")
    protected boolean adminStateUp = true;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("port_id")
    protected String parentPortId;

    @JsonProperty("revision_number")
    protected int revisionNumber;

    @JsonProperty("status")
    protected State state;

    @JsonProperty("sub_ports")
    protected List<NeutronSubPort> subPorts;

    @JsonProperty("tenant_id")
    protected String tenantId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof NeutronTrunk) {
            NeutronTrunk that = (NeutronTrunk) obj;
            if (java.util.Objects.equals(id, that.id) && java.util.Objects.equals(name, that.name)
                    && java.util.Objects.equals(adminStateUp, that.adminStateUp)
                    && java.util.Objects.equals(tenantId, that.tenantId)
                    && java.util.Objects.equals(parentPortId, that.parentPortId)
                    && java.util.Objects.equals(revisionNumber, that.revisionNumber)
                    && java.util.Objects.equals(state, that.state) && java.util.Objects.equals(subPorts, that.subPorts)
                    && java.util.Objects.equals(description, that.description)) {
                return true;
            }
        }
        return false;
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
    public String getParentPort() {
        return parentPortId;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<NeutronSubPort> getSubPorts() {
        return subPorts;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    public int hashCode() {
        return Objects.hash(id, name, parentPortId, revisionNumber, state, tenantId, subPorts, description);
    }

    @Override
    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setPortId(String parentPortId) {
        this.parentPortId = parentPortId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public TrunkBuilder toBuilder() {
        return new TrunkConcreteBuilder(this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("name", name)
                .add("adminStateUp", adminStateUp).add("parentPortId", parentPortId)
                .add("revisionNumber", revisionNumber).add("state", state).add("tenantId", tenantId)
                .add("subPorts", subPorts).add("description", description).toString();
    }
}