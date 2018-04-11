package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.qos.NetQosMinimumBandwidthRule;
import org.openstack4j.model.network.qos.builder.NetQosMinimumBandwidthRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

/**
 * An OpenStack (Neutron) QoS Minimum Bandwidth Rule
 *
 * @author Guoshuai Li
 */
@JsonRootName("minimum_bandwidth_rule")
public class NeutronQosMinimumBandwidthRule extends NeutronQosRule implements NetQosMinimumBandwidthRule {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("min_kbps")
    private Integer minKbps;

    @JsonProperty("direction")
    private String direction;

    public static QosMinimumBandwidthRuleConcreteBuilder builder() {
        return new QosMinimumBandwidthRuleConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosMinimumBandwidthRuleBuilder toBuilder() {
        return new QosMinimumBandwidthRuleConcreteBuilder(this);
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
    public Integer getMinKbps() {
        return this.minKbps;
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
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id).add("min_kbps", minKbps)
                .add("direction", direction).addValue("\n")
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, minKbps, direction);
    }

    /**
     * The Class Neutron Qos Minimum Bandwidth Rules.
     *
     * @author Guoshuai Li
     */
    public static class NeutronQosMinimumBandwidthRules extends ListResult<NeutronQosMinimumBandwidthRule> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("minimum_bandwidth_rules")
        private List<NeutronQosMinimumBandwidthRule> qosMinimumBandwidthRules;

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronQosMinimumBandwidthRule> value() {
            return this.qosMinimumBandwidthRules;
        }
    }

    public static class QosMinimumBandwidthRuleConcreteBuilder implements NetQosMinimumBandwidthRuleBuilder {

        private NeutronQosMinimumBandwidthRule m;

        QosMinimumBandwidthRuleConcreteBuilder() {
            this(new NeutronQosMinimumBandwidthRule());
        }

        QosMinimumBandwidthRuleConcreteBuilder(NeutronQosMinimumBandwidthRule qosMinimumBandwidthRule) {
            this.m = qosMinimumBandwidthRule;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosMinimumBandwidthRuleBuilder minKbps(Integer minKbps) {
            m.minKbps = minKbps;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosMinimumBandwidthRuleBuilder direction(String direction) {
            m.direction = direction;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosMinimumBandwidthRule build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosMinimumBandwidthRuleBuilder from(NetQosMinimumBandwidthRule in) {
            m = (NeutronQosMinimumBandwidthRule) in;
            return this;
        }
    }
}
