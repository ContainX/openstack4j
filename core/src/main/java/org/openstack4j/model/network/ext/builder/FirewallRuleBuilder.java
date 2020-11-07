package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.ext.FirewallRule;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallRule;

/**
 * A Builder to Create Firewall Rule of FwaaS.
 *
 * @author Vishvesh Deshmukh
 */
public interface FirewallRuleBuilder extends Builder<FirewallRuleBuilder, FirewallRule> {

    /**
     * @param name : Human readable name for the firewall rule (255 characters limit). Does not have to be unique.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder name(String name);

    /**
     * @param tenantId : Owner of the Firewall Rule. Only an administrative user can
     *                 specify a tenant ID other than its own.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder tenantId(String tenantId);

    /**
     * @param description : Human readable description for the firewall rule (1024 characters limit).
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder description(String description);

    /**
     * @param shared : When set to True makes this firewall rule visible to tenants other than its owner,
     *               and can be used in firewall policies not owned by its tenant.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder shared(Boolean shared);

    /**
     * @param protocol : IP Protocol : Possible values are ICMP/TCP/UDP/NONE(ANY).
     * @return FirewallRuleBuilder
     * @see NeutronFirewallRule.IPProtocol
     */
    FirewallRuleBuilder protocol(NeutronFirewallRule.IPProtocol protocol);

    /**
     * @param ipVersion : IP Protocol Version : Possible values are 4/6.
     * @return FirewallRuleBuilder
     * @see IPVersionType
     */
    FirewallRuleBuilder ipVersion(IPVersionType ipVersion);

    /**
     * @param sourceIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder sourceIpAddress(String sourceIpAddress);

    /**
     * @param destinationIpAddress or CIDR : Valid IP address (v4 or v6), or CIDR.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder destinationIpAddress(String destinationIpAddress);

    /**
     * @param sourcePort : Valid port number (integer or FirewallRuleBuilder), or port range in the format of a ':' separated range).
     *                   In the case of port range, both ends of the range are included.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder sourcePort(String sourcePort);

    /**
     * @param destinationPort : Valid port number (integer or FirewallRuleBuilder), or port range in the format of a ':' separated range).
     *                        In the case of port range, both ends of the range are included.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder destinationPort(String destinationPort);

    /**
     * @param action : Action to be performed on the traffic matching the rule (allow, deny).
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder action(NeutronFirewallRule.FirewallRuleAction action);

    /**
     * @param enabled : When set to False will disable this rule in the firewall policy. Facilitates selectively turning off
     *                rules without having to disassociate the rule from the firewall policy.
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder enabled(Boolean enabled);

}
