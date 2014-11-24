package org.openstack4j.openstack.networking.domain;

import static com.google.common.base.Objects.toStringHelper;

import org.openstack4j.model.network.NetQuota;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Network quotas that are bound to a Tenant
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("quota")
public class NeutronNetQuota implements NetQuota {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private int subnet;
    @JsonProperty
    private int router;
    @JsonProperty
    private int port;
    @JsonProperty
    private int network;
    @JsonProperty("floatingip")
    private int floatingIp;
    
    @Override
    public int getSubnet() {
        return subnet;
    }

    @Override
    public int getRouter() {
        return router;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getNetwork() {
        return network;
    }

    @JsonIgnore
    @Override
    public int getFloatingIP() {
        return floatingIp;
    }
    
    @Override
    public String toString() {
        return toStringHelper(this)
                .add("subnet", subnet).add("router", router).add("port", port)
                .add("network", network).add("floatingIp", floatingIp)
                .toString();
    }

}
