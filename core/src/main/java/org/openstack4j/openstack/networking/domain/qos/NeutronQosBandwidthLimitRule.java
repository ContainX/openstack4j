package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;
import org.openstack4j.model.network.qos.builder.NetQosBandwidthLimitRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

/**
 * An OpenStack (Neutron) QoS Bandwidth Limit Rule
 *
 * @author Guoshuai Li
 */
@JsonRootName("bandwidth_limit_rule")
public class NeutronQosBandwidthLimitRule extends NeutronQosRule implements NetQosBandwidthLimitRule {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("max_kbps")
    private Integer maxKbps;

    @JsonProperty("max_burst_kbps")
    private Integer maxBurstKbps;

    @JsonProperty("direction")
    private String direction;

    public static QosBandwidthLimitRuleConcreteBuilder builder() {
        return new QosBandwidthLimitRuleConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosBandwidthLimitRuleBuilder toBuilder() {
        return new QosBandwidthLimitRuleConcreteBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMaxKbps() {
        return this.maxKbps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMaxBurstKbps() {
        return this.maxBurstKbps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDirection() {
        return this.direction;
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
                .add("id", id).add("max_kbps", maxKbps)
                .add("max_burst_kbps", maxBurstKbps)
                .add("direction", direction)
                .addValue("\n")
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, maxKbps, maxBurstKbps, direction);
    }

    /**
     * The Class Neutron Qos Bandwidth Limit Rules.
     *
     * @author Guoshuai Li
     */
    public static class NeutronQosBandwidthLimitRules extends ListResult<NeutronQosBandwidthLimitRule> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("bandwidth_limit_rules")
        private List<NeutronQosBandwidthLimitRule> qosBandwidthLimitRules;

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronQosBandwidthLimitRule> value() {
            return this.qosBandwidthLimitRules;
        }
    }

    public static class QosBandwidthLimitRuleConcreteBuilder implements NetQosBandwidthLimitRuleBuilder {

        private NeutronQosBandwidthLimitRule m;

        QosBandwidthLimitRuleConcreteBuilder() {
            this(new NeutronQosBandwidthLimitRule());
        }

        QosBandwidthLimitRuleConcreteBuilder(NeutronQosBandwidthLimitRule qosBandwidthLimitRule) {
            this.m = qosBandwidthLimitRule;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosBandwidthLimitRuleBuilder maxKbps(Integer maxKbps) {
            m.maxKbps = maxKbps;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosBandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps) {
            m.maxBurstKbps = maxBurstKbps;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosBandwidthLimitRuleBuilder direction(String direction) {
            m.direction = direction;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosBandwidthLimitRule build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosBandwidthLimitRuleBuilder from(NetQosBandwidthLimitRule in) {
            m = (NeutronQosBandwidthLimitRule) in;
            return this;
        }
    }
}
