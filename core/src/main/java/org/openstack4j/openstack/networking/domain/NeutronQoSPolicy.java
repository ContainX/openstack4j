package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.QoSPolicy;
import org.openstack4j.model.network.builder.QoSPolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class NeutronQoSPolicy implements QoSPolicy {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("project_id")
    private String projectId;

    @JsonProperty("revision_number")
    private int revisionNumber;

    private boolean shared;

    @JsonProperty("is_default")
    private boolean isDefault;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = NeutronBandwidthLimitRule.class, name = "bandwidth_limit"),
            @JsonSubTypes.Type(value = NeutronDscpMarkingRule.class, name = "dscp_marking"),
            @JsonSubTypes.Type(value = NeutronMinimumBandwidthRule.class, name = "minimum_bandwidth"),
    })
    @JsonProperty("rules")
    private List<? extends Rule> rules;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public int getRevisionNumber() {
        return revisionNumber;
    }

    @Override
    public boolean isShared() {
        return shared;
    }

    @Override
    public List<? extends Rule> getRules() {
        return rules;
    }

    @Override
    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public QoSPolicyBuilder toBuilder() {
        return new QoSPolicyConcreteBuilder(this);
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

    public static QoSPolicyBuilder builder() {
        return new QoSPolicyConcreteBuilder();
    }

    public static class Policies extends ListResult<NeutronQoSPolicy> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("policies")
        private List<NeutronQoSPolicy> policies;

        @Override
        protected List<NeutronQoSPolicy> value() {
            return policies;
        }
    }

    public static class QoSPolicyConcreteBuilder extends ResourceBuilder<QoSPolicy, QoSPolicyConcreteBuilder> implements QoSPolicyBuilder {
        private NeutronQoSPolicy m;

        QoSPolicyConcreteBuilder() {
            this(new NeutronQoSPolicy());
        }

        QoSPolicyConcreteBuilder(NeutronQoSPolicy policy) {
            this.m = policy;
        }

        @Override
        protected QoSPolicy reference() {
            return m;
        }

        @Override
        public QoSPolicyBuilder description(String description) {
            m.description = description;
            return this;
        }

        @Override
        public QoSPolicyBuilder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        @Override
        public QoSPolicyBuilder revisionNumber(int revisionNumber) {
            m.revisionNumber = revisionNumber;
            return this;
        }

        @Override
        public QoSPolicyBuilder shared(boolean shared) {
            m.shared = shared;
            return this;
        }

        @Override
        public QoSPolicyBuilder rules(List<? extends Rule> rules) {
            m.rules = rules;
            return this;
        }

        @Override
        public QoSPolicyBuilder isDefault(boolean isDefault) {
            m.isDefault = isDefault;
            return this;
        }

        @Override
        public QoSPolicyBuilder createdAt(String createdAt) {
            m.createdAt = createdAt;
            return this;
        }

        @Override
        public QoSPolicyBuilder updatedAt(String updatedAt) {
            m.updatedAt = updatedAt;
            return this;
        }

        @Override
        public QoSPolicy build() {
            return m;
        }

        @Override
        public QoSPolicyBuilder from(QoSPolicy in) {
            m = (NeutronQoSPolicy) in;
            return this;
        }
    }
}
