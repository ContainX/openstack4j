package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRuleSupportedChoices;

/**
 * The Class Neutron Qos Rule Driver Supported Choices.
 *
 * @author Guoshuai Li
 */
public class NeutronQosRuleSupportedChoices extends NeutronQosRuleSupported implements NetQosRuleSupportedChoices {

    @JsonProperty("parameter_values")
    private List<String> parameterValues;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getParameterValues() {
        return parameterValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterType() {
        return "choices";
    }
}
