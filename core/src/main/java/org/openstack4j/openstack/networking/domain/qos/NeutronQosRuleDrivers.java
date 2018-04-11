package org.openstack4j.openstack.networking.domain.qos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.qos.NetQosRuleDriver;
import org.openstack4j.model.network.qos.NetQosRuleDrivers;

/**
 * The Class Neutron Qos Rule Drivers.
 *
 * @author Guoshuai Li
 */
public class NeutronQosRuleDrivers implements NetQosRuleDrivers {

    @JsonProperty("drivers")
    private List<NeutronQosRuleDriver> drivers;

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
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosRuleDriver> getDrivers() {
        return this.drivers;
    }
}


