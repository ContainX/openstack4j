package org.openstack4j.api.bareMetal;

import org.openstack4j.common.RestService;

/**
 * bare metal service
 *
 * @author zhangliang
 */
public interface BareMetalService extends RestService {

    /**
     * Chassis Service API
     *
     * @return the chassis service
     */
    ChassisService chassis();

    /**
     * Driver Service API
     *
     * @return the driver service
     */
    DriverService drivers();

    /**
     * Node Service API
     *
     * @return the node service
     */
    NodeService nodes();

    /**
     * Port Service API
     *
     * @return the port service
     */
    PortService ports();

}
