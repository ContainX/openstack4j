package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.bareMetal.NodeSupportedBootDevices;

import java.util.List;

public class BareMetalNodeSupportedBootDevices implements NodeSupportedBootDevices {

    @JsonProperty("supported_boot_devices")
    private List<String> supportedBootDevices;

    @Override
    public List<String> getSupportedBootDevices() {
        return supportedBootDevices;
    }
}
