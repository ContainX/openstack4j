package org.openstack4j.model.bareMetal;

/**
 * Boot Device
 * @author zhangliang
 */
public interface BootDevice {

    /**
     * The boot device for a Node, eg. “pxe” or “disk”.
     * @return
     */
    String getBootDevice();

    /**
     * Whether the boot device should be set only for the next reboot, or persistently.
     * @return
     */
    boolean getPersistent();

}
