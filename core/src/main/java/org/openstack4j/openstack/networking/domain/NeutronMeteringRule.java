package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.openstack4j.model.network.MeteringDirection;
import org.openstack4j.model.network.MeteringRule;
import org.openstack4j.model.network.builder.MeteringRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * An OpenStack (Neutron) metering rule
 * 
 * @author Caio Bergamasco
 */
@JsonRootName("metering_label_rule")
public class NeutronMeteringRule implements MeteringRule {

    private static final long serialVersionUID = 1L;

    private MeteringDirection direction;
    @JsonProperty("metering_label_id")
    private String meteringLabelId;
    @JsonProperty("remote_ip_prefix")
    private String remoteIpPrefix;
    private String id;
    private Boolean excluded;

    public static MeteringRuleBuilder builder() {
        return new MeteringRuleConcreteBuilder();
    }

    /**
     * Wraps this Metering Rule into a Builder
     * @return the metering rule builder
     */
    public MeteringRuleBuilder toBuilder() {
        return new MeteringRuleConcreteBuilder(this);
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
    public void setDirection(MeteringDirection direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeteringLabelId(String meteringLabelId) {
        this.meteringLabelId = meteringLabelId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRemoteIpPrefix(String remoteIpPrefix) {
        this.remoteIpPrefix = remoteIpPrefix;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setExcluded(Boolean excluded) {
        this.excluded = excluded;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getMeteringLabelId() {
        return meteringLabelId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRemoteIpPrefix() {
        return remoteIpPrefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MeteringDirection getDirection() {
        return direction;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getExcluded() {
        return excluded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("meteringLabelId", meteringLabelId)
                .add("direction", direction)
                .add("excluded", excluded)
                .add("remoteIpPrefix", remoteIpPrefix)
                .toString();
    }
    
    public static class MeteringRules extends ListResult<NeutronMeteringRule> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("meteringLabels")
        private List<NeutronMeteringRule> meteringRules;

        public List<NeutronMeteringRule> value() {
            return meteringRules;
        }
    }

    public static class MeteringRuleConcreteBuilder implements MeteringRuleBuilder {

        private NeutronMeteringRule m;

        public MeteringRuleConcreteBuilder() {
            this(new NeutronMeteringRule());
        }

        public MeteringRuleConcreteBuilder(NeutronMeteringRule m) {
            this.m = m;
        }

        @Override
        public MeteringRuleBuilder remoteIpPrefix(String remoteIpPrefix) {
            m.remoteIpPrefix = remoteIpPrefix;
            return this;
        }

        @Override
        public MeteringRuleBuilder direction(MeteringDirection direction) {
            m.direction = direction;
            return this;
        }

        @Override
        public MeteringRuleBuilder meteringLabelId(String meteringLabelId) {
            m.meteringLabelId = meteringLabelId;
            return this;
        }

        @Override
        public MeteringRuleBuilder excluded(Boolean excluded) {
            m.excluded = excluded;
            return this;
        }

        @Override
        public MeteringRule build() {
            return m;
        }

        @Override
        public MeteringRuleBuilder from(MeteringRule in) {
            m = (NeutronMeteringRule) in;
            return this;
        }
    }

}
