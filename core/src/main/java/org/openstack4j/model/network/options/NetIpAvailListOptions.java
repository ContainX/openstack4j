package org.openstack4j.model.network.options;

import org.openstack4j.model.common.BaseListOptions;
import org.openstack4j.model.network.IPVersionType;

/**
 * @author bboyHan
 */
public class NetIpAvailListOptions extends BaseListOptions<NetIpAvailListOptions> {

    private NetIpAvailListOptions() {
    }

    public static NetIpAvailListOptions create() {
        return new NetIpAvailListOptions();
    }

    public NetIpAvailListOptions networkId(String networkId) {
        return add("network_id", networkId);
    }

    public NetIpAvailListOptions networkName(String networkName) {
        return add("network_name", networkName);
    }

    public NetIpAvailListOptions tenantId(String tenantId) {
        return add("tenant_id", tenantId);
    }

    public NetIpAvailListOptions projectId(String projectId) {
        return add("project_id", projectId);
    }

    public NetIpAvailListOptions ipVersion(IPVersionType ipVersionType) {
        return add("ip_version", String.valueOf(ipVersionType.getVersion()));
    }

    public NetIpAvailListOptions add(String key, String value) {
        filter(key, value);
        return this;
    }
}
