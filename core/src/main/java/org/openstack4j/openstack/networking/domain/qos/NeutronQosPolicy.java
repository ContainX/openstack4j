package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;

import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.qos.NetQosPolicy;
import org.openstack4j.model.network.qos.NetQosRule;
import org.openstack4j.model.network.qos.builder.NetQosPolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

/**
 * An OpenStack (Neutron) QoS Policy
 *
 * @author Guoshuai Li
 */
@JsonRootName("policy")
public class NeutronQosPolicy implements NetQosPolicy {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tenant_id")
    private String tenant_id;

    @JsonProperty("shared")
    private Boolean shared;

    @JsonProperty("is_default")
    private Boolean isDefault;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = NeutronQosBandwidthLimitRule.class, name = "bandwidth_limit"),
            @JsonSubTypes.Type(value = NeutronQosDscpMarkingRule.class, name = "dscp_marking"),
            @JsonSubTypes.Type(value = NeutronQosMinimumBandwidthRule.class, name = "minimum_bandwidth"),
    })
    @JsonProperty("rules")
    private List<NeutronQosRule> rules;

    public static QosPolicyConcreteBuilder builder() {
        return new QosPolicyConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicyBuilder toBuilder() {
        return new QosPolicyConcreteBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShared() {
        return this.shared != null && this.shared;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public boolean isDefault() {
        return this.isDefault != null && this.isDefault;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosRule> getRules() {
        return rules;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTenantId() {
        return this.tenant_id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTenantId(String tenantId) {
        this.tenant_id = tenantId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id).add("name", name)
                .add("tenant_id", tenant_id)
                .add("shared", shared)
                .add("is_default", isDefault)
                .addValue("\n")
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, tenant_id, shared, isDefault);
    }

    /**
     * The Class QosPolicies.
     *
     * @author Guoshuai Li
     */
    public static class QosPolicies extends ListResult<NeutronQosPolicy> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("policies")
        private List<NeutronQosPolicy> policies;

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronQosPolicy> value() {
            return this.policies;
        }
    }

    public static class QosPolicyConcreteBuilder extends ResourceBuilder<NetQosPolicy, QosPolicyConcreteBuilder> implements NetQosPolicyBuilder {

        private NeutronQosPolicy m;

        QosPolicyConcreteBuilder() {
            this(new NeutronQosPolicy());
        }

        QosPolicyConcreteBuilder(NeutronQosPolicy qosPolicy) {
            this.m = qosPolicy;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosPolicyBuilder isShared(boolean shared) {
            m.shared = shared;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosPolicyBuilder isDefault(boolean isDefault) {
            m.isDefault = isDefault;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosPolicy build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosPolicyBuilder from(NetQosPolicy in) {
            m = (NeutronQosPolicy) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected NetQosPolicy reference() {
            return m;
        }
    }
}
