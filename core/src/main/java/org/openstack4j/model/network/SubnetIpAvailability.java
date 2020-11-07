package org.openstack4j.model.network;

import org.openstack4j.model.ModelEntity;

/**
 * @author bboyHan
 */
public interface SubnetIpAvailability extends ModelEntity {

    String getUsedIps();

    String getSubnetId();

    String getSubnetName();

    IPVersionType getIpVersion();

    String getCidr();

    String getTotalIps();
}
