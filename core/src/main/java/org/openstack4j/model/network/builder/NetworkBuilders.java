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
    NetworkBuilder network();

    /**
     * The builder to update a network
     *
     * @return the NetworkUpdateBuilder
     */
    NetworkUpdateBuilder networkUpdate();

    ExtraDhcpOptBuilder extraDhcpOpt();

    /**
     * The builder to create a Subnet
     *
     * @return the subnet builder
     */
    SubnetBuilder subnet();

    /**
     * The builder to create a Port
     *
     * @return the port builder
     */
    PortBuilder port();

    /**
     * The builder to create a Router
     *
     * @return the router builder
     */
    RouterBuilder router();

    /**
     * The builder to create a Neutron Security Group
     *
     * @return the security group builder
     */
    NetSecurityGroupBuilder securityGroup();

    /**
     * The builder to create a Neutron Security Group Rule
     *
     * @return the security group builder
     */
    NetSecurityGroupRuleBuilder securityGroupRule();

    /**
     * The builder to create a Neutron Floating IP Address
     *
     * @return the floating ip builder
     */
    NetFloatingIPBuilder netFloatingIP();

    /**
     * The builder to create NetQuota entities
     *
     * @return the NetQuota builder
     */
    NetQuotaBuilder netQuota();

    /**
     * The builder to create a lb member
     *
     * @return the Member Builder
     */
    MemberBuilder member();

    /**
     * The builder to update a lb member
     *
     * @return the MemberUpdate Builder
     */
    MemberUpdateBuilder memberUpdate();

    /**
     * The builder to create and update a sessionPersistence
     *
     * @return SessionPersistenceBuilder
     */
    SessionPersistenceBuilder sessionPersistence();

    /**
     * The builder to create a vip.
     *
     * @return VipBuilder the vip builder
     */
    VipBuilder vip();

    /**
     * The builder to update a vip.
     *
     * @return VipUpdateBuilder
     */
    VipUpdateBuilder vipUpdate();

    /**
     * The builder to create a healthMonitor
     *
     * @return HealthMonitorBuilder
     */
    HealthMonitorBuilder healthMonitor();

    /**
     * The builder to update a healthMonitor
     *
     * @return HealthMonitorUpdateBuilder
     */
    HealthMonitorUpdateBuilder healthMonitorUpdate();

    /**
     * The builder to create a firewall
     *
     * @return FirewallBuilder
     */
    FirewallBuilder firewall();

    /**
     * The builder to update a healthMonitor
     *
     * @return FirewallUpdateBuilder
     */
    FirewallUpdateBuilder firewallUpdate();

    /**
     * The builder to create a firewallRule
     *
     * @return FirewallRuleBuilder
     */
    FirewallRuleBuilder firewallRule();

    /**
     * The builder to update a firewallRule
     *
     * @return FirewallUpdateBuilder
     */
    FirewallRuleUpdateBuilder firewallRuleUpdate();

    /**
     * The builder to create a firewallPolicy
     *
     * @return FirewallPolicyBuilder
     */
    FirewallPolicyBuilder firewallPolicy();

    /**
     * The builder to update a firewallPolicy
     *
     * @return FirewallPolicyUpdateBuilder
     */
    FirewallPolicyUpdateBuilder firewallPolicyUpdate();

    /**
     * The builder to create a lbPool
     *
     * @return LbPoolBuilder
     */
    LbPoolBuilder lbPool();

    /**
     * The builder to update a lbPool
     *
     * @return LbPoolUpdateBuilder
     */
    LbPoolUpdateBuilder lbPoolUpdate();

    /**
     * The builder to create a lbPool
     *
     * @return HealthMonitorAssociateBuilder
     */
    HealthMonitorAssociateBuilder lbPoolAssociateHealthMonitor();

    /**
     * The builder to create a lb v2 member
     *
     * @return the Member Builder v2
     */
    MemberV2Builder memberV2();

    /**
     * The builder to update a lbaas v2 member
     *
     * @return MemberV2UpdateBuilder
     */
    MemberV2UpdateBuilder memberV2Update();

    /**
     * The builder to create a v2 loadbalancer.
     *
     * @return LoadBalancerV2Builder the loadbalancer v2 builder
     */
    LoadBalancerV2Builder lbV2();

    /**
     * The builder to update a lbaas v2 loadbalancer
     *
     * @return LoadBalancerV2UpdateBuilder
     */
    LoadBalancerV2UpdateBuilder loadBalancerV2Update();

    /**
     * The builder to create a v2 lbPool
     *
     * @return LbPoolV2Builder
     */
    LbPoolV2Builder lbPoolV2();

    /**
     * The builder to update a lbaas v2 lbPool
     *
     * @return LbPoolV2UpdateBuilder
     */
    LbPoolV2UpdateBuilder lbPoolV2Update();

    /**
     * The builder to createa v2 lbaas listener
     *
     * @return ListenerV2Builder
     */
    ListenerV2Builder listenerV2();

    /**
     * The builder to update a lbaas v2 lbaas listener
     *
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder listenerV2Update();

    /**
     * The builder to create a v2 healthMonitor
     *
     * @return HealthMonitorV2Builder
     */
    HealthMonitorV2Builder healthMonitorV2();

    /**
     * The builder to update a lbaas v2 health monitor
     *
     * @return HealthMonitorV2UpdateBuilder
     */
    HealthMonitorV2UpdateBuilder healthMonitorV2Update();
}
