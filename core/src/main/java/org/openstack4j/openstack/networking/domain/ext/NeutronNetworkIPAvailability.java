package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.NetworkIPAvailability;
import org.openstack4j.openstack.common.ListEntity;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * An OpenStack (Neutron) network ip availabilities
 *
 * @author bboyHan
 */
@JsonRootName("network_ip_availability")
public class NeutronNetworkIPAvailability implements NetworkIPAvailability {

    private static final long serialVersionUID = 1L;

    @JsonProperty("network_id")
    private String networkId;
    @JsonProperty("network_name")
    private String networkName;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("total_ips")
    private String totalIps;
    @JsonProperty("used_ips")
    private String usedIps;
    @JsonProperty("subnet_ip_availability")
    private List<NeutronSubnetIpAvailability> subnetIpAvailabilities;

    public NeutronNetworkIPAvailability() {
    }

    public NeutronNetworkIPAvailability(String networkId, String networkName, String tenantId,
                                        String projectId, String totalIps, String usedIps,
                                        List<NeutronSubnetIpAvailability> subnetIpAvailabilities) {
        this.networkId = networkId;
        this.networkName = networkName;
        this.tenantId = tenantId;
        this.projectId = projectId;
        this.totalIps = totalIps;
        this.usedIps = usedIps;
        this.subnetIpAvailabilities = subnetIpAvailabilities;
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public String getNetworkName() {
        return networkName;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getTotalIps() {
        return totalIps;
    }

    @Override
    public String getUsedIps() {
        return usedIps;
    }

    @Override
    public List<NeutronSubnetIpAvailability> getSubnetIpAvailabilities() {
        return subnetIpAvailabilities;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("networkId", networkId)
                .add("networkName", networkName)
                .add("tenantId", tenantId)
                .add("projectId", projectId)
                .add("totalIps", totalIps)
                .add("usedIps", usedIps)
                .add("tenantId", tenantId)
                .add("subnetIpAvailabilities", subnetIpAvailabilities)
                .toString();
    }

    @SuppressWarnings("unchecked")
    public static NeutronNetworkIPAvailability fromNeutronNetIpAvailability(NetworkIPAvailability networkIPAvailability) {
        NeutronNetworkIPAvailability n = new NeutronNetworkIPAvailability();
        n.networkId = networkIPAvailability.getNetworkId();
        n.networkName = networkIPAvailability.getNetworkName();
        n.tenantId = networkIPAvailability.getTenantId();
        n.projectId = networkIPAvailability.getProjectId();
        n.totalIps = networkIPAvailability.getTotalIps();
        n.usedIps = networkIPAvailability.getUsedIps();
        n.subnetIpAvailabilities = (List<NeutronSubnetIpAvailability>) networkIPAvailability.getSubnetIpAvailabilities();
        return n;
    }

    public static class NetworkIPAvailabilities extends ListResult<NeutronNetworkIPAvailability> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("network_ip_availabilities")
        private List<NeutronNetworkIPAvailability> netIpAvailabilities;

        public NetworkIPAvailabilities() {
            netIpAvailabilities = new ListEntity<>();
        }

        @Override
        public List<NeutronNetworkIPAvailability> value() {
            return netIpAvailabilities;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("netIpAvailabilities", netIpAvailabilities).toString();
        }

        @SuppressWarnings("unchecked")
        public static NetworkIPAvailabilities fromNetsIpAvailabilities(List<? extends NetworkIPAvailability> netsIpAvailabilities) {
            NetworkIPAvailabilities n = new NetworkIPAvailabilities();
            for (NetworkIPAvailability networkIPAvailability : netsIpAvailabilities) {
                n.netIpAvailabilities.add(NeutronNetworkIPAvailability.fromNeutronNetIpAvailability(networkIPAvailability));
            }
            return n;
        }
    }
}
