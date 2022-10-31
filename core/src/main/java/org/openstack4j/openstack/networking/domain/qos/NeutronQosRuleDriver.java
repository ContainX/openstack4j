package org.openstack4j.openstack.networking.domain.qos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRuleDriver;
import org.openstack4j.model.network.qos.NetQosRuleSupported;

import java.util.List;

/**
 * The Class Neutron Qos Rule Driver.
 *
 * @author Guoshuai Li
 */
class NeutronQosRuleDriver implements NetQosRuleDriver {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("supported_parameters")
    private List<NeutronQosRuleSupported> supportedParameters;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosRuleSupported> getSupportedParameters() {
        return supportedParameters;
    }
}