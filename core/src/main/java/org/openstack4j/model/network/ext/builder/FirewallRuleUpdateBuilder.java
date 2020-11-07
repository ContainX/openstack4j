package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.ext.FirewallRuleUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;

/**
 * A Builder to Update Firewall Rule of FwaaS
 *
 * @author Vishvesh Deshmukh
 */
public interface FirewallRuleUpdateBuilder extends Builder<FirewallRuleUpdateBuilder, FirewallRuleUpdate> {

    /**
     * @param name : Human readable name for the firewall rule (255 characters limit). Does not have to be unique.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder name(String name);

    /**
     * @param tenantId : Owner of the Firewall. Only an administrative user can
     *                 specify a tenant ID other than its own.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder tenantId(String tenantId);

    /**
     * @param description : Human readable description for the firewall rule (1024 characters limit).
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder description(String description);

    /**
     * @param shared : When set to True makes this firewall rule visible to tenants other than its owner,
     *               and can be used in firewall policies not owned by its tenant.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder shared(Boolean shared);

    /**
     * @param protocol : IP Protocol : Possible values are ICMP/TCP/UDP/NONE(ANY).
     * @return FirewallRuleUpdateBuilder
     * @see NeutronFirewallRule.IPProtocol
     */
    FirewallRuleUpdateBuilder protocol(NeutronFirewallRule.IPProtocol protocol);

    /**
     * @param ipVersion : IP Protocol Version : Possible values are 4/6.
     * @return FirewallRuleUpdateBuilder
     * @see IPVersionType
     */
    FirewallRuleUpdateBuilder ipVersion(IPVersionType ipVersion);

    /**
     * @param sourceIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder sourceIpAddress(String sourceIpAddress);

    /**
     * @param destinationIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder destinationIpAddress(String destinationIpAddress);

    /**
     * @param sourcePort : Valid port number (integer or FirewallRuleUpdateBuilder), or port range in the format of a ':' separated range).
     *                   In the case of port range, both ends of the range are included.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder sourcePort(String sourcePort);

    /**
     * @param destinationPort : Valid port number (integer or FirewallRuleUpdateBuilder), or port range in the format of a ':' separated range).
     *                        In the case of port range, both ends of the range are included.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder destinationPort(String destinationPort);

    /**
     * @param action : Action to be performed on the traffic matching the rule (allow, deny).
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder action(NeutronFirewallRule.FirewallRuleAction action);

    /**
     * @param enabled : When set to False will disable this rule in the firewall policy. Facilitates selectively turning off
     *                rules without having to disassociate the rule from the firewall policy.
     * @return FirewallRuleUpdateBuilder
     */
    FirewallRuleUpdateBuilder enabled(Boolean enabled);
}
