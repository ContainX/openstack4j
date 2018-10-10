package org.openstack4j.model.bareMetal;

import java.util.List;

/**
 * Supported Boot Devices
 * @author zhangliang
 */
public interface NodeSupportedBootDevices {

    /**
     * Get Supported Boot Devices
     * @return
     */
    List<String> getSupportedBootDevices();

}
