package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.qos.NetQosDscpMarkingRule;
import org.openstack4j.model.network.qos.builder.NetQosDscpMarkingRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

/**
 * An OpenStack (Neutron) QoS Dscp Marking Rule
 *
 * @author Guoshuai Li
 */
@JsonRootName("dscp_marking_rule")
public class NeutronQosDscpMarkingRule extends NeutronQosRule implements NetQosDscpMarkingRule {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("dscp_mark")
    private Integer dscpMark;

    public static QosDscpMarkingRuleConcreteBuilder builder() {
        return new QosDscpMarkingRuleConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosDscpMarkingRuleBuilder toBuilder() {
        return new QosDscpMarkingRuleConcreteBuilder(this);
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
    public Integer getDscpMark() {
        return this.dscpMark;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id).add("dscp_mark", dscpMark)
                .addValue("\n")
                .toString();
    }

    /**
     * The Class Neutron Qos DSCP Marking Rules.
     *
     * @author Guoshuai Li
     */
    public static class NeutronQosDscpMarkingRules extends ListResult<NeutronQosDscpMarkingRule> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("dscp_marking_rules")
        private List<NeutronQosDscpMarkingRule> qosDscpMarkingRules;

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronQosDscpMarkingRule> value() {
            return this.qosDscpMarkingRules;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, dscpMark);
    }

    public static class QosDscpMarkingRuleConcreteBuilder implements NetQosDscpMarkingRuleBuilder {

        private NeutronQosDscpMarkingRule m;

        QosDscpMarkingRuleConcreteBuilder() {
            this(new NeutronQosDscpMarkingRule());
        }

        QosDscpMarkingRuleConcreteBuilder(NeutronQosDscpMarkingRule qosDscpMarkingRule) {
            this.m = qosDscpMarkingRule;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosDscpMarkingRuleBuilder dscpMark(Integer dscpMark) {
            m.dscpMark = dscpMark;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosDscpMarkingRule build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NetQosDscpMarkingRuleBuilder from(NetQosDscpMarkingRule in) {
            m = (NeutronQosDscpMarkingRule) in;
            return this;
        }
    }
}
