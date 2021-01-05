package org.openstack4j.openstack.networking.domain.qos;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRuleSupportedRange;

/**
 * The Class Neutron Qos Rule Driver Supported Range.
 *
 * @author Guoshuai Li
 */
public class NeutronQosRuleSupportedRange extends NeutronQosRuleSupported implements NetQosRuleSupportedRange {

    @JsonProperty("parameter_values")
    private Map<String, Integer> parameterValues;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getParameterValues() {
        return this.parameterValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterType() {
        return "range";
    }
}
