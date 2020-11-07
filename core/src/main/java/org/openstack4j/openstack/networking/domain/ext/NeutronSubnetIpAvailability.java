package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.SubnetIpAvailability;

/**
 * @author bboyHan
 */
public class NeutronSubnetIpAvailability implements SubnetIpAvailability {

    @JsonProperty("used_ips")
    private String usedIps;
    @JsonProperty("subnet_id")
    private String subnetId;
    @JsonProperty("subnet_name")
    private String subnetName;
    @JsonProperty("ip_version")
    private IPVersionType ipVersion;
    private String cidr;
    @JsonProperty("total_ips")
    private String totalIps;

    public NeutronSubnetIpAvailability() {
    }

    public NeutronSubnetIpAvailability(String usedIps, String subnetId,
                                       String subnetName, IPVersionType ipVersion,
                                       String cidr, String totalIps) {
        this.usedIps = usedIps;
        this.subnetId = subnetId;
        this.subnetName = subnetName;
        this.ipVersion = ipVersion;
        this.cidr = cidr;
        this.totalIps = totalIps;
    }

    @Override
    public String getUsedIps() {
        return usedIps;
    }

    @Override
    public String getSubnetId() {
        return subnetId;
    }

    @Override
    public String getSubnetName() {
        return subnetName;
    }

    @Override
    public IPVersionType getIpVersion() {
        return ipVersion;
    }

    @Override
    public String getCidr() {
        return cidr;
    }

    @Override
    public String getTotalIps() {
        return totalIps;
    }
}
