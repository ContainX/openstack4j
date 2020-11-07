package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.ext.builder.FirewallRuleUpdateBuilder;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;

/**
 * <p>A Builder to Update Firewall Rule of FwaaS</p>
 *
 * <p>Represents a collection of attributes like ports, ip addresses which define match
 * criteria and action (allow, or deny) that needs to be taken on the matched data traffic.</p>
 *
 * @author Vishvesh Deshmukh
 */
public interface FirewallRuleUpdate extends ModelEntity, Buildable<FirewallRuleUpdateBuilder> {

    /**
     * @return name : Human readable name for the firewall rule (255 characters limit). Does not have to be unique.
     */
    String getName();

    /**
     * @return tenantId : Owner of the Firewall Rule. Only an administrative user can
     * specify a tenant ID other than its own.
     */
    String getTenantId();

    /**
     * @return description : Human readable description for the firewall rule (1024 characters limit).
     */
    String getDescription();

    /**
     * @return shared : When set to True makes this firewall rule visible to tenants other than its owner,
     * and can be used in firewall policies not owned by its tenant.
     */
    Boolean isShared();

    /**
     * @return protocol : IP Protocol : Possible values are ICMP/TCP/UDP/NONE(ANY).
     * @see NeutronFirewallRule.IPProtocol
     */
    NeutronFirewallRule.IPProtocol getProtocol();

    /**
     * @return ipVersion : IP Protocol Version : Possible values are 4/6.
     * @see IPVersionType
     */
    IPVersionType getIpVersion();

    /**
     * @return sourceIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     */
    String getSourceIpAddress();

    /**
     * @return destinationIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     */
    String getDestinationIpAddress();

    /**
     * @return sourcePort : Valid port number (integer or string), or port range in the format of a ':' separated range).
     * In the case of port range, both ends of the range are included.
     */
    String getSourcePort();

    /**
     * @return destinationPort : Valid port number (integer or string), or port range in the format of a ':' separated range).
     * In the case of port range, both ends of the range are included.
     */
    String getDestinationPort();

    /**
     * @return action : Action to be performed on the traffic matching the rule (allow, deny).
     * @see NeutronFirewallRule.FirewallRuleAction
     */
    NeutronFirewallRule.FirewallRuleAction getAction();

    /**
     * @return enabled : When set to False will disable this rule in the firewall policy. Facilitates selectively turning off
     * rules without having to disassociate the rule from the firewall policy.
     */
    Boolean isEnabled();
}
