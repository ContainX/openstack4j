package org.openstack4j.model.network.builder;

import org.openstack4j.model.network.ext.builder.*;

/**
 * The Network builders
 */
public interface NetworkBuilders {

    /**
     * The builder to create a Network
     *
     * @return the network builder
     */
    public NetworkBuilder network();

    /**
     * The builder to update a network
     *
     * @return the NetworkUpdateBuilder
     */
    public NetworkUpdateBuilder networkUpdate();

    public ExtraDhcpOptBuilder extraDhcpOpt();

    /**
     * The builder to create a Subnet
     *
     * @return the subnet builder
     */
    public SubnetBuilder subnet();

    /**
     * The builder to create a Port
     *
     * @return the port builder
     */
    public PortBuilder port();

    /**
     * The builder to create a Router
     *
     * @return the router builder
     */
    public RouterBuilder router();

    /**
     * The builder to create a Neutron Security Group
     *
     * @return the security group builder
     */
    public NetSecurityGroupBuilder securityGroup();

    /**
     * The builder to create a Neutron Security Group Rule
     *
     * @return the security group builder
     */
    public NetSecurityGroupRuleBuilder securityGroupRule();

    /**
     * The builder to create a Neutron Floating IP Address
     *
     * @return the floating ip builder
     */
    public NetFloatingIPBuilder netFloatingIP();

    /**
     * The builder to create NetQuota entities
     *
     * @return the NetQuota builder
     */
    public NetQuotaBuilder netQuota();

    /**
     * The builder to create a lb member
     *
     * @return the Member Builder
     */
    public MemberBuilder member();

    /**
     * The builder to update a lb member
     *
     * @return the MemberUpdate Builder
     */
    public MemberUpdateBuilder memberUpdate();

    /**
     * The builder to create and update a sessionPersistence
     *
     * @return SessionPersistenceBuilder
     */
    public SessionPersistenceBuilder sessionPersistence();

    /**
     * The builder to create a vip.
     *
     * @return VipBuilder the vip builder
     */
    public VipBuilder vip();

    /**
     * The builder to update a vip.
     *
     * @return VipUpdateBuilder
     */
    public VipUpdateBuilder vipUpdate();

    /**
     * The builder to create a healthMonitor
     *
     * @return HealthMonitorBuilder
     */
    public HealthMonitorBuilder healthMonitor();

    /**
     * The builder to update a healthMonitor
     *
     * @return HealthMonitorUpdateBuilder
     */
    public HealthMonitorUpdateBuilder healthMonitorUpdate();

    /**
     * The builder to create a firewall
     *
     * @return FirewallBuilder
     */
    public FirewallBuilder firewall();

    /**
     * The builder to update a healthMonitor
     *
     * @return FirewallUpdateBuilder
     */
    public FirewallUpdateBuilder firewallUpdate();

    /**
     * The builder to create a firewallRule
     *
     * @return FirewallRuleBuilder
     */
    public FirewallRuleBuilder firewallRule();

    /**
     * The builder to update a firewallRule
     *
     * @return FirewallUpdateBuilder
     */
    public FirewallRuleUpdateBuilder firewallRuleUpdate();

    /**
     * The builder to create a firewallPolicy
     *
     * @return FirewallPolicyBuilder
     */
    public FirewallPolicyBuilder firewallPolicy();

    /**
     * The builder to update a firewallPolicy
     *
     * @return FirewallPolicyUpdateBuilder
     */
    public FirewallPolicyUpdateBuilder firewallPolicyUpdate();

    /**
     * The builder to create a lbPool
     *
     * @return LbPoolBuilder
     */
    public LbPoolBuilder lbPool();

    /**
     * The builder to update a lbPool
     *
     * @return LbPoolUpdateBuilder
     */
    public LbPoolUpdateBuilder lbPoolUpdate();

    /**
     * The builder to create a lbPool
     *
     * @return HealthMonitorAssociateBuilder
     */
    public HealthMonitorAssociateBuilder lbPoolAssociateHealthMonitor();

}
