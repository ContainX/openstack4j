package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRuleType;
import org.openstack4j.openstack.common.ListResult;

public class NeutronQosRuleType implements NetQosRuleType {

    private static final long serialVersionUID = 1L;

    @JsonProperty("type")
    private String type;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * The Class NeutronQosRuleTypes.
     *
     * @author Guoshuai Li
     */
    public static class NeutronQosRuleTypes extends ListResult<NeutronQosRuleType> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("rule_types")
        private List<NeutronQosRuleType> ruleTypes;

        /**
         * {@inheritDoc}
         */
        @Override
        protected List<NeutronQosRuleType> value() {
            return this.ruleTypes;
        }
    }
}
