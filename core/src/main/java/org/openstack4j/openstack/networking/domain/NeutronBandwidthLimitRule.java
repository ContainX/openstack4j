package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.QoSPolicy.BandwidthLimitRule;
import org.openstack4j.model.network.builder.BandwidthLimitRuleBuilder;

import java.util.List;
import java.util.Set;

public class NeutronBandwidthLimitRule implements BandwidthLimitRule {

    private String id;
    private String tenantId;
    private String type;
    private String name;
    private Integer maxKbps;
    private Integer maxBurstKbps;
    private String direction;
    private List<String> tags;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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
    public String getType() {
        return type;
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
    public Integer getMaxKbps() {
        return maxKbps;
    }

    @Override
    public Integer getMaxBurstKbps() {
        return 0;
    }

    @Override
    public String getDirection() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return null;
    }

    @Override
    public BandwidthLimitRuleBuilder toBuilder() {
        return new BandwidthLimitRuleConcreteBuilder(this);
    }

    public static BandwidthLimitRuleBuilder builder() {
        return new BandwidthLimitRuleConcreteBuilder();
    }

    public static class BandwidthLimitRuleConcreteBuilder extends ResourceBuilder<BandwidthLimitRule, BandwidthLimitRuleConcreteBuilder> implements BandwidthLimitRuleBuilder {
        private NeutronBandwidthLimitRule m;

        BandwidthLimitRuleConcreteBuilder() {
            this(new NeutronBandwidthLimitRule());
        }

        BandwidthLimitRuleConcreteBuilder(NeutronBandwidthLimitRule rule) {
            this.m = rule;
        }

        @Override
        protected BandwidthLimitRule reference() {
            return m;
        }

        @Override
        public BandwidthLimitRuleBuilder maxKbps(Integer maxKbps) {
            m.maxKbps = maxKbps;
            return this;
        }

        @Override
        public BandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps) {
            m.maxBurstKbps = maxBurstKbps;
            return this;
        }

        @Override
        public BandwidthLimitRuleBuilder direction(String direction) {
            m.direction = direction;
            return this;
        }

        @Override
        public BandwidthLimitRule build() {
            return m;
        }

        @Override
        public BandwidthLimitRuleBuilder from(BandwidthLimitRule in) {
            m = (NeutronBandwidthLimitRule) in;
            return this;
        }
    }
}
