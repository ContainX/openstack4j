package org.openstack4j.model.network.builder;
import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.NetExternalFixedIps;
public interface NetExternalFixedIpsBuilder extends Builder<NetExternalFixedIpsBuilder, NetExternalFixedIps> {

    NetExternalFixedIpsBuilder ipAddress(String ipAddress);

    NetExternalFixedIpsBuilder subnetId(String subnetId);


}
