package org.openstack4j.model.network;

import org.openstack4j.model.ModelEntity;

/**
 *
 *
 * @author Luigi De Masi
 */

public interface ExternalFixedIps extends ModelEntity {


    /**
     * @return external Ip Address
     */
    String getIpAddress();

    /**
     * @return external SubnetId
     */
    String getSubnetId();


}
