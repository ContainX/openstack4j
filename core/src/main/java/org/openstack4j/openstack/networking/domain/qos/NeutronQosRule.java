package org.openstack4j.openstack.networking.domain.qos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRule;

/**
 * An OpenStack (Neutron) QoS Rule
 *
 * @author Guoshuai Li
 */
public class NeutronQosRule implements NetQosRule {

    @JsonProperty("qos_policy_id")
    private String qosPolicy;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQosPolicy() {
        return this.qosPolicy;
    }
}
