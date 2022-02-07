package org.openstack4j.model.network.options;

/**
 * @author bboyHan
 */
public class SubnetListOptions extends BaseListOptions<SubnetListOptions> {

    private SubnetListOptions() {
    }

    public SubnetListOptions name(String name) {
        return add("name", name);
    }

    public SubnetListOptions projectId(String projectId) {
        return add("project_id", projectId);
    }

    /**
     * port status - ACTIVE, DOWN, BUILD and ERROR
     *
     * @param status - ACTIVE, DOWN, BUILD and ERROR
     * @return options
     */
    public SubnetListOptions status(String status) {
        return add("status", status);
    }

    public SubnetListOptions ipVersion(int ipVersion) {
        return add("ip_version", ipVersion);
    }

    public SubnetListOptions enableDHCP(boolean enableDHCP) {
        return add("enable_dhcp", enableDHCP);
    }

    public SubnetListOptions gatewayIp(boolean gatewayIp) {
        return add("gateway_ip", gatewayIp);
    }

    public SubnetListOptions cidr(boolean cidr) {
        return add("cidr", cidr);
    }

    public SubnetListOptions segmentId(boolean segmentId) {
        return add("segment_id", segmentId);
    }

    public SubnetListOptions shared(boolean shared) {
        return add("shared", shared);
    }

    public static SubnetListOptions create() {
        return new SubnetListOptions();
    }

    public SubnetListOptions add(String key, Object value) {
        putParams(key, value);
        return this;
    }

}
