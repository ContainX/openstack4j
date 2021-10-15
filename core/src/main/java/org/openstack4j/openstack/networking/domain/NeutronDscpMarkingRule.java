package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.QoSPolicy.DscpMarkingRule;
import org.openstack4j.model.network.builder.DscpMarkingRuleBuilder;

import java.util.Set;

public class NeutronDscpMarkingRule implements DscpMarkingRule {

    private String id;
    private String type;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;
    private Integer dscpMark;
    private Set<String> tags;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
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
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public Integer getDscpMark() {
        return dscpMark;
    }

    @Override
    public DscpMarkingRuleBuilder toBuilder() {
        return new DscpMarkingRuleConcreteBuilder(this);
    }

    public static DscpMarkingRuleBuilder builder() {
        return new DscpMarkingRuleConcreteBuilder();
    }

    public static class DscpMarkingRuleConcreteBuilder extends ResourceBuilder<DscpMarkingRule, DscpMarkingRuleConcreteBuilder> implements DscpMarkingRuleBuilder {

        private NeutronDscpMarkingRule m;

        DscpMarkingRuleConcreteBuilder() {
            this(new NeutronDscpMarkingRule());
        }

        DscpMarkingRuleConcreteBuilder(NeutronDscpMarkingRule rule) {
            this.m = rule;
        }

        @Override
        protected DscpMarkingRule reference() {
            return m;
        }

        @Override
        public DscpMarkingRuleBuilder dscpMark(Integer dscpMark) {
            m.dscpMark = dscpMark;
            return this;
        }

        @Override
        public DscpMarkingRuleBuilder tags(Set<String> tags) {
            m.tags = tags;
            return this;
        }

        @Override
        public DscpMarkingRule build() {
            return m;
        }

        @Override
        public DscpMarkingRuleBuilder from(DscpMarkingRule in) {
            m = (NeutronDscpMarkingRule) in;
            return this;
        }
    }
}
