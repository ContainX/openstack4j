package org.openstack4j.openstack.networking.domain.qos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openstack4j.model.network.qos.NetQosRuleSupported;

/**
 * The Class Neutron Qos Rule Driver Supported.
 *
 * @author Guoshuai Li
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "parameter_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NeutronQosRuleSupportedRange.class, name = "range"),
        @JsonSubTypes.Type(value = NeutronQosRuleSupportedChoices.class, name = "choices")
})
public abstract class NeutronQosRuleSupported implements NetQosRuleSupported {

    private static final long serialVersionUID = 1L;

    @JsonProperty("parameter_name")
    private String parameterName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterName() {
        return parameterName;
    }

}
