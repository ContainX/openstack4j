package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.NetQosPolicy;
import org.openstack4j.model.network.NetQosPolicyUpdate;
import org.openstack4j.model.network.builder.NetQosPolicyUpdateBuilder;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Network qos that are bound to a Tenant
 *
 * @author bboyHan
 */
@JsonRootName("policy")
public class NeutronNetQosPolicyUpdate implements NetQosPolicyUpdate {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String description;
    @JsonProperty
    private boolean shared;
    @JsonProperty
    private String id;
    @JsonProperty("is_default")
    private boolean isDefault;
    private String name;

    public static NetQosPolicyUpdateBuilder builder() {
        return new NetQosPolicyUpdateConcreteBuilder();
    }

    @Override
    public NetQosPolicyUpdateBuilder toBuilder() {
        return new NetQosPolicyUpdateConcreteBuilder(this);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isShared() {
        return shared;
    }

    @Override
    public boolean isDefault() {
        return isDefault;
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
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id).add("description", description).add("shared", shared)
                .add("isDefault", isDefault).add("name", name)
                .toString();
    }

    public static class NetQosPolicyUpdateConcreteBuilder implements NetQosPolicyUpdateBuilder {

        private NeutronNetQosPolicyUpdate model;

        public NetQosPolicyUpdateConcreteBuilder() {
            model = new NeutronNetQosPolicyUpdate();
        }

        public NetQosPolicyUpdateConcreteBuilder(NeutronNetQosPolicyUpdate model) {
            this.model = model;
        }

        @Override
        public NetQosPolicyUpdate build() {
            return model;
        }

        @Override
        public NetQosPolicyUpdateBuilder from(NetQosPolicyUpdate in) {
            model = (NeutronNetQosPolicyUpdate) in;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder shared(boolean shared) {
            model.shared = shared;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder isDefault(boolean isDefault) {
            model.isDefault = isDefault;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder name(String name) {
            model.name = name;
            return this;
        }

    }

}
