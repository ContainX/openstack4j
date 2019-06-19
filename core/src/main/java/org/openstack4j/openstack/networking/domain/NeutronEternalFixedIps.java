package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.ExternalFixedIps;

public class NeutronEternalFixedIps implements ExternalFixedIps {


    private String ipAddress;

    private String subnetId;

    @Override
    @JsonProperty("ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    @JsonProperty("subnet_id")
    public String getSubnetId() {
        return subnetId;
    }
}

