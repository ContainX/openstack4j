package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.bareMetal.Port;

import java.util.Map;

/**
 * A Builder which creates a Bare Metal Port
 *
 * @author zhangliang
 */
public interface PortBuilder extends Builder<PortBuilder, Port> {

    /**
     * set nodeUuid
     * @param nodeUuid
     * @return
     */
    PortBuilder nodeUuid(String nodeUuid);

    /**
     * set address
     * @param address
     * @return
     */
    PortBuilder address(String address);

    /**
     * set portgroupUuid
     * @param portgroupUuid
     * @return
     */
    PortBuilder portgroupUuid(String portgroupUuid);

    /**
     * set pxe enable
     * @param pxeEnable
     * @return
     */
    PortBuilder pxeEnable(Boolean pxeEnable);

    /**
     * set physicalNetwork
     * @param networkName
     * @return
     */
    PortBuilder physicalNetwork(String networkName);

    /**
     * set extra
     * @param extra
     * @return
     */
    PortBuilder extra(Map<String, String> extra);

    /**
     * add key and value to extra
     * @param key
     * @param value
     * @return
     */
    PortBuilder addExtraItem(String key, String value);

}
