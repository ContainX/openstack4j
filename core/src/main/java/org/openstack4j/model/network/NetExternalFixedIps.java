package org.openstack4j.model.network;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.builder.NetExternalFixedIpsBuilder;

public interface NetExternalFixedIps extends ModelEntity, Buildable<NetExternalFixedIpsBuilder> {

    String getIpAddress();

    String getSubnetId();

}
