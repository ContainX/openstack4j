package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.BootDevice;

public class BareMetalBootDevice implements BootDevice {

    @JsonProperty("boot_device")
    private String bootDevice;

    @JsonProperty("persistent")
    private boolean persistent;

    @Override
    public String getBootDevice() {
        return bootDevice;
    }

    @Override
    public boolean getPersistent() {
        return persistent;
    }
}
