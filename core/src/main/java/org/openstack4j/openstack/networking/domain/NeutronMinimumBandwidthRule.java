package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.QoSPolicy.MinimumBandwidthRule;
import org.openstack4j.model.network.builder.MinimumBandwidthRuleBuilder;

import java.util.Set;

public class NeutronMinimumBandwidthRule implements MinimumBandwidthRule {

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;

    @JsonProperty("min_kbps")
    private Integer minKbps;

    private String direction;
    private Set<String> tags;
    private String type;

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
    public Integer getMinKbps() {
        return minKbps;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public MinimumBandwidthRuleBuilder toBuilder() {
        return new MinimumBandwidthRuleConcreteBuilder(this);
    }

    public static MinimumBandwidthRuleBuilder builder() {
        return new MinimumBandwidthRuleConcreteBuilder();
    }

    public static class MinimumBandwidthRuleConcreteBuilder extends ResourceBuilder<MinimumBandwidthRule, MinimumBandwidthRuleConcreteBuilder> implements MinimumBandwidthRuleBuilder {

        private NeutronMinimumBandwidthRule m;

        MinimumBandwidthRuleConcreteBuilder() {
            this(new NeutronMinimumBandwidthRule());
        }

        MinimumBandwidthRuleConcreteBuilder(NeutronMinimumBandwidthRule rule) {
            this.m = rule;
        }

        @Override
        protected MinimumBandwidthRule reference() {
            return m;
        }

        @Override
        public MinimumBandwidthRuleBuilder minKbps(Integer minKbps) {
            m.minKbps = minKbps;
            return this;
        }

        @Override
        public MinimumBandwidthRuleBuilder direction(String direction) {
            m.direction = direction;
            return this;
        }

        @Override
        public MinimumBandwidthRuleBuilder tags(Set<String> tags) {
            m.tags = tags;
            return this;
        }

        @Override
        public MinimumBandwidthRule build() {
            return m;
        }

        @Override
        public MinimumBandwidthRuleBuilder from(MinimumBandwidthRule in) {
            m = (NeutronMinimumBandwidthRule) in;
            return this;
        }
    }
}
