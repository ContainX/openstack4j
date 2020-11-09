package org.openstack4j.model.network.options;

import org.openstack4j.model.network.NetworkType;

/**
 * @author bboyHan
 */
public class NetworkListOptions extends BaseListOptions<NetworkListOptions> {

    private NetworkListOptions() {
    }

    public NetworkListOptions name(String name) {
        return add("name", name);
    }

    public NetworkListOptions projectId(String projectId) {
        return add("project_id", projectId);
    }

    public NetworkListOptions tenantId(String tenantId) {
        return add("tenant_id", tenantId);
    }

    public NetworkListOptions networkType(NetworkType networkType) {
        return add("provider:network_type", networkType.getNetworkType());
    }

    public NetworkListOptions description(String description) {
        return add("description", description);
    }

    public NetworkListOptions shared(boolean shared) {
        return add("shared", shared);
    }

    /**
     * port status - ACTIVE, DOWN, BUILD and ERROR
     *
     * @param status - ACTIVE, DOWN, BUILD and ERROR
     * @return options
     */
    public NetworkListOptions status(String status) {
        return add("status", status);
    }

    public static NetworkListOptions create() {
        return new NetworkListOptions();
    }

    public NetworkListOptions add(String key, Object value) {
        putParams(key, value);
        return this;
    }


}
