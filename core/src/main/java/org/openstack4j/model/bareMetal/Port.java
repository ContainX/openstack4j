package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.builder.PortBuilder;
import org.openstack4j.model.common.Resource;

import java.util.Map;

/**
 * Bare Metal Port
 *
 * @author zhangliang
 */
public interface Port extends Resource, Buildable<PortBuilder> {

    /**
     * The UUID for the resource
     * @return
     */
    String getUuid();

    /**
     * Physical hardware address of this network Port, typically the hardware MAC address.
     * @return
     */
    String getAddress();

    /**
     * UUID of the Node this resource belongs to.
     * @return
     */
    String getNodeUuid();

    /**
     * UUID of the Portgroup this resource belongs to.
     * @return
     */
    String getPortgroupUuid();

    /**
     * Indicates whether PXE is enabled or disabled on the Port.
     * @return
     */
    Boolean getPxeEnable();

    /**
     * 	The name of the physical network to which a port is connected. May be empty.
     * @return
     */
    String getPhysicalNetwork();

    /**
     * A set of one or more arbitrary metadata key and value pairs.
     * @return
     */
    Map<String, String> getExtra();

}
